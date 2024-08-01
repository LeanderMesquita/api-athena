package com.example.athena.core.entity;


import com.example.athena.core.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    private String email;

    @Setter
    private String username;
    private String password;
    private UserRole userRole;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updated_at;

    public User (String name, String lastName, String password, UserRole role, String email){
        this.password = password;
        this.userRole = role;
        this.username = name+" "+lastName;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return switch (this.userRole) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR"),
                    new SimpleGrantedAuthority("ROLE_INTERN")
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR"),
                    new SimpleGrantedAuthority("ROLE_INTERN")
            );
            case COLLABORATOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR"),
                    new SimpleGrantedAuthority("ROLE_INTERN")
            );
            case INTERN -> List.of(
                    new SimpleGrantedAuthority("ROLE_INTERN")
            );
        };
    }

}
