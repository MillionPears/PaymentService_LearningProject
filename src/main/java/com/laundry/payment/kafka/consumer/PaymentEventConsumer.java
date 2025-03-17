package com.laundry.payment.kafka.consumer;

import com.laundry.payment.enums.KafkaTopic;
import com.laundry.payment.enums.PaymentCreateStatus;
import com.laundry.payment.event.PaymentEvent;
import com.laundry.payment.parser.Parser;
import com.laundry.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class PaymentEventConsumer {
  private final PaymentService paymentService;
  private final Parser eventParser;
  private final KafkaTemplate<String, String> stringKafkaTemplate;
  private final String paymentStatusTopic = KafkaTopic.PAYMENT_PROCESS_STATUS.getTopicName();

  @KafkaListener(topics = "#{T(com.laundry.payment.enums.KafkaTopic).PROCESS_PAYMENT_EVENT.getTopicName()}", groupId = "order-payment-group")
  public void consumeOrderPaymentEvent(String paymentEventReceived,@Header(KafkaHeaders.RECEIVED_KEY) String eventId) {
    PaymentEvent paymentEvent = eventParser.parseToObject(paymentEventReceived, PaymentEvent.class);
    try {
      paymentService.createPayment(paymentEvent);
      log.info(" [PAYMENT EVENT CONSUMER] - Successfully create payment for order");
      stringKafkaTemplate.send(paymentStatusTopic, eventId, PaymentCreateStatus.COMPLETED.toString());

    } catch (Exception e) {
      log.error(" [PAYMENT EVENT CONSUMER] - Error updating payment: {}", e.getMessage(), e);
      stringKafkaTemplate.send(paymentStatusTopic, eventId, PaymentCreateStatus.FAILED.toString());
    }

  }

}
