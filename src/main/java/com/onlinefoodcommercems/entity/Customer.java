package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements UserDetails {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer")
    List<CartItem> cartItems;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer")
    List<Order> orders;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer")
    List<ConfirmationToken> token;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private String activationCode;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<UserAuthority> authorities = new HashSet<>();
    private boolean accountNonExpired;

    private boolean accountNonLocked = false;

    private boolean credentialsNonExpired;

    private boolean enabled = false;
    public Customer(Customer user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
    }

    public void addAuthority(UserAuthority authority) {
        this.authorities.add(authority);
        authority.setCustomer(this);
    }
}
