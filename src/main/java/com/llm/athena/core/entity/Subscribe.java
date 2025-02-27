package com.llm.athena.core.entity;

import jakarta.persistence.Entity;

@Entity
public class Subscribe extends Publish{

    private User subscribed;

    public Subscribe(){}

    public Subscribe(User author, String description, User subscribed) {
        super(author, description);
        this.subscribed = subscribed;
    }

    public User getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(User subscribed) {
        this.subscribed = subscribed;
    }
}
