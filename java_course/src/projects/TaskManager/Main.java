package projects.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask(new Task("Fix login bug", "Backend", 9, false));
        manager.addTask(new Task("Write unit tests", "Backend", 7, false));
        manager.addTask(new Task("Design homepage", "Frontend", 8, true));
        manager.addTask(new Task("Update README", "Docs", 3, true));
        manager.addTask(new Task("Deploy to staging", "DevOps", 6, false));
        manager.addTask(new Task("Review PR #42", "Backend", 5, false));

        manager.printReport();

        System.out.println("\n--- Pending tasks (sorted by priority) ---");
        manager.getPending().forEach(t -> System.out.println("[" + t.priority() + "] " + t.title()));
    }
}