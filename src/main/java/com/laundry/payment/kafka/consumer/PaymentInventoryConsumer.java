package com.laundry.payment.kafka.consumer;

import com.laundry.payment.event.PaymentEvent;
import com.laundry.payment.parser.EventParser;
import com.laundry.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class PaymentInventoryConsumer {
  private final PaymentService paymentService;
  private final EventParser eventParser;

  @KafkaListener(topics = "${kafka.topics.order-payment}", groupId = "order-payment-group")
  public void consumeOrderPaymentEvent(String paymentEventReceived) {
    PaymentEvent paymentEvent = eventParser.parseEvent(paymentEventReceived, PaymentEvent.class);
    try {
      paymentService.createPayment(paymentEvent);
      log.info(" [PAYMENT EVENT CONSUMER] - Successfully create payment for order");
    } catch (Exception e) {
      log.error(" [PAYMENT EVENT CONSUMER] - Error updating payment: {}", e.getMessage(), e);
    }

  }

}
