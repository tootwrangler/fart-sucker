package com.fart.sucker.fartsucker.alexa;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.springframework.stereotype.Component;

@Component
public class AmazonStopIntentHandler implements IntentHandler {

    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
        return AlexaUtils.getStopResponse();
    }
}
