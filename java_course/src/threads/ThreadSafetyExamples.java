package threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates thread safety in Java.
 *
 * Covers four scenarios, side by side:
 *   1. Race condition  -> shared int + no protection (BUG)
 *   2. synchronized    -> simple lock, one thread at a time (FIX 1)
 *   3. AtomicInteger   -> lock-free atomic CPU operation   (FIX 2)
 *   4. Thread-safe collections (ConcurrentHashMap, CopyOnWriteArrayList)
 *
 * Run multiple times: scenario #1 will give a DIFFERENT, WRONG result each time,
 * while scenarios #2 and #3 always give the expected value.
 */
public class thread_safety_examples {

    // Number of threads and increments per thread.
    // 2 threads * 100_000 = 200_000 expected increments.
    private static final int THREADS = 2;
    private static final int ITERATIONS = 100_000;

    public static void main(String[] args) throws InterruptedException {

        // ------------------------------------------------------------------
        // 1) RACE CONDITION — plain int, no protection
        // ------------------------------------------------------------------
        // value++ is NOT atomic: it is "read, add 1, write".
        // Two threads can read the same value, both add 1, and both write back
        // -> one increment is lost (this is called a "lost update").
        UnsafeCounter unsafe = new UnsafeCounter();
        runWithThreads(() -> {
            for (int i = 0; i < ITERATIONS; i++) unsafe.increment();
        });
        System.out.println("1) Unsafe counter        : " + unsafe.value
                + "   (expected " + (THREADS * ITERATIONS) + ")  <-- usually WRONG");

        // ------------------------------------------------------------------
        // 2) synchronized — simple lock
        // ------------------------------------------------------------------
        // The "synchronized" keyword guarantees that only ONE thread at a time
        // can be inside the method. The 3 steps of value++ become atomic.
        // Trade-off: slower, because other threads must wait their turn.
        SyncCounter sync = new SyncCounter();
        runWithThreads(() -> {
            for (int i = 0; i < ITERATIONS; i++) sync.increment();
        });
        System.out.println("2) Synchronized counter  : " + sync.getValue()
                + "   (expected " + (THREADS * ITERATIONS) + ")  <-- always correct");

        // ------------------------------------------------------------------
        // 3) AtomicInteger — lock-free, CPU-level atomic operation
        // ------------------------------------------------------------------
        // AtomicInteger uses a special CPU instruction (Compare-And-Swap)
        // to update the value in ONE indivisible step. No lock, no waiting.
        // Faster than "synchronized" for simple counters/flags.
        AtomicInteger atomic = new AtomicInteger(0);
        runWithThreads(() -> {
            for (int i = 0; i < ITERATIONS; i++) atomic.incrementAndGet();
        });
        System.out.println("3) AtomicInteger counter : " + atomic.get()
                + "   (expected " + (THREADS * ITERATIONS) + ")  <-- always correct");

        // ------------------------------------------------------------------
        // 4) Thread-safe collections
        // ------------------------------------------------------------------
        // HashMap and ArrayList are NOT thread-safe: concurrent writes can
        // corrupt their internal structure (lost entries, infinite loops...).
        // Their thread-safe siblings are ConcurrentHashMap and
        // CopyOnWriteArrayList.

        // -- 4a) ConcurrentHashMap --------------------------------------------
        // Internally splits the map into segments and locks only the segment
        // being modified. Multiple threads can write to different keys in
        // parallel. Much faster than synchronizing the whole map.
        Map<String, Integer> safeMap = new ConcurrentHashMap<>();
        runWithThreads(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                // merge: if key absent -> put 1, else apply function (old + 1).
                // The whole "read-modify-write" is atomic per key.
                safeMap.merge("hits", 1, Integer::sum);
            }
        });
        System.out.println("4a) ConcurrentHashMap    : " + safeMap.get("hits")
                + "   (expected " + (THREADS * ITERATIONS) + ")  <-- always correct");

        // For comparison: a regular HashMap would be UNSAFE here.
        // Uncomment to see corrupted results / exceptions:
        Map<String, Integer> unsafeMap = new HashMap<>();
        runWithThreads(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                unsafeMap.merge("hits", 1, Integer::sum);
            }
        });
        System.out.println("   (unsafe HashMap demo  : " + unsafeMap.get("hits") + ")");

        // -- 4b) CopyOnWriteArrayList -----------------------------------------
        // Every write creates a brand new internal array (expensive!).
        // Reads are lock-free and very fast.
        // Best for "many reads, few writes" use cases (e.g. listener lists).
        List<Integer> safeList = new CopyOnWriteArrayList<>();
        runWithThreads(() -> {
            for (int i = 0; i < 1000; i++) safeList.add(i);
        });
        System.out.println("4b) CopyOnWriteArrayList : size = " + safeList.size()
                + "   (expected " + (THREADS * 1000) + ")  <-- always correct");

        // For comparison: a regular ArrayList would lose entries or throw.
        List<Integer> unsafeList = new ArrayList<>();
        runWithThreads(() -> {
            for (int i = 0; i < 1000; i++) unsafeList.add(i);
        });
        System.out.println("   (unsafe ArrayList demo: size = " + unsafeList.size() + ")");
    }

    /**
     * Runs the given task on THREADS threads in parallel and waits for all
     * of them to finish (via join). Encapsulates the boilerplate so the
     * main method stays focused on the actual concept being demonstrated.
     */
    private static void runWithThreads(Runnable task) throws InterruptedException {
        Thread[] pool = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            pool[i] = new Thread(task, "worker-" + i);
            pool[i].start();
        }
        // join() makes the main thread wait until each worker has finished.
        for (Thread t : pool) t.join();
    }

    /** Plain counter — NOT thread-safe (used to demonstrate the bug). */
    static class UnsafeCounter {
        int value = 0;

        void increment() {
            // value++ is actually 3 operations: read, add 1, write.
            // Without synchronization, two threads can interleave and lose updates.
            value++;
        }
    }

    /** Counter protected by "synchronized" — thread-safe. */
    static class SyncCounter {
        private int value = 0;

        // Only one thread at a time can execute this method on a given instance.
        public synchronized void increment() {
            value++;
        }

        // The getter is also synchronized to guarantee the most recent value
        // is visible across threads (memory visibility, not just atomicity).
        public synchronized int getValue() {
            return value;
        }
    }
}
