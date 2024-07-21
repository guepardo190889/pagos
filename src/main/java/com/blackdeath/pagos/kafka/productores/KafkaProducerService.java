package com.blackdeath.pagos.kafka.productores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * {@link Service} productor de Kafka
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@Service
public class KafkaProducerService {

	private static final String TOPIC = "pagos-topic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		kafkaTemplate.send(TOPIC, message);
	}

}
