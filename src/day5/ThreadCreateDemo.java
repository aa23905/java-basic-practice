package day5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreateDemo {
    public static void main(String[] args) {
        System.out.println("=== 方式1：继承 Thread 类 ===");
        // 直接 start() 启动线程
        new MyThread().start();

        System.out.println("=== 方式2：实现 Runnable 接口 ===");
        // 传入 Thread 构造方法
        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        System.out.println("=== 方式3：实现 Callable 接口（带返回值） ===");
        // Callable 需要 FutureTask 包装
        FutureTask<Integer> task = new FutureTask<>(new MyCallable());
        Thread t3 = new Thread(task);
        t3.start();

        try {
            Integer result = task.get(); // 获取线程返回的结果
            System.out.println("Callable 返回结果：" + result);
        } catch (InterruptedException |
                 ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("=== main 方法执行完毕 ===");
    }
}

// 方式1：继承 Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread 运行中，线程名：" + getName());
    }
}

// 方式2：实现 Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable 运行中，线程名：" + Thread.currentThread().getName());
    }
}

// 方式3：实现 Callable（有返回值）
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("MyCallable 运行中，线程名：" + Thread.currentThread().getName());
        return 42; // 返回计算结果
    }
}