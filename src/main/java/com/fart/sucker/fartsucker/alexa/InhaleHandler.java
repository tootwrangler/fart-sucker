package com.fart.sucker.fartsucker.alexa;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.fart.sucker.fartsucker.service.FartSuckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InhaleHandler implements IntentHandler {

    @Autowired
    private FartSuckerService fartSuckerService;

    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
        String fart = fartSuckerService.getRandomFart().getFart();
        Card card = AlexaUtils.newCard("Inhale Fart", fart);
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(fart, AlexaUtils.inConversationMode(session));
        return AlexaUtils.newSpeechletResponse( card, speech, session);
    }

}
