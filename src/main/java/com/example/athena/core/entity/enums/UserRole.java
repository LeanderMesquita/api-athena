package com.example.athena.core.entity.enums;

public enum UserRole {
    ADMIN("admin"),
    MANAGER("manager"),
    COLLABORATOR("collaborator"),
    INTERN("intern");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }}


