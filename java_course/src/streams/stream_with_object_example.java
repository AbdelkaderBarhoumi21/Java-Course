package streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Etudiant(String nom, int age, double moyenne, String filiere) {
}

public class stream_with_object_example {
    public static void main(String[] args) {

        // Create an immutable list of students using the Etudiant record
        List<Etudiant> etudiants = List.of(
                new Etudiant("Alice", 22, 16.5, "Informatique"),
                new Etudiant("Bob", 20, 12.0, "Mathématiques"),
                new Etudiant("Charlie", 23, 14.5, "Informatique"),
                new Etudiant("Diana", 21, 18.0, "Physique"),
                new Etudiant("Eve", 22, 11.5, "Informatique"));

        // Get names of Computer Science students with average > 14, sorted by
        // descending average
        List<String> topInfo = etudiants.stream().filter(e -> e.filiere().equals("Informatique"))
                .filter(e -> e.moyenne() > 14).sorted((a, b) -> Double.compare(b.moyenne(), a.moyenne()))
                .map(Etudiant::nom).toList();
        System.out.println(topInfo);

        // Group students by their major (filiere)
        Map<String, List<Etudiant>> parFiliere = etudiants.stream()
                .collect(Collectors.groupingBy(Etudiant::filiere));
        System.out.println(parFiliere);

        // Compute the average grade per major
        Map<String, Double> moyennePerFileier = etudiants.stream()
                .collect(Collectors.groupingBy(Etudiant::filiere, Collectors.averagingDouble(Etudiant::moyenne)));
        System.out.println(moyennePerFileier);

        // Partition students into two groups: passed (>= 12) vs failed
        Map<Boolean, List<Etudiant>> partition = etudiants.stream()
                .collect(Collectors.partitioningBy(e -> e.moyenne() >= 12));

    }

}
