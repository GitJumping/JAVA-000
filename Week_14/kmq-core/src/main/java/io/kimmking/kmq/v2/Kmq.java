package io.kimmking.kmq.v2;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        //this.queue = new LinkedBlockingQueue(capacity);
        this.queue = new KmqMessage[capacity];
    }

    private String topic;

    private int capacity;

    private int offset = 0;

    private AtomicInteger writeIndex = new AtomicInteger(0);

    private KmqMessage[] queue;
    //private LinkedBlockingQueue<KmqMessage> queue;

    //public boolean send(KmqMessage message) {
    //    return queue.offer(message);
    //}
    @SneakyThrows
    public boolean send(KmqMessage message) {
        if (writeIndex.intValue() == capacity) {
            throw new IllegalArgumentException("capacity exceed !");
        }
        queue[writeIndex.intValue()] = message;

        if (new Random().nextBoolean()) {
            Thread.sleep(20);
            System.out.println("模拟消息确认延迟" + "offset:" + offset + "writeIndex:" + writeIndex);
        }

        // 也可以再设计一个方法专门提交offset，判断条件是不大于writeIndex就保存
        offset = writeIndex.intValue();

        // 考虑线程安全也可以用AtomicInteger
        writeIndex.getAndIncrement();
        return true;
    }

    public KmqMessage poll(int readIndex) {
        return queue[readIndex];
    }

    public KmqMessage poll(int readIndex, long timeout) {

        long currentTimeMillis = System.currentTimeMillis();
        // 简单模拟延迟
        while (offset < readIndex) {
            if (System.currentTimeMillis() - currentTimeMillis > timeout) {
                break;
            }
        }
        return poll(readIndex);
    }

}
