package threads;

class Counter {
    int value = 0;

    public synchronized void increment() {
        value++;

    }

    public synchronized int getValue() {
        return value;
    }
}

public class thread_synchronized {
    public static void main(String[] args) throws InterruptedException {

        Counter c = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                c.increment();
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();

        t1.join(); // wait until t1 finish
        t2.join(); // wait unit t2 finish

        System.out.println(c.getValue());

    }

}
