package com.fart.sucker.fartsucker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "guid", columnDefinition = "VARCHAR(36)")
    private String guid;

    protected AbstractEntity(String guid) {
        this.guid = guid;
    }

    AbstractEntity() {}

    public String getGuid() {
        return guid;
    }
}
