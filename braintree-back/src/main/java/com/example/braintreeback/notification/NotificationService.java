package com.example.braintreeback.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
  public void sendNotification() {
    log.info("Token obtenido con éxito.");
  }
}
