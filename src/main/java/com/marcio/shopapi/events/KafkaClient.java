package com.marcio.shopapi.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.marcio.shopapi.dto.ShopDTO;

import lombok.RequiredArgsConstructor;

/**
 * Classe que envia a mensagem para o Kafka.
 * Esta classe está sendo o produtor.
 */

@Service
@RequiredArgsConstructor
public class KafkaClient {

	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;
	
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	
	public void sendMessage(ShopDTO msg) {
		kafkaTemplate.send(SHOP_TOPIC_NAME, msg.getBuyerIdentifier(), msg);
	}
	
}
