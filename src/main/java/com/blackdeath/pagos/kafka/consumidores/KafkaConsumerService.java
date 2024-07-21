package com.blackdeath.pagos.kafka.consumidores;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * {@link Service} consumidor de Kafka
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "pagos-topic", groupId = "grupo-consumidor")
    public void consume(String message) {
        System.out.println("Mensaje recibido: " + message);
    }
}
