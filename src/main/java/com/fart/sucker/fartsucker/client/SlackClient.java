package com.fart.sucker.fartsucker.client;

import com.fart.sucker.fartsucker.dto.SlackHookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${slack.url}")
    private String slackUrl;

    public void sendSlackMessage(String message, String mention) {
        restTemplate.exchange(slackUrl, HttpMethod.POST, new HttpEntity<>(new SlackHookRequest(String.format("<@%s>", mention) + message, 0)), String.class);
    }
}
