package com.laundry.payment.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {
  private Long orderId;
  private BigDecimal totalAmount;
  private String paymentMethod;
}
