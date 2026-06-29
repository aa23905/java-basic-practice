package day5;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 3, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 6; i++) {
            int orderId = i;
            System.out.println("📦 提交第 " + orderId + " 个订单");

            executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " 开始处理订单 #" + orderId);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()
                            + " ✅ 完成订单 #" + orderId);
                });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("📊 最大并发线程数: " + executor.getLargestPoolSize());
            executor.shutdown();
            System.out.println("\n=== 打烊了，不再接新单 ===");

        }
    }
