package com.fart.sucker.fartsucker.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "s3links")
@Entity
public class S3LinkEntity extends AbstractEntity {

    private String url;

    public S3LinkEntity(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
