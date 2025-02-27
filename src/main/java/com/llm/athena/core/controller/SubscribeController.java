package com.llm.athena.core.controller;

import com.llm.athena.core.entity.Subscribe;
import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.SubscribeCreateRequestDto;
import com.llm.athena.core.http.request.SubscribeUpdateRequestDto;
import com.llm.athena.core.http.response.SubscribeResponseDto;
import com.llm.athena.core.policy.SubscribePolicy;
import com.llm.athena.core.service.SubscribeService;
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
@RequestMapping("/publish/subscribes")
public class SubscribeController {

    private final SubscribePolicy policy;
    private final SubscribeService service;

    public SubscribeController(
            SubscribePolicy policy,
            SubscribeService service
    ) {
        this.policy = policy;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<SubscribeResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User authUser
    )
    {
        if (!policy.getAll(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<SubscribeResponseDto> response = service.getAll(pageable).map(SubscribeResponseDto::new);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscribeResponseDto> getById(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        Subscribe subscribe = service.getById(id);
        if (!policy.getById(authUser, subscribe)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        SubscribeResponseDto response = new SubscribeResponseDto(subscribe);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SubscribeCreateRequestDto dto, @AuthenticationPrincipal User authUser)
    {
        if (!policy.create(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
        service.create(dto, authUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody SubscribeUpdateRequestDto dto, @AuthenticationPrincipal User authUser){
        Subscribe subscribe = service.getById(id);
        if (!policy.update(authUser, subscribe)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        Subscribe subscribe = service.getById(id);
        if (!policy.delete(authUser, subscribe)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
