package com.laundry.payment.dto;

import com.laundry.payment.enums.PaymentMethod;
import com.laundry.payment.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
  private Long id;
  private Long orderId;
  private BigDecimal amount;
  private PaymentMethod method;
  private PaymentStatus status;
  private LocalDateTime paymentDate;
  private String transactionId;
}
