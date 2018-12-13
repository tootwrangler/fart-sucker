package com.fart.sucker.fartsucker.dto;

import java.util.List;

public class FartRequest {

    private List<FartDto> entries;

    public List<FartDto> getEntries() {
        return entries;
    }

    public void setEntries(List<FartDto> entries) {
        this.entries = entries;
    }
}
