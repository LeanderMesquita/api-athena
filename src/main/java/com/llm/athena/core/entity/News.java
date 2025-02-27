package com.llm.athena.core.entity;

import com.llm.athena.core.http.request.NewsCreateRequestDto;
import jakarta.persistence.Entity;

@Entity
public class News extends Publish{

    private String title;
    private String lead;

    public News(User author, String description, String lead, String title) {
        super(author, description);
        this.lead = lead;
        this.title = title;
    }

    public News(){}

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
