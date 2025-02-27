package com.llm.athena.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Event extends Publish{

    private String title;
    @Column(columnDefinition = "TEXT")
    private String rules;
    private LocalDate endDate;

    public Event(User author, String description, String title, String rules, LocalDate endDate) {
        super(author, description);
        this.title = title;
        this.rules = rules;
        this.endDate = endDate;
    }

    public Event(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
