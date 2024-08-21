package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
//    @MessageMapping("/private-message")
//    public Message recMessage(@Payload Message message){
//        simpMessagingTemplate.convertAndSendToUser(message.getGetterMail(),"/private",message);
//        addMessageToRoom(message);
//        return message;
//    }

    @MessageMapping("/user/chatroom")
    public String getRealtimeBooking(String id){
        ResponseEntity.ok(appointmentService.findByAppointmentId(Integer.parseInt(id)));
        simpMessagingTemplate.convertAndSend("/chatroom",appointmentService.findByAppointmentId(Integer.parseInt(id)));
        System.out.println("i got"+ id);
        return id;
    }
}
