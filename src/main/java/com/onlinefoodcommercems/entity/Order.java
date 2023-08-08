package com.onlinefoodcommercems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinefoodcommercems.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
   @UpdateTimestamp
    private LocalDateTime orderDate;
   private LocalDateTime deliveryDate;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
