package com.llm.athena.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llm.athena.core.entity.enums.JobRole;
import com.llm.athena.core.http.request.LoginRequestDto;
import com.llm.athena.core.http.request.UserCreateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String password;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private String username;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private JobRole role;

    private Long points = 0L;

    @Size(min = 14, max = 18)
    private String cpfCnpj;

    private String jobPosition;
    private String company;
    private LocalDate birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscribe> subscribes;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<News> news;

    public boolean isLoginCorrect(LoginRequestDto loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), getPassword());
    }

    public User (UserCreateRequestDto dto, PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(dto.password());
        this.username = dto.name() + " " + dto.lastName();
        this.email = dto.email();
        this.cpfCnpj = dto.cpfCnpj();
        this.jobPosition = dto.jobPosition();
        this.company = dto.company();
        this.birthdate = dto.birthdate();
        this.role = JobRole.INTERN;
    }

    public User (){}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public JobRole getRole() {
        return role;
    }

    public Long getPoints() {
        return points;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public String getCompany() {
        return company;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<Subscribe> getSubscribes() {
        return subscribes;
    }

    public List<News> getNews() {
        return news;
    }
}
