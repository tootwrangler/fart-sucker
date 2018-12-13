package com.fart.sucker.fartsucker.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class UserEntity extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
