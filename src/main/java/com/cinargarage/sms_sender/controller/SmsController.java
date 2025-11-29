package com.cinargarage.sms_sender.controller;

import com.cinargarage.sms_sender.dto.ApiResponse;
import com.cinargarage.sms_sender.dto.SmsRequest;
import com.cinargarage.sms_sender.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sms")
@CrossOrigin(origins = "*")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Void>> sendSms(@Valid @RequestBody SmsRequest request) {
        ApiResponse<Void> response = smsService.sendSms(request.getTo(), request.getMessage());
        return ResponseEntity.ok(response);
    }
}
