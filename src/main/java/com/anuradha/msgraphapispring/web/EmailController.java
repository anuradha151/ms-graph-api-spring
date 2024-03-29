package com.anuradha.msgraphapispring.web;

import com.anuradha.msgraphapispring.dto.MessageResponseDto;
import com.anuradha.msgraphapispring.model.EmailRequest;
import com.anuradha.msgraphapispring.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {

        log.info("REST API invoked to send email");
        emailService.sendEmail(emailRequest);

        return ResponseEntity.ok("Email sent successfully");
    }


    @GetMapping("/list")
    public ResponseEntity<List<MessageResponseDto>> listMessages() {

        log.info("REST API invoked to list emails");
        List<MessageResponseDto> response = emailService.listMessages();

        return ResponseEntity.ok(response);
    }


}
