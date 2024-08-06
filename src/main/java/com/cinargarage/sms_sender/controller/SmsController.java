package com.cinargarage.sms_sender.controller;

import com.cinargarage.sms_sender.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        smsService.sendSms(to, message);
        return "SMS başarıyla gönderildi!";
    }
}
