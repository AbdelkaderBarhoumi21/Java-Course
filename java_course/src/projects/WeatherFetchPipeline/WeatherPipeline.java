
/*
            ┌─────────────────┐
            │ WeatherService  │  ← orchestrates (Single Responsibility: "get me weather")
            └────────┬────────┘
                     │ depends on the INTERFACE, not the impl  (Dependency Inversion)
          ┌──────────▼──────────┐
          │  WeatherSource      │  (interface / Strategy)
          └──────────┬──────────┘
        ┌────────────┴────────────┐
        ▼                         ▼
 FakeApiSource            (real HttpSource — drop-in later)
        │
        ▼ async, in parallel for N cities
   List<Result<Weather>>  → cache to weather_cache.json


   Step 1 — .map(supplyAsync)

→ launches all fetches IN PARALLEL

→ returns List<Future> — no values ​​yet

Step 2 — .map(join)

→ waits for each future

→ returns List<Result> — actual values

join() AFTER toList() = fan-out pattern
join() DURING the map = sequential — loses all the advantage

*/
package projects.WeatherFetchPipeline;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

record Weather(String city, double tempC, String condition) {
    String toJson() {
        return "{\"city\":\"%s\",\"tempC\":%.1f,\"condition\":\"%s\"}"
                .formatted(city, tempC, condition);
    }
}
// A tiny Result type (Java has none built in) — sealed for exhaustive handling

sealed interface Result<T> permits Result.Ok, Result.Err {

    record Ok<T>(T value) implements Result<T> {
    }

    record Err<T>(String message) implements Result<T> {
    }

}

interface WeatherSource {
    Weather fetch(String city) throws Exception;
}

class FakeApiSource implements WeatherSource {
    @Override
    public Weather fetch(String city) throws Exception {
        Thread.sleep(300); // simulate latency
        if (city.equals("Atlantis")) {
            throw new IllegalStateException("City not found");
        }
        return new Weather(city, 15.0 + city.length(), "Sunny");
    }
}

class WeatherService {

    // Injected dependency: source can vary (real API, mock, stub…)
    // Caller decides which implementation to use — supports DIP & testing
    private final WeatherSource source;

    // Internal implementation detail: always 4 threads, caller doesn't need to know
    // Not injected — no reason to expose this choice outside the class
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    // DIP: depend on the abstraction (WeatherSource), not the concrete
    // implementation
    WeatherService(WeatherSource source) {
        this.source = source;
    }

    // Fan out with CompletableFuture; each result wrapped so one failure is
    // isolated
    List<Result<Weather>> fetchAll(List<String> cities) {
        List<CompletableFuture<Result<Weather>>> futures = cities.stream()
                .map(city -> CompletableFuture.<Result<Weather>>supplyAsync(() -> {
                
                    try {
                        return new Result.Ok<>(source.fetch(city));
                    } catch (Exception e) {
                        return new Result.Err<Weather>(e.getMessage());
                    }
                }, pool))
                .toList();
        return futures.stream().map(CompletableFuture::join).toList();
        // futures.stream().map(CompletableFuture::join).toList(); method reference —
        // raccourci == .map(t -> t.join())
    }

    void cache(List<Result<Weather>> results, String path) throws IOException {
        String json = results.stream()
                .filter(r -> r instanceof Result.Ok)
                .map(r -> ((Result.Ok<Weather>) r).value().toJson())
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        try (var writer = new FileWriter(path)) {
            writer.write(json);
        }
    }

    void shutdown() {
        pool.shutdown();
    }
}

public class WeatherPipeline {

    public static void main(String[] args) throws IOException {

        var service = new WeatherService(new FakeApiSource());
        var cities = List.of("Paris", "Tokyo", "Atlantis", "Cairo");

        var results = service.fetchAll(cities);

        for (var r : results) { // pattern matching switch
            switch (r) {
                case Result.Ok<Weather> ok ->
                    System.out.printf("OK  %s: %.1f°C %s%n",
                            ok.value().city(), ok.value().tempC(), ok.value().condition());
                case Result.Err<Weather> err ->
                    System.out.println("ERR " + err.message());
            }
        }

        service.cache(results, "weather_cache.json");
        long saved = results.stream().filter(r -> r instanceof Result.Ok).count();
        System.out.println("Cached " + saved + " cities to weather_cache.json");
        service.shutdown();

    }

}
