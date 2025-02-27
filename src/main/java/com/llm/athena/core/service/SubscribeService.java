package com.llm.athena.core.service;

import com.llm.athena.core.entity.Subscribe;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.SubscribeCreateRequestDto;
import com.llm.athena.core.http.request.SubscribeUpdateRequestDto;
import com.llm.athena.core.repository.SubscribeRepository;
import com.llm.athena.core.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepository repository;

    @Autowired
    private UserService userService;


    public Page<Subscribe> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Subscribe getById(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Subscribe getByEmail(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void create(SubscribeCreateRequestDto dto, User author){
        Subscribe subscribe = new Subscribe(
                author,
                dto.description(),
                userService.getById(dto.subscribedId())
        );
        repository.save(subscribe);
    }

    public void update(String id, SubscribeUpdateRequestDto dto){
        Subscribe subscribe = getById(id);
        subscribe.setDescription(dto.description());
        subscribe.setSubscribed(userService.getById(dto.subscribedId()));
        repository.save(subscribe);
    }

    public void delete(String id){
        repository.delete(getById(id));
    }
}
