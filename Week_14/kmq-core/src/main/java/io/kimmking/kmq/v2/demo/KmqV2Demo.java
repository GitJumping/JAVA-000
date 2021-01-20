package io.kimmking.kmq.v2.demo;

import io.kimmking.kmq.core.KmqBroker;
import io.kimmking.kmq.core.KmqConsumer;
import io.kimmking.kmq.core.KmqMessage;
import io.kimmking.kmq.core.KmqProducer;
import lombok.SneakyThrows;

public class KmqV2Demo {

    @SneakyThrows
    public static void main(String[] args) {
        String topic = "kmp array implement test";
        KmqBroker broker = new KmqBroker();
        broker.createTopic(topic);

        final boolean[] flag = new boolean[1];
        flag[0] = true;
        for (int i = 0; i < 10; i++) {
            final int threadId = i + 1;
            KmqConsumer consumer = broker.createConsumer();
            consumer.subscribe(topic);
            new Thread(() -> {
                while (flag[0]) {
                    KmqMessage<Order> message = consumer.poll(100);
                    if (null != message) {
                        System.out.println("thread-" + threadId + ":" + message.getBody());
                    }
                }
                System.out.println("thread-" + threadId + ":" + " program exit。");
            }).start();
        }

        KmqProducer producer = broker.createProducer();
        for (int i = 0; i < 100; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new KmqMessage(null, order));
        }
        Thread.sleep(500);
        System.out.println("press any key, or send a message; click q or e, exit program. ");
        while (true) {
            char c = (char) System.in.read();
            if (c > 20) {
                System.out.println(c);
                producer.send(topic, new KmqMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if (c == 'q' || c == 'e') {
                break;
            }
        }

        flag[0] = false;
    }
}
