package com.example.athena.core.entity;

import com.example.athena.core.entity.enums.UserRole;
import com.example.athena.core.entity.interfaces.CreditAssignable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@DiscriminatorValue("INTERN")
@Getter
@Setter
public class Intern extends User implements CreditAssignable {

    private Integer credit;

    public Intern(
            String name,
            String lastName,
            String password,
            String email
    ){
        super(name, lastName, password, UserRole.INTERN, email);
        this.credit = 0;
    }

    @Override
    public void assignCredit(Integer credit) {
        this.setCredit(credit);
    }
}
