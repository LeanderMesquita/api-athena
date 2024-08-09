package com.example.athena.core.entity;


import com.example.athena.core.entity.enums.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MANAGER")
@Getter
public class Manager extends User{
    public Manager(
            String name,
            String lastName,
            String password,
            String email
    ){
        super(name, lastName, password, UserRole.MANAGER, email);
    }
}
