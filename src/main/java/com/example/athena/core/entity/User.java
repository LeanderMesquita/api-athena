package com.example.athena.core.entity;


import com.example.athena.core.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;

    @Setter
    private String name;
    @Setter
    private String lastName;

    private String password;
    private UserRole userRole;
    private Instant created_at;
    private Instant updated_at;

    public User (String name, String lastName, String password, UserRole role){
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.userRole = role;
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

    @Override
    public String getUsername() {
        return name+" "+lastName;
    }

}
