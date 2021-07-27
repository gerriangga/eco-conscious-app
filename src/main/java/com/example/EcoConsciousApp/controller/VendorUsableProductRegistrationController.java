package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistration;
import com.example.EcoConsciousApp.service.VendorUsableProductRegistrationService;
import com.example.EcoConsciousApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendorUsableProductRegistrationController {
    @Autowired
    VendorUsableProductRegistrationService registrationService;

    @PostMapping("/registration_transactions")
    public ResponseEntity<Response<VendorUsableProductRegistration>> registration
            (@RequestBody VendorUsableProductRegistration registration){
        String message = String.format(ResponseMessage.DATA_INSERTED, "registration");
        Response<VendorUsableProductRegistration> response = new Response<>();
        response.setMessage(message);
        response.setData(registrationService.transaction(registration));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }


    @GetMapping("/registration_transactions/{registrationId}")
    public VendorUsableProductRegistration getRegistrationById(@PathVariable String registrationId){
        return registrationService.getTransactionByiD(registrationId);
    }
}
