package com.example.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JmsConsumer {
    @JmsListener(destination = "activeTest")
    public void receiveMessage(final Map message) {
        System.out.println(message.toString());
    }
}
