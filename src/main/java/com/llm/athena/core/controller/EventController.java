package com.llm.athena.core.controller;

import com.llm.athena.core.entity.Event;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.EventCreateRequestDto;
import com.llm.athena.core.http.request.EventUpdateRequestDto;
import com.llm.athena.core.http.response.EventResponseDto;
import com.llm.athena.core.policy.EventPolicy;
import com.llm.athena.core.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish/events")
public class EventController {

    private final EventPolicy policy;
    private final EventService service;

    public EventController(
            EventPolicy policy,
            EventService service
    ) {
        this.policy = policy;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EventResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User authUser
    )
    {
        if (!policy.getAll(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<EventResponseDto> response = service.getAll(pageable).map(EventResponseDto::new);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        Event event = service.getById(id);
        if (!policy.getById(authUser, event)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        EventResponseDto response = new EventResponseDto(event);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody EventCreateRequestDto dto, @AuthenticationPrincipal User authUser)
    {
        if (!policy.create(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
        service.create(dto, authUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody EventUpdateRequestDto dto, @AuthenticationPrincipal User authUser){
        Event event = service.getById(id);
        if (!policy.update(authUser, event)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        Event event = service.getById(id);
        if (!policy.delete(authUser, event)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
