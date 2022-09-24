package com.tamerb.jdbc.transaction;

import lombok.Data;

@Data // used to for getter && setters
public class Actor {

    private String firstName;
    private String lastName;

    public Actor(){}

    public  Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
