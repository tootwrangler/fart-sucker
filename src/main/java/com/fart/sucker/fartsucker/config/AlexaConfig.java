package com.fart.sucker.fartsucker.config;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.fart.sucker.fartsucker.alexa.HandlerSpeechlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlexaConfig {

    @Autowired
    private HandlerSpeechlet handlerSpeechlet;

    @Bean
    public ServletRegistrationBean registerSpeechletServlet() {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(handlerSpeechlet);
        return new ServletRegistrationBean<>(speechletServlet, "/alexa");
    }

}
