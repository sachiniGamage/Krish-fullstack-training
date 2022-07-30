package com.example.kafkaexample;

public class MessageRequest {

    String message;

    public MessageRequest(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
