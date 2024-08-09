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
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    private String email;

    @Setter
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updated_at;

    public User (String name, String lastName, String password, UserRole role, String email){
        this.password = password;
        this.role = role;
        this.username = name+" "+lastName;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return switch (this.role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER")
            );
            case COLLABORATOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR")
            );
            case INTERN -> List.of(
                    new SimpleGrantedAuthority("ROLE_INTERN")
            );
        };
    }

}
