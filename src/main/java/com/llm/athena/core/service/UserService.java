package com.llm.athena.core.service;

import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.UserCreateRequestDto;
import com.llm.athena.core.http.request.UserUpdateRequestDto;
import com.llm.athena.core.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public User getById(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User getByEmail(String email){
        return repository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public void create(UserCreateRequestDto dto){
        User user = new User(dto, passwordEncoder);
        repository.save(user);
    }

    public void update(String id, UserUpdateRequestDto dto){
        User user = getById(id);
        user.setUsername(dto.name() +" "+ dto.lastName());
        user.setCpfCnpj(dto.cpfCnpj());
        user.setJobPosition(dto.jobPosition());
        user.setCompany(dto.company());
        user.setBirthdate(dto.birthdate());

        repository.save(user);
    }

    public void delete(String id){
        repository.delete(getById(id));
    }


}
