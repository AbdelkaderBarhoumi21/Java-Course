package files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Demonstrates common file and directory operations using java.nio.file.
 *
 * Covers:
 * - creating directory trees
 * - checking existence and type of a path
 * - copying, moving and deleting files
 * - listing the content of a directory (1 level)
 * - walking a directory tree recursively
 */
public class file_io_exmpales {
    public static void main(String[] args) {

        try {
            // ----- Create directories -----
            // createDirectories creates the whole path, including any missing parent.
            // Equivalent to "mkdir -p" in bash.
            Path folder = Path.of("project/src/files");
            Files.createDirectories(folder);

            // ----- Check existence / type -----
            // exists -> true if the path exists (file or folder)
            // isDirectory -> true only if the path points to a directory
            Path file = Path.of("data.txt");
            boolean fileExists = Files.exists(file);
            boolean isFolder = Files.isDirectory(folder);
            System.out.println("data.txt exists?     " + fileExists);
            System.out.println("folder is a folder?  " + isFolder);

            // ----- Copy a file -----
            // REPLACE_EXISTING allows overwriting the target if it already exists.
            // Without it, a FileAlreadyExistsException would be thrown.
            if (fileExists) {
                Files.copy(file, Path.of("backup.txt"),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            // ----- Move / rename a file -----
            // The destination folder must already exist (created above).
            // move can also be used to rename a file in place.
            if (fileExists) {
                Files.move(file, folder.resolve("data.txt"),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            // ----- Delete a file -----
            // deleteIfExists returns false instead of throwing if the file is absent.
            Files.deleteIfExists(Path.of("backup.txt"));

            // ----- List the content of a directory (1 level only) -----
            // Files.list returns a lazy Stream<Path> of direct entries.
            // try-with-resources is required to release the underlying handle.
            System.out.println("\n--- Content of current directory ---");
            try (Stream<Path> stream = Files.list(Path.of("."))) {
                stream.forEach(System.out::println);
            }

            // ----- Walk a directory tree recursively -----
            // Files.walk visits every file/folder under the root, at any depth.
            // Here we keep only files whose name ends with ".java".
            System.out.println("\n--- Java files under current directory ---");
            try (Stream<Path> stream = Files.walk(Path.of("."))) {
                stream
                        .filter(p -> p.toString().endsWith(".java"))
                        .forEach(System.out::println);
            }

        } catch (IOException e) {
            System.err.println("We got an IO exception: " + e.getMessage());
        }
    }
}