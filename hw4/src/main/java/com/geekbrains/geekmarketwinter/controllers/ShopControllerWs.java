package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Message;
import com.geekbrains.geekmarketwinter.entites.OrderLight;
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

    public void sendViaWebsocket(OrderLight orderLight) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpMessagingTemplate.convertAndSend("/topic/productAdded", orderLight);
    }
}
