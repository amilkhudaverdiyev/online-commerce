package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity
@Table(name = "roles")
public class UserAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles authority;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Customer customer;

    @Override
    public String getAuthority() {
        return this.authority.toString();
    }
}
