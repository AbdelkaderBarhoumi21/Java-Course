package projects.TaskManagerSystem;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum TaskPriority {
    LOW, MEDIUM, HIGH
}

enum TaskStatus {
    TODO, IN_PROGRESS, DONE
}

record Task(int id, String title, TaskPriority taskPriority, TaskStatus taskStatus, int dayDue) {
}

// --- Generic, reusable repository ---

class Repository<T> {
    private final List<T> items = new ArrayList<T>();

    void add(T item) {
        items.add(item);
    }

    List<T> findAll() {
        return List.copyOf(items);
    }

    // higher-order function
    List<T> findWhere(Predicate<T> predicate) {
        return items.stream().filter(predicate).toList();
    }
}

class TaskAnalytics {
    private final List<Task> tasks;

    TaskAnalytics(List<Task> tasks) {
        this.tasks = tasks;
    }

    Map<TaskStatus, List<Task>> groupByStatus() {
        return tasks.stream().collect(Collectors.groupingBy(Task::taskStatus));
    }

    Map<TaskPriority, Long> groupByPriority() {
        return tasks.stream().collect(Collectors.groupingBy(Task::taskPriority, Collectors.counting()));
    }

    List<Task> overdue(int today) {
        return tasks.stream().filter(t -> t.taskStatus() != TaskStatus.DONE && t.dayDue() < today)
                .sorted(Comparator.comparingInt(Task::dayDue)).toList();
    }

    double completionRate() {
        if (tasks.isEmpty())
            return 0.0;
        long done = tasks.stream().filter(t -> t.taskStatus() == TaskStatus.DONE).count();
        return (double) done / tasks.size() * 100;
    }

}

public class TaskManagerSystem {

    public static void main(String[] args) {
        var repo = new Repository<Task>();
        repo.add(new Task(1, "Write report", TaskPriority.HIGH, TaskStatus.TODO, 5));
        repo.add(new Task(2, "Email client", TaskPriority.MEDIUM, TaskStatus.DONE, 2));
        repo.add(new Task(3, "Fix bug", TaskPriority.HIGH, TaskStatus.IN_PROGRESS, 1));
        repo.add(new Task(4, "Tidy desk", TaskPriority.LOW, TaskStatus.DONE, 10));
        List<Task> allTasks = repo.findAll();
        System.out.println("All tasks:");
        allTasks.forEach(System.out::println);
        var analytics = new TaskAnalytics(allTasks);

        System.out.println("By status: " + analytics.groupByStatus().keySet());
        System.out.println("Counts:    " + analytics.groupByPriority());
        System.out.println("Overdue:   " + analytics.overdue(3)); // task 3
        System.out.printf("Done: %.1f%%%n", analytics.completionRate()); // 50.0%

        var highPriorityTasks = repo.findWhere(t -> t.taskPriority() == TaskPriority.HIGH);
        System.out.println("High priority: " + highPriorityTasks.size() + " tasks");

    }

}
