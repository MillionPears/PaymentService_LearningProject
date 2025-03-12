package com.laundry.payment.kafka.producer;


import com.laundry.payment.enums.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaProducer {
  private final KafkaTemplate<String, String> stringKafkaTemplate;

  private final String inventoryProcessStatus = KafkaTopic.PAYMENT_PROCESS_STATUS.getTopicName();
  private final short replicationFactor = 1;
  private final int partitionNumber = 3;



  @Bean
  public NewTopic inventoryNewTopic() {
    return new NewTopic(inventoryProcessStatus, partitionNumber, replicationFactor);
  }


}
