package br.com.amaurygomes.ecpay.user.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
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
    private Role role;
    private boolean isActive;

}
