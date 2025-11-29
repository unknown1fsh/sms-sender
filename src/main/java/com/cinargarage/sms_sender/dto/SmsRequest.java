package com.cinargarage.sms_sender.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SmsRequest {

    @NotBlank(message = "Telefon numarası boş olamaz")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Geçerli bir telefon numarası formatı giriniz (örn: +905551234567)")
    private String to;

    @NotBlank(message = "Mesaj içeriği boş olamaz")
    @Size(max = 1600, message = "Mesaj en fazla 1600 karakter olabilir")
    private String message;

    // Constructors
    public SmsRequest() {
    }

    public SmsRequest(String to, String message) {
        this.to = to;
        this.message = message;
    }

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

