package com.laundry.payment.service;


import com.laundry.payment.event.PaymentEvent;

import java.math.BigDecimal;

public interface PaymentService {
  void createPayment(PaymentEvent paymentEvent);
}
