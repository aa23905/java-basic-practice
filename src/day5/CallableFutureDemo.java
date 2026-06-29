package day5;

import java.util.concurrent.*;

public class CallableFutureDemo {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        System.out.println("=== Callable + Future 演示 ===");

        // 1️⃣ Callable：带返回值的任务
        Callable<String> task1 = () -> {
            Thread.sleep(1500);
            return "老板，查询结果：用户张三的余额是 8888 元";
        };

        // 2️⃣ 提交任务，拿到 Future（未来的结果凭证）
        Future<String> future1 = executor.submit(task1);
        System.out.println("📦 任务已提交，main线程继续干别的...");

        // main 线程先干点别的
        for (int i = 0; i < 3; i++) {
            System.out.println("  main 线程正在摸鱼..." + (i + 1) + "秒");
            Thread.sleep(500);
        }

        // 3️⃣ 拿结果：future.get() 会阻塞等待线程完成
        System.out.println("\n🔍 试图获取结果...");
        String result = future1.get();  // 这里会等，直到线程返回
        System.out.println("✅ 拿到结果: " + result);

        // 4️⃣ 多个任务一起跑
        System.out.println("\n=== 多任务并行 ===");
        Future<Integer> f1 = executor.submit(() -> {
            Thread.sleep(2000);
            return 100;
        });
        Future<Integer> f2 = executor.submit(() -> {
            Thread.sleep(3000);
            return 200;
        });
        Future<Integer> f3 = executor.submit(() -> {
            Thread.sleep(1000);
            return 300;
        });

        // 全部提交后再逐个拿结果
        System.out.println("三个任务已提交，等待结果...");
        int total = f1.get() + f2.get() + f3.get();
        System.out.println("✅ 三个任务结果之和: " + total);

        executor.shutdown();
    }
}

