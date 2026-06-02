package java_course.src.projects.temperature_converter;

import java.util.Scanner;

enum Scale {
    CELSIUS, FAHRENHEIT, KELVIN
}

public class TemperatureConverter {
    // --- Pure conversion helpers (single responsibility each) ---

    static double toCelsius(double value, Scale from) {
        return switch (from) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;

        };
    }

    static double fromCelsius(double celsius, Scale to) {
        return switch (to) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> celsius * 9 / 5 + 32;
            case KELVIN -> celsius + 273.15;
        };
    }

    static double convert(double value, Scale from, Scale to) {
        double celsius = toCelsius(value, from);
        return fromCelsius(celsius, to);
    }

    static Scale parseScale(String input) {
        return switch (input.toUpperCase()) {
            case "C" -> Scale.CELSIUS;
            case "F" -> Scale.FAHRENHEIT;
            case "K" -> Scale.KELVIN;
            default -> throw new IllegalArgumentException("Invalid scale: " + input);
        };
    }

    public static void main(String[] args) {
        // try-with-resources
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Value: ");
            double value = scanner.nextDouble();
            System.out.println("From (C/F/K): ");
            Scale from = parseScale(scanner.next());
            System.out.println("To (C/F/K): ");
            Scale to = parseScale(scanner.next());

            double result = convert(value, from, to);
            System.out.printf("%.2f %s = %.2f %s%n", value, from, result, to);

        }
    }

}
