package com.llm.athena.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "admin_user")
public class Admin extends BaseUser {

    @Size(max = 10)
    private final String credential;

    public Admin(String password, String credential) {
        super(password);
        this.credential = credential;
    }

    public String getCredential() {
        return credential;
    }
}
