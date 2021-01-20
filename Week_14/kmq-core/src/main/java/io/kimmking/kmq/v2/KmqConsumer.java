package io.kimmking.kmq.v2;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    private int readIndex;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(long timeout) {
        KmqMessage kmqMessage = kmq.poll(readIndex, timeout);
        if (kmqMessage != null) {
            readIndex++;
        }
        return kmqMessage;
    }

}
