package com.fart.sucker.fartsucker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "farts")
@Entity
public class FartEntity extends AbstractEntity {

    @Column(name = "fart")
    private String fart;

    public FartEntity(String fart) {
        this.fart = fart;
    }

    public FartEntity() {}

    public String getFart() {
        return fart;
    }

    public void setFart(String fart) {
        this.fart = fart;
    }
}
