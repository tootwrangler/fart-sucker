package com.fart.sucker.fartsucker.controller;

import com.fart.sucker.fartsucker.dto.TranscodeNotification;
import com.fart.sucker.fartsucker.entity.S3LinkEntity;
import com.fart.sucker.fartsucker.repo.S3LinkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/${root-path}/complete")
public class SNSController {

    @Autowired
    private S3LinkRepository s3LinkRepository;

    @Value("${amazon.s3.bucket.url}")
    private String bucketUrl;

    @NotificationSubscriptionMapping
    public void confirmUnsubscribeMessage(
            NotificationStatus notificationStatus) {
        notificationStatus.confirmSubscription();
    }

    @NotificationMessageMapping
    public void receiveNotification(@NotificationMessage String message) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TranscodeNotification transcodeNotification = objectMapper.readValue(message, TranscodeNotification.class);
            if ("COMPLETED".equals(transcodeNotification.getState())) {
                s3LinkRepository.save(new S3LinkEntity(String.format("%s/%s", bucketUrl, transcodeNotification.getOutputs().get(0).getKey())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotificationUnsubscribeConfirmationMapping
    public void confirmSubscriptionMessage(
            NotificationStatus notificationStatus) {
        notificationStatus.confirmSubscription();
    }
}
