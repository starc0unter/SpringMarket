package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Message;
import com.geekbrains.geekmarketwinter.entites.OrderLight;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ShopControllerWs {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/addProduct")
    public void receiveEvent(Message event) {
        System.out.println(event);
    }

    public void sendMessage(String destination, OrderLight order) {
        simpMessagingTemplate.convertAndSend(destination, order);
    }
}
