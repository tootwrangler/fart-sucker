package com.fart.sucker.fartsucker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackHookRequest {

    private String text;

    @JsonProperty("link_names")
    private int link_names;

    public SlackHookRequest(String text, int linkNames) {
        this.text = text;
        this.link_names = linkNames;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLinkNames() {
        return link_names;
    }

    public void setLinkNames(int linkNames) {
        this.link_names = linkNames;
    }
}
