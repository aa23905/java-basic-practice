package day5;


import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(3);
        System.out.println("11111111111111111111111");
        Thread produce = new Thread(() -> {
            System.out.println("22222222222222222222");
            for (int i = 0; i < 10; i++) {
                buffer.produce("生产一个给队列加数据一个" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "生产者");

        Thread consume = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                buffer.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "消费者");
        produce.start();
        consume.start();
    }


}

class Buffer {
    private final Queue<String> queue = new LinkedList<>();
    private final int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void produce(String data) {
        System.out.println("当前queue的尺寸" + queue.size() + "，queue的最大长度" + maxSize);
        while (queue.size() == maxSize) {
            System.out.println("第一次什么时候进来的？");
            try {
                System.out.println("【缓冲区满了】生产者等待...");
                wait();  // 释放锁，进入等待状态
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(data);
        System.out.println("for循环给队列第+" + data+1 + "个子弹");
        System.out.println("✅ 生产：" + data + "，当前数量：" + queue.size());
        notify();  // 唤醒消费者（如果有在等的）
    }

    public synchronized String consume() {
        System.out.println("消费者里面队列的长度是" + queue.size());
        while (queue.isEmpty()) {
            try {
                System.out.println("【缓冲区空了】消费者等待...");
                System.out.println("消费者要释放锁了");
                wait();  // 释放锁，进入等待状态
                System.out.println("消费者被唤醒了========");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String data = queue.poll();
        System.out.println("✅ 消费：" + data + "，当前数量：" + queue.size());
        notify();  // 唤醒生产者（如果有在等的）
        return data;
    }

}