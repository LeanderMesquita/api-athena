package com.llm.athena.core.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class Publish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Publish(User author, String description) {
        this.author = author;
        this.description = description;
    }

    public Publish(){}

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public User getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
