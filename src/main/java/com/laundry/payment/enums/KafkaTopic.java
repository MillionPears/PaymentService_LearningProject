package com.laundry.payment.enums;

import lombok.Getter;

@Getter
public enum KafkaTopic {
  PROCESS_PAYMENT_EVENT("payment.process"),
  PAYMENT_PROCESS_STATUS("payment.process.status");

  private final String topicName;

  KafkaTopic(String topicName) {
    this.topicName = topicName;
  }
}
