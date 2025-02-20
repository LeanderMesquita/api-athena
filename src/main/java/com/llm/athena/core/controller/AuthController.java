package com.llm.athena.core.controller;

import com.llm.athena.core.entity.User;
import com.llm.athena.core.http.request.LoginRequestDto;
import com.llm.athena.core.http.request.UserCreateRequestDto;
import com.llm.athena.core.http.response.LoginResponseDto;
import com.llm.athena.core.service.TokenService;
import com.llm.athena.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
        User user = userService.getByEmail(dto.email());

        if (!user.isLoginCorrect(dto, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }

        long expiresIn = 28800L;
        String token = tokenService.generateCommonToken(user, expiresIn);

        LoginResponseDto response = new LoginResponseDto(token, expiresIn);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@Valid @RequestBody UserCreateRequestDto dto)
    {
        userService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
