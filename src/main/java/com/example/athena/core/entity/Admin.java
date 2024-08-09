package com.example.athena.core.entity;


import com.example.athena.core.entity.enums.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Getter
public class Admin extends User {
    public Admin(
            String name,
            String lastName,
            String password,
            String email
    ){
        super(name, lastName, password, UserRole.ADMIN, email);
    }

}
