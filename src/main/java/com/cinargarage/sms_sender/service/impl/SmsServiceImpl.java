package com.cinargarage.sms_sender.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.cinargarage.sms_sender.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendSms(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();
    }
}
