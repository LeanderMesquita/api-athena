package com.llm.athena.core.service;

import com.llm.athena.core.entity.Event;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.EventCreateRequestDto;
import com.llm.athena.core.http.request.EventUpdateRequestDto;
import com.llm.athena.core.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    public Page<Event> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Event getById(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Event getByEmail(String id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void create(EventCreateRequestDto dto, User author){
        LocalDate endDate = dto.endDate().isBefore(LocalDate.now()) ? dto.endDate() : LocalDate.now();

        Event event = new Event(
                author, dto.description(),
                dto.title(),
                dto.rules(),
                endDate
        );
        repository.save(event);
    }

    public void update(String id, EventUpdateRequestDto dto){
        Event event = getById(id);
        LocalDate endDate = dto.endDate().isBefore(LocalDate.now()) ? dto.endDate() : LocalDate.now();

        event.setDescription(dto.description());
        event.setTitle(dto.title());
        event.setRules(dto.rules());
        event.setEndDate(endDate);
        repository.save(event);
    }

    public void delete(String id){
        repository.delete(getById(id));
    }
    
}
