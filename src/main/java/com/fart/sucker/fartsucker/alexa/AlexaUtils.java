package com.fart.sucker.fartsucker.alexa;

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioItem;
import com.amazon.speech.speechlet.interfaces.audioplayer.PlayBehavior;
import com.amazon.speech.speechlet.interfaces.audioplayer.Stream;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.PlayDirective;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.StopDirective;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.StandardCard;

import java.util.ArrayList;

final class AlexaUtils {
    private static final String SESSION_CONVERSATION_FLAG = "conversation";
    private static final String REPROMPT_TEXT = "What else can I tell you?  Say \"Help\" for some suggestions. Or say \"Quit\"";
    static final String SAMPLES_HELP_TEXT = "Here are some things you can say: Inhale a fart, or, Play a Toot";

    private AlexaUtils() {}

    static Card newCard( String cardTitle, String cardText ) {
        StandardCard card = new StandardCard();
        card.setTitle(cardTitle);
        card.setText(cardText);
        return card;
    }

    static PlainTextOutputSpeech newSpeech( String speechText, boolean appendRepromptText ) {

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText( appendRepromptText ? speechText + "\n\n" + AlexaUtils.REPROMPT_TEXT : speechText);

        return speech;
    }

    static SpeechletResponse newSpeechletResponse(Card card, PlainTextOutputSpeech speech, Session session)  {

        if (AlexaUtils.inConversationMode(session)) {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(AlexaUtils.REPROMPT_TEXT);

            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt, card);
        }
        else {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }

    static boolean inConversationMode(Session session) {
        return session.getAttribute(SESSION_CONVERSATION_FLAG) != null;
    }

    static void setConversationMode(Session session) {
        session.setAttribute(SESSION_CONVERSATION_FLAG, "true");
    }

    static SpeechletResponse getStopResponse() {
        StopDirective directive = new StopDirective();
        SpeechletResponse response = new SpeechletResponse();
        response.setNullableShouldEndSession(true);
        response.setDirectives(new ArrayList<>());
        response.getDirectives().add(directive);
        return response;
    }

    static SpeechletResponse getStartResponse(String url) {
        SpeechletResponse response = new SpeechletResponse();
        response.setNullableShouldEndSession(true);
        response.setDirectives(new ArrayList<>());
        response.getDirectives().add(getPlayDirective(url));
        return response;
    }

    private static Directive getPlayDirective(String url) {
        Stream stream = new Stream();
        stream.setToken("toot-sound");
        stream.setUrl(url);

        AudioItem audioItem = new AudioItem();
        audioItem.setStream(stream);

        PlayDirective directive = new PlayDirective();
        directive.setPlayBehavior(PlayBehavior.REPLACE_ALL);
        directive.setAudioItem(audioItem);
        return directive;
    }
}
