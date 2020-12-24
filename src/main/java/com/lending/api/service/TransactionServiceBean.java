package com.lending.api.service;

import com.lending.api.config.KafkaConfig;
import com.lending.api.entity.Transaction;
import com.lending.api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TransactionServiceBean implements TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  private KafkaProducer<String, String> producer;

  private KafkaProducer<String, String> createKafkaProducer() {
    // Create producer properties
    Properties prop = new Properties();
    prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BOOTSTRAPSERVERS);
    prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    // create safe Producer
    prop.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
    prop.setProperty(ProducerConfig.ACKS_CONFIG, KafkaConfig.ACKS_CONFIG);
    prop.setProperty(ProducerConfig.RETRIES_CONFIG, KafkaConfig.RETRIES_CONFIG);
    prop.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, KafkaConfig.MAX_IN_FLIGHT_CONN);

    // Additional settings for high throughput producer
    prop.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, KafkaConfig.COMPRESSION_TYPE);
    prop.setProperty(ProducerConfig.LINGER_MS_CONFIG, KafkaConfig.LINGER_CONFIG);
    prop.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, KafkaConfig.BATCH_SIZE);

    // Create producer
    return new KafkaProducer<String, String>(prop);
  }

  @Override
  public void create(Transaction transaction) {
    Transaction savedTransaction = transactionRepository.save(transaction);
    String msg = savedTransaction.toString();

    producer = createKafkaProducer();
    producer.send(new ProducerRecord<String, String>(KafkaConfig.TOPIC, null, msg));
  }

  @Override
  public List<Transaction> findByDateRange(Date startDate, Date endDate) {
    return transactionRepository.findAllByTransactionDateGreaterThanEqualAndTransactionDateLessThanEqual(startDate, endDate);
  }

  @Override
  public List<Transaction> findByUserId(Integer userId) {
    return transactionRepository.findAllByUserId(userId);
  }
}
