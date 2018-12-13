package com.fart.sucker.fartsucker.alexa;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.interfaces.playbackcontroller.PlaybackController;
import com.amazon.speech.speechlet.interfaces.playbackcontroller.request.NextCommandIssuedRequest;
import com.amazon.speech.speechlet.interfaces.playbackcontroller.request.PauseCommandIssuedRequest;
import com.amazon.speech.speechlet.interfaces.playbackcontroller.request.PlayCommandIssuedRequest;
import com.amazon.speech.speechlet.interfaces.playbackcontroller.request.PreviousCommandIssuedRequest;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerSpeechlet implements SpeechletV2, PlaybackController {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        Session session = requestEnvelope.getSession();
        AlexaUtils.setConversationMode(session);

        String speechText = "Hello. My belly is full of nasty farts. " + AlexaUtils.SAMPLES_HELP_TEXT;

        Card card = AlexaUtils.newCard("Welcome!", speechText);
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(speechText, false);

        return AlexaUtils.newSpeechletResponse(card, speech, session);
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        IntentRequest request = requestEnvelope.getRequest();
        Session session = requestEnvelope.getSession();

        Intent intent = request.getIntent();
        if ( intent != null ) {
            String intentName = intent.getName();
            String handlerBeanName = intentName + "Handler";

            handlerBeanName = StringUtils.replace(handlerBeanName, "AMAZON.", "Amazon");
            handlerBeanName = handlerBeanName.substring(0, 1).toLowerCase() + handlerBeanName.substring(1);

            try {
                Object handlerBean = beanFactory.getBean(handlerBeanName);

                if ( handlerBean instanceof IntentHandler ) {
                    IntentHandler intentHandler = (IntentHandler) handlerBean;
                    return intentHandler.handleIntent(intent, request, session);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        AlexaUtils.setConversationMode(session);

        String errorText = "I don't know what that means. " + AlexaUtils.SAMPLES_HELP_TEXT;
        Card card = AlexaUtils.newCard("Ruh roh", errorText);
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(errorText, false);

        return AlexaUtils.newSpeechletResponse(card, speech, session);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
    }

    @Override
    public SpeechletResponse onPauseCommandIssued(SpeechletRequestEnvelope<PauseCommandIssuedRequest> requestEnvelope) {
        return AlexaUtils.getStopResponse();
    }
    @Override
    public SpeechletResponse onPlayCommandIssued(SpeechletRequestEnvelope<PlayCommandIssuedRequest> requestEnvelope) {
        return AlexaUtils.getStartResponse(null);
    }
    @Override
    public SpeechletResponse onNextCommandIssued(SpeechletRequestEnvelope<NextCommandIssuedRequest> requestEnvelope) {
        return AlexaUtils.getStartResponse(null);
    }
    @Override
    public SpeechletResponse onPreviousCommandIssued(SpeechletRequestEnvelope<PreviousCommandIssuedRequest> requestEnvelope) {
        return AlexaUtils.getStartResponse(null);
    }
}
