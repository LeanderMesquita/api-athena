package com.example.athena.core.http.service;

import com.example.athena.core.entity.User;
import com.example.athena.core.entity.interfaces.CreditAssignable;
import com.example.athena.core.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @Autowired
    UserRepository userRepository;


    public User sendCredit(String id, Integer credit){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(user instanceof CreditAssignable){
            ((CreditAssignable) user).assignCredit(credit);
            return userRepository.save(user);
        }

        throw new UnsupportedOperationException("This user type cannot receive credit");

    }
}
