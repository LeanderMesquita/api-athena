package com.llm.athena.core.entity;

import jakarta.persistence.Entity;

@Entity
public class News extends Publish{

    private String title;
    private String lead;

    public News(User author, String description, String title, String lead) {
        super(author, description);
        this.title = title;
        this.lead = lead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }
}
