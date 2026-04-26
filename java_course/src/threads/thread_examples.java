package threads;

class Counter {
    int counter = 0;

    void increment() {
        counter++;
    }
}

public class thread_examples {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++)
                c.increment();

        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++)
                c.increment();
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(c.counter);
    }

}
