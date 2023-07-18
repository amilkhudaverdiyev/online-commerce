package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", uniqueConstraints  = @UniqueConstraint(columnNames = "pinCode"))
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String area;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;
}
