package com.cinargarage.sms_sender.service.impl;

import com.cinargarage.sms_sender.dto.ApiResponse;
import com.cinargarage.sms_sender.exception.SmsException;
import com.cinargarage.sms_sender.model.SmsLog;
import com.cinargarage.sms_sender.repository.SmsLogRepository;
import com.cinargarage.sms_sender.service.SmsService;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.PostConstruct;

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @Autowired
    private SmsLogRepository smsLogRepository;

    @PostConstruct
    public void init() {
        if (accountSid == null || accountSid.isEmpty() || 
            authToken == null || authToken.isEmpty() || 
            fromPhoneNumber == null || fromPhoneNumber.isEmpty()) {
            logger.warn("Twilio credentials are not configured. SMS sending will fail.");
        } else {
            try {
                Twilio.init(accountSid, authToken);
                logger.info("Twilio initialized successfully");
            } catch (Exception e) {
                logger.error("Failed to initialize Twilio: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    @Transactional
    public ApiResponse<Void> sendSms(String to, String message) {
        logger.info("Attempting to send SMS to: {}", to);
        
        // Validate Twilio configuration
        if (accountSid == null || accountSid.isEmpty() || 
            authToken == null || authToken.isEmpty() || 
            fromPhoneNumber == null || fromPhoneNumber.isEmpty()) {
            String errorMsg = "Twilio credentials are not configured";
            logger.error(errorMsg);
            saveLog(to, message, "FAILED", errorMsg);
            throw new SmsException(errorMsg);
        }

        try {
            // Create and send SMS via Twilio
            Message twilioMessage = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(fromPhoneNumber),
                    message
            ).create();

            String status = twilioMessage.getStatus().toString();
            logger.info("SMS sent successfully. Status: {}, SID: {}", status, twilioMessage.getSid());
            
            // Save successful log
            saveLog(to, message, "SENT", null);
            
            return ApiResponse.success("SMS başarıyla gönderildi!");
            
        } catch (TwilioException e) {
            String errorMsg = "Twilio hatası: " + e.getMessage();
            logger.error("Failed to send SMS: {}", errorMsg, e);
            
            // Save failed log
            saveLog(to, message, "FAILED", errorMsg);
            
            throw new SmsException(errorMsg, e);
            
        } catch (Exception e) {
            String errorMsg = "Beklenmeyen hata: " + e.getMessage();
            logger.error("Unexpected error while sending SMS: {}", errorMsg, e);
            
            // Save failed log
            saveLog(to, message, "FAILED", errorMsg);
            
            throw new SmsException(errorMsg, e);
        }
    }

    private SmsLog saveLog(String to, String message, String status, String errorMessage) {
        try {
            SmsLog smsLog = new SmsLog(to, message, status, errorMessage);
            smsLog = smsLogRepository.save(smsLog);
            logger.debug("SMS log saved with ID: {}", smsLog.getId());
            return smsLog;
        } catch (Exception e) {
            logger.error("Failed to save SMS log: {}", e.getMessage(), e);
            // Don't throw exception, logging failure shouldn't break SMS sending
            return null;
        }
    }
}
