package com.fart.sucker.fartsucker.service;

import com.fart.sucker.fartsucker.client.SlackClient;
import com.fart.sucker.fartsucker.dto.FartDto;
import com.fart.sucker.fartsucker.dto.FartRequest;
import com.fart.sucker.fartsucker.entity.FartEntity;
import com.fart.sucker.fartsucker.entity.UserEntity;
import com.fart.sucker.fartsucker.repo.FartRepository;
import com.fart.sucker.fartsucker.repo.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Service
public class FartSuckerService {

    @Autowired
    private FartRepository fartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SlackClient slackClient;

    @Value("${twilio.sid}")
    private String sid;

    @Value("${twilio.auth}")
    private String auth;

    @Value("${twilio.fromNumber}")
    private String fromNumber;

    public FartEntity getRandomFart() {
        return fartRepository.getRandomFart();
    }

    public void inhaleFarts(FartRequest fartRequest) {
        List<FartDto> farts = fartRequest.getEntries();
        List<FartEntity> fartEntities = farts.stream().map(fart -> new FartEntity(fart.getFart())).collect(Collectors.toList());
        fartRepository.saveAll(fartEntities);
    }

    public void sendSlackMessage(String mention) {
        slackClient.sendSlackMessage(getRandomFart().getFart(), mention);
    }

    @Scheduled(cron = "0 0 2 * * *")
    private void sendToots() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        String fart = getRandomFart().getFart();

        Twilio.init(sid, auth);
        userEntities.forEach(user -> Message.creator(
                new com.twilio.type.PhoneNumber(user.getPhoneNumber()),
                new com.twilio.type.PhoneNumber(fromNumber),
                fart)
                .create());
    }
}
