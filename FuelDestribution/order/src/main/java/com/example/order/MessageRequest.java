package com.example.order;

import lombok.Setter;

@Setter
public class MessageRequest {

    String message ;

//    public MessageRequest() {
//    }

//    public MessageRequest(String message) {
//        this.message = message;
//    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
