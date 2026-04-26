package threads;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Run My thread.......");
        super.run();
    }
}

public class create_thread {
    public static void main(String[] args) {
        Runnable task = () -> {

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();

        // second methdos to create thread

        new MyThread().run();

    }

}
