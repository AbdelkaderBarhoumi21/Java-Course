package files;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates how to read from and write to files using the modern
 * java.nio.file API (Path, Files, StandardOpenOption).
 *
 * Covers:
 * - writing a String to a file (with CREATE + APPEND options)
 * - reading the full content as a single String
 * - reading line by line into a List
 * - reading lazily with a Stream (memory-efficient for large files)
 */
public class read_write_files_examples {
    public static void main(String[] args) {

        // Path representing the target file (relative to the working directory).
        Path file = Path.of("data.txt");

        try {
            // Write text to the file.
            // CREATE -> create the file if it does not exist
            // APPEND -> add content at the end instead of overwriting
            Files.writeString(file, "First line\nsecond line\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            // Read the whole file content as a single String.
            // Convenient for small files; loads everything into memory.
            String content = Files.readString(file);
            System.out.println(content);

            // Read the file line by line into a List<String>.
            // Each element is one line, without the trailing newline.
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                System.out.println(line);
            }

            // Read the file lazily as a Stream<String>.
            // Lines are read on demand, so this works well for large files.
            // try-with-resources ensures the underlying file handle is closed.
            try (Stream<String> stream = Files.lines(file)) {
                stream
                        .filter(l -> l.contains("line"))
                        .map(String::toUpperCase)
                        .forEach(System.out::println);

            } catch (IOException e) {
                System.err.println("We got an IO Stream exception: " + e.getMessage());

            }

        } catch (IOException e) {
            System.err.println("We got an IO exception: " + e.getMessage());
        }
    }

}
