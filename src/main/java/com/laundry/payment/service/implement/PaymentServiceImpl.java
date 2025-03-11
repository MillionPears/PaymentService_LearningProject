package com.laundry.payment.service.implement;

import com.laundry.payment.entity.Payment;
import com.laundry.payment.enums.PaymentMethod;
import com.laundry.payment.enums.PaymentStatus;
import com.laundry.payment.event.PaymentEvent;
import com.laundry.payment.repository.PaymentRepository;
import com.laundry.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;

  @Override
  @Transactional
  public void createPayment(PaymentEvent paymentEvent) {
    Payment payment = Payment.builder()
      .orderId(paymentEvent.getOrderId())
      .amount(paymentEvent.getTotalAmount())
      .method(PaymentMethod.valueOf(paymentEvent.getPaymentMethod()))
      .status(PaymentStatus.PENDING)
      .build();
    paymentRepository.save(payment);
  }
}
