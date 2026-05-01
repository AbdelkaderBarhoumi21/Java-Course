package projects.StudentGradeSystem;

import java.util.*;
import java.util.stream.*;

// Generic repository — works for any type T
class GradeBook<T extends Number> {
    private final Map<String, T> grades = new LinkedHashMap<>();

    public void addGrade(String student, T grade) {
        grades.put(student, grade);
    }

    public Optional<T> getGrade(String student) {
        return Optional.ofNullable(grades.get(student));
    }

    public double average() {
        return grades.values().stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    public Optional<Map.Entry<String, T>> topStudent() {
        return grades.entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().doubleValue()));
    }

    public List<String> failing(double threshold) {
        return grades.entrySet().stream()
                .filter(e -> e.getValue().doubleValue() < threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void printReport() {
        System.out.println("=== Grade Report ===");
        grades.forEach((s, g) -> System.out.printf("%-15s : %s%n", s, g));
        System.out.printf("Average          : %.2f%n", average());
        topStudent().ifPresent(e -> System.out.println("Top student      : " + e.getKey() + " (" + e.getValue() + ")"));
    }
}

public class StudentGradeSystem {
    public static void main(String[] args) {

        // Works with Integer grades
        GradeBook<Integer> mathClass = new GradeBook<>();
        mathClass.addGrade("Alice", 95);
        mathClass.addGrade("Bob", 72);
        mathClass.addGrade("Carol", 88);
        mathClass.addGrade("David", 41);
        mathClass.addGrade("Eve", 63);

        mathClass.printReport();

        System.out.println("\nFailing students (< 60): " + mathClass.failing(60));

        // Works with Double grades too — same class, different type
        GradeBook<Double> scienceClass = new GradeBook<>();
        scienceClass.addGrade("Alice", 18.5);
        scienceClass.addGrade("Bob", 14.0);
        scienceClass.addGrade("Carol", 19.0);

        scienceClass.printReport();

        // Optional usage
        mathClass.getGrade("Alice")
                .ifPresentOrElse(
                        g -> System.out.println("\nAlice's grade: " + g),
                        () -> System.out.println("Student not found"));

        mathClass.getGrade("Unknown")
                .ifPresentOrElse(
                        g -> System.out.println("Grade: " + g),
                        () -> System.out.println("Student not found") // → this runs
                );
    }

}
