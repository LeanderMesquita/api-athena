package com.example.athena.core.http.controller;

import com.example.athena.core.entity.User;
import com.example.athena.core.entity.enums.UserRole;
import com.example.athena.core.entity.factory.UserFactory;
import com.example.athena.core.http.dto.AuthDTO;
import com.example.athena.core.http.dto.LoginResponseDTO;
import com.example.athena.core.http.dto.RegisterDTO;
import com.example.athena.core.http.service.TokenService;
import com.example.athena.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){

        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());


        User newUser;

        if(EnumSet.of(UserRole.COLLABORATOR).contains(data.role())){
            newUser = UserFactory.createCollaborator(data.name(),data.lastName(),data.email(),encryptedPassword);
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }

        newUser = UserFactory.createIntern(data.name(),data.lastName(),data.email(),encryptedPassword);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/operators")
    public ResponseEntity<Void> registerAdmin(@RequestBody @Valid RegisterDTO data){

        if(!EnumSet.of(UserRole.MANAGER, UserRole.ADMIN).contains(data.role())){
            return ResponseEntity.badRequest().build();
        }

        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        if (EnumSet.of(UserRole.ADMIN).contains(data.role())){
            User admin = UserFactory.createAdmin(data.name(), data.lastName(), data.email(), encryptedPassword);
            userRepository.save(admin);
            return ResponseEntity.ok().build();
        }

        User manager = UserFactory.createManager(data.name(), data.lastName(), data.email(), encryptedPassword);
        userRepository.save(manager);
        return ResponseEntity.ok().build();
    }




}
