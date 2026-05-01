package threads;

import java.util.concurrent.*;

/**
 * Demonstrates modern concurrency tools in Java:
 * - ExecutorService : a pool of reusable threads
 * - Future : a "ticket" to retrieve an async result (blocking)
 * - CompletableFuture: non-blocking async pipeline (chain / combine)
 *
 * Why a pool? Creating a new Thread is expensive (~1 MB of stack + OS call).
 * A pool creates N threads ONCE and reuses them for many tasks.
 */
public class thread_pools_examples {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // ----- 1. ExecutorService = pool of 4 reusable threads -----
        // Tasks go into an internal queue; the 4 threads pick them up.
        // No matter how many tasks we submit, we never have more than 4 threads.
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // ----- 2. Submit "fire and forget" tasks (no return value) -----
        // submit(Runnable) queues a task to be executed by one of the 4 threads.
        // Output order is NOT guaranteed (depends on the OS scheduler).
        // Notice the thread NAMES repeat -> proof that threads are reused.
        for (int i = 0; i < 10; i++) {
            // 'i' must be effectively final to be used inside a lambda.
            final int number = i;
            executorService.submit(() -> {
                System.out.println("Task " + number + " on " + Thread.currentThread().getName());
            });
        }

        // ----- 3. Future = ticket to get an async result -----
        // submit(Callable<T>) returns a Future<T>. The task runs in the background.
        // future.get() BLOCKS the current thread until the result is ready.
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(1000); // simulate a slow computation
            return 42;
        });
        int result = future.get(); // blocks until the worker thread finishes
        System.out.println("Future result = " + result);

        // ----- 4. CompletableFuture = modern, NON-blocking async pipeline -----
        // supplyAsync : start an async task that produces a value.
        // thenApply : transform the value once it is ready (like Stream.map).
        // thenAccept : consume the final value (terminal step, returns void).
        // The main thread does NOT wait here — the chain runs in the background.
        CompletableFuture<String> cf = CompletableFuture
                .supplyAsync(() -> "Data") // step 1: produce "Data"
                .thenApply(String::toUpperCase) // step 2: -> "DATA"
                .thenApply(s -> "Result " + s); // step 3: -> "Result DATA"
        cf.thenAccept(System.out::println); // step 4: print it

        // ----- 5. Combine multiple futures with thenCombine -----
        // f1 and f2 run IN PARALLEL on different threads.
        // thenCombine waits for BOTH, then merges their results with a BiFunction.
        // Typical use case: 2 independent API calls run at the same time.
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "world");

        f1.thenCombine(f2, (a, b) -> a + " " + b) // "hello" + " " + "world"
                .thenAccept(System.out::println); // prints "hello world"

        // ----- 6. ALWAYS shut the executor down -----
        // Pool threads are non-daemon: without shutdown(), the JVM never exits.
        // shutdown() refuses new tasks but lets the queued ones finish.
        executorService.shutdown();
        // Optional: wait up to 5s for remaining tasks (incl. the CompletableFutures
        // chained above) to complete before main returns.
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
