package com.fart.sucker.fartsucker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranscodeNotification {

    private String state;

    private List<Output> outputs;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
}
