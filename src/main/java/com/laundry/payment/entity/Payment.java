package com.laundry.payment.entity;


import com.laundry.payment.enums.PaymentMethod;
import com.laundry.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "payments")
public class Payment extends Auditor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @NotNull
  @Column(name = "amount")
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "method")
  private PaymentMethod method;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private PaymentStatus status;
}
