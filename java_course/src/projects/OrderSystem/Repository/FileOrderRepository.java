package Repository;

import Domain.Order;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileOrderRepository implements OrderRepository {
    private final Path filePath;

    public FileOrderRepository(String path) {
        this.filePath = Path.of(path);
    }

    @Override
    public void save(Order order) {
        try {
            List<String> lines = Files.exists(filePath)
                    ? new ArrayList<>(Files.readAllLines(filePath))
                    : new ArrayList<>();

            // maintain a JSON array: remove closing ] if exists
            if (!lines.isEmpty() && lines.getLast().trim().equals("]"))
                lines.removeLast();

            if (lines.isEmpty()) {
                lines.add("[");
            } else {
                // add comma after previous entry
                int last = lines.size() - 1;
                lines.set(last, lines.get(last) + ",");
            }

            lines.add(order.toJson());
            lines.add("]");
            Files.write(filePath, lines);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to save order", e);
        }
    }

    @Override
    public List<Order> loadAll() {
        // Raw file read — for display only (no full JSON parser needed)
        if (!Files.exists(filePath))
            return List.of();
        try {
            return List.of(); // simplified: real app would parse JSON
        } catch (Exception e) {
            return List.of();
        }
    }
}