package com.example.DreamCar.email;

public interface EmailSender {
    void send(String to, String email, String subject);
}
