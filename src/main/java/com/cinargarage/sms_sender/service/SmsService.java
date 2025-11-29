package com.cinargarage.sms_sender.service;

import com.cinargarage.sms_sender.dto.ApiResponse;

public interface SmsService {
    ApiResponse<Void> sendSms(String to, String message);
}
