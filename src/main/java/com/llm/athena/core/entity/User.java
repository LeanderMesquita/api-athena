package com.llm.athena.core.entity;

import com.llm.athena.core.entity.enums.JobRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseUser {

    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private JobRole role;

    private Long points;

    @Size(min = 14, max = 18)
    private String cpfCnpj;

    private String jobPosition;
    private String company;
    private LocalDate birthdate;

    public User
    (
            String password,
            String name,
            String lastName,
            String email,
            JobRole role,
            Long points,
            String cpfCnpj,
            String jobPosition,
            String company,
            LocalDate birthdate
    ) {
        super(password);
        this.username = name + " " + lastName;
        this.email = email;
        this.role = role;
        this.points = points;
        this.cpfCnpj = cpfCnpj;
        this.jobPosition = jobPosition;
        this.company = company;
        this.birthdate = birthdate;
    }

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
