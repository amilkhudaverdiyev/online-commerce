//package com.onlinefoodcommercems.controller.auth;
//import com.onlinefoodcommercems.dto.MessageModel;
//import com.onlinefoodcommercems.storage.UserStorage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/message")
//@RequiredArgsConstructor
//public class MessageController {
//
//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/chat/{to}")
//    public void sendMessage(@DestinationVariable String to, MessageModel message){
//        System.out.println("handling this message: " + message + " to" + to);
//        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
//        if(isExists){
//         simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
//        }
//    }
//}
