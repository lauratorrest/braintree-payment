package com.example.braintreeback.notification;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificationAspect {
  @Autowired
  private NotificationService notificationService;

  @AfterReturning(value = "@annotation(notificationPushElement)")
  public void sendPush(NotificationPushElement notificationPushElement) {
    notificationService.sendNotification();
  }
}
