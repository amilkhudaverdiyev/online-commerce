package com.onlinefoodcommercems.entity;

import com.onlinefoodcommercems.enums.OrderAcceptStatus;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Date orderDate;
    private Date deliveryDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderAcceptStatus acceptStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
