package com.llm.athena.core.controller;

import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.UserUpdateRequestDto;
import com.llm.athena.core.http.response.UserResponseDto;
import com.llm.athena.core.policy.UserPolicy;
import com.llm.athena.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserPolicy policy;
    private final UserService service;

    public UserController(
            UserPolicy policy,
            UserService service
    ) {
        this.policy = policy;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal User authUser
    )
    {
        if (!policy.getAll(authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<UserResponseDto> response = service.getAll(pageable).map(UserResponseDto::new);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        User user = service.getById(id);
        if (!policy.getById(user, authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        UserResponseDto response = new UserResponseDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody UserUpdateRequestDto dto, @AuthenticationPrincipal User authUser){
        User user = service.getById(id);
        if (!policy.update(user, authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @AuthenticationPrincipal User authUser)
    {
        User user = service.getById(id);
        if (!policy.delete(user, authUser)){return new ResponseEntity<>(HttpStatus.FORBIDDEN);}

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
