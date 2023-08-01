package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phoneNumber;
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Roles role;
    @Enumerated(EnumType.STRING)
    private Status status;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany( mappedBy = "customer")
    List<CartItem> cartItems;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany( mappedBy = "customer")
    List<Order> orders;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @OneToMany( mappedBy = "customer")
//    List<Attachment> pdf;



}
