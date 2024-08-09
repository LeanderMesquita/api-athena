package com.example.athena.core.entity.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Administrador"),
    MANAGER("Gerente"),
    COLLABORATOR("Colaborador"),
    INTERN("Estagiário");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

}


