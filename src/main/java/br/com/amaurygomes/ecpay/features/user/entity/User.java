package br.com.amaurygomes.ecpay.features.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String encodedPassword;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ADMIN;
    private boolean isActive = true;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return List.of();
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.encodedPassword;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
