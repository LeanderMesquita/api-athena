package com.llm.athena.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llm.athena.core.entity.enums.JobRole;
import com.llm.athena.core.http.request.UserCreateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseUser {

    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private JobRole role = JobRole.EMPLOYEE;

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


    public User (UserCreateRequestDto dto){
        super(dto.password());
        this.username = dto.name() + " " + dto.lastName();
        this.email = dto.email();
        this.cpfCnpj = dto.cpfCnpj();
        this.jobPosition = dto.jobPosition();
        this.company = dto.company();
        this.birthdate = dto.birthdate();
    }

    public User (){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JobRole getRole() {
        return role;
    }

    public void setRole(JobRole role) {
        this.role = role;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
