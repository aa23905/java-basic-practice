package day5;

public class SynchronizedDemo {
    public static void main(String[] args) {
        System.out.println("===== 没有 synchronized 的情况 =====");
        Counter counter = new Counter();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> counter.increment());
            threads[i].start();

        }
        for (int i = 0; i < 1000; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("期望结果：1000");
        System.out.println("实际结果：" + counter.getCount());
        System.out.println("理论上应该是 1000，但没加锁时经常小于 1000\n");


        // ===== 加上 synchronized 试试 =====
        System.out.println("===== 加上 synchronized =====");
        SafeCounter safeCounter = new SafeCounter();

        Thread[] safeThreads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            safeThreads[i] = new Thread(() -> safeCounter.increment());
            safeThreads[i].start();
        }

        for (int i = 0; i < 1000; i++) {
            try {
                safeThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("期望结果：1000");
        System.out.println("实际结果：" + safeCounter.getCount());
        System.out.println("每次都是 1000 ✅");
    }
}


class Counter {
    private int count = 0;

    public void increment() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
    }

    public int getCount() {
        return count;
    }
}