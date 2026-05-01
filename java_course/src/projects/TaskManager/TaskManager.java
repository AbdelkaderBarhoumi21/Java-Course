package projects.TaskManager;

import java.util.*;
import java.util.stream.*;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    // All pending tasks sorted by priority
    public List<Task> getPending() {
        return tasks.stream().filter(t -> !t.completed()).sorted(Comparator.comparingInt(Task::priority).reversed())
                .collect(Collectors.toList());

    }

    // task grouped by category
    public Map<String, List<Task>> groupByCategory() {
        return tasks.stream().collect(Collectors.groupingBy(Task::category));
    }

    // How many tasks are completed4
    public long countCompleted() {
        return tasks.stream().filter(Task::completed).count();
    }
    // Find highest priority task

    public Optional<Task> highestPriority() {
        return tasks.stream().max(Comparator.comparingInt(Task::priority));

    }

    public void printReport() {
        System.out.println("=== Task Report ===");
        System.out.println("Total tasks: " + tasks.size());
        System.out.println("Pending tasks: " + getPending().size());
        System.out.println("Completed tasks: " + countCompleted());
        highestPriority().ifPresentOrElse(
                t -> System.out.println("Top priority   : " + t.title()),
                () -> System.out.println("No tasks found"));

        System.out.println("\n--- By Category ---");
        groupByCategory().forEach((cat, list) -> System.out.println(cat + " → " + list.size() + " tasks"));
        System.out.println("=== End of Report ===");
    }

}
