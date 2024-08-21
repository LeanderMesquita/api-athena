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
@DiscriminatorValue("COLLABORATOR")
@Getter
@Setter
public class Collaborator extends User implements CreditAssignable {

    private Integer credit;

    public Collaborator(
            String name,
            String lastName,
            String password,
            String email
    ){
        super(name, lastName, password, UserRole.COLLABORATOR, email);
        this.credit = 0;
    }

    @Override
    public void assignCredit(Integer credit) {
        int finalCredit = (int) (credit * 1.5);
        this.setCredit(finalCredit);
    }
}
