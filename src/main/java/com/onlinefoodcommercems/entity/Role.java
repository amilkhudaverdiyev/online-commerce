//package com.onlinefoodcommercems.entity;
//
//import com.onlinefoodcommercems.dto.request.RoleRequest;
//import com.onlinefoodcommercems.enums.Roles;
//import com.onlinefoodcommercems.enums.Status;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "role")
//@Builder
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Enumerated(EnumType.STRING)
//    private Roles authority;
//    @Enumerated(EnumType.STRING)
//    private Status status;
//    @OneToMany(cascade = CascadeType.ALL,mappedBy ="role" ,fetch = FetchType.EAGER)
//    private List<Customer> customers;
//}
