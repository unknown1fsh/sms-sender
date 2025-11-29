-- SMS Sender Veritabanı Şeması
-- MySQL 8.0 için oluşturulmuştur

-- Veritabanını oluştur (eğer yoksa)
CREATE DATABASE IF NOT EXISTS sms_sender 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE sms_sender;

-- SMS Logs Tablosu
CREATE TABLE IF NOT EXISTS sms_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    to_phone VARCHAR(20) NOT NULL,
    message TEXT NOT NULL,
    status VARCHAR(20) NOT NULL,
    error_message TEXT,
    created_at DATETIME NOT NULL,
    INDEX idx_to_phone (to_phone),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

