package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;

/**
 * Demonstrates JSON serialization and deserialization with Jackson.
 *
 * Covers:
 * - Java object -> JSON String (writeValueAsString)
 * - JSON String -> Java object (readValue)
 * - Java object -> JSON file (writeValue)
 * - JSON file -> Java object (readValue)
 * - Pretty-printed JSON output
 *
 * Maven dependency:
 * com.fasterxml.jackson.core:jackson-databind
 */
public class json_examples {
    public static void main(String[] args) {

        // ObjectMapper is the central class of Jackson.
        // It is thread-safe and expensive to build -> create one per application.
        ObjectMapper mapper = new ObjectMapper();

        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            // ----- 1. Java object -> JSON String (serialization) -----
            // Jackson reads the public getters of Student to build the JSON.
            Student student = new Student("Alice", 22, 16.5, "Computer Science");
            String json = mapper.writeValueAsString(student);
            System.out.println("Serialized JSON: " + json);

            Student s = new Student("Alice", 11, 11.5, "Computer Science");
            String gson = jsonMapper.writeValueAsString(s);
            System.out.println("Serialized JSON 2: " + gson);
            // Output: {"name":"Alice","age":22,"average":16.5,"major":"Computer Science"}

            // ----- 2. JSON String -> Java object (deserialization) -----
            // Jackson calls the no-arg constructor, then uses setters to fill fields.

            Student parsed = mapper.readValue(json, Student.class);
            System.out.println("Parsed object:   " + parsed);

            Student fromJson = jsonMapper.readValue(gson, student.getClass());
            System.out.println("Parsed object 2 :   " + fromJson);

            // ----- 3. Java object -> JSON file -----
            // writeValue creates (or overwrites) the file with the JSON content.
            File file = new File("student.json");
            mapper.writeValue(file, student);
            System.out.println("Written to:      " + file.getAbsolutePath());

            File f = new File("s.json");
            mapper.writeValue(f, s);
            System.out.println("Written to 2:      " + f.getAbsolutePath());

            // ----- 4. JSON file -> Java object -----
            // Symmetric to the previous call: rebuilds a Student from the file.
            Student fromFile = mapper.readValue(file, Student.class);
            System.out.println("Read from file:  " + fromFile);

            Student fromF = mapper.readValue(f, student.getClass());

            System.out.println("Read from file 2 :  " + fromF);

            // ----- 5. Pretty-printed JSON (human readable) -----
            // Useful when the JSON file is meant to be read or edited by hand.
            String prettyJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(student);
            System.out.println("Pretty JSON:\n" + prettyJson);

            String prettyGson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
            System.out.println("Pretty JSON 2:\n" + prettyGson);

        } catch (JsonProcessingException e) {
            // Thrown when serialization/deserialization fails (bad JSON, type mismatch...).
            System.err.println("JSON error: " + e.getMessage());
        } catch (IOException e) {
            // Thrown when the underlying file cannot be read or written.
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
