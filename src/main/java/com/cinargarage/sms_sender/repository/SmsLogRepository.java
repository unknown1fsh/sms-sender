package com.cinargarage.sms_sender.repository;

import com.cinargarage.sms_sender.model.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsLogRepository extends JpaRepository<SmsLog, Long> {
}

