package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.entity.EmailRequest;
import com.example.EcoConsciousApp.service.impl.EmailServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailServicelmpl emailService;

    //this api send email with html content
    @PostMapping("/sendemailhtml")
    public ResponseEntity<?> sendEmailHtml(@RequestBody EmailRequest request) {
        System.out.println(request);
        boolean result = this.emailService.sendHtmlTemplate(request.getSubject(), request.getMessage(), request.getTo());

        if (result) {
            return ResponseEntity.ok("Sent Email With HTML template style Successfully... ");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("html template style email sending fail");
        }
    }
}

