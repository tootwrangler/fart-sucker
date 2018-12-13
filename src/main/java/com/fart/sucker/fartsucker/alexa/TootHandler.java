package com.fart.sucker.fartsucker.alexa;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.fart.sucker.fartsucker.repo.S3LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TootHandler implements IntentHandler {

    @Autowired
    private S3LinkRepository s3LinkRepository;

    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
        return AlexaUtils.getStartResponse(s3LinkRepository.getRandomS3Link().getUrl());
    }
}
