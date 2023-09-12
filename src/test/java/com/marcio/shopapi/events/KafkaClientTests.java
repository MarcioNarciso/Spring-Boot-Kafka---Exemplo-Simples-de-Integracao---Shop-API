package com.marcio.shopapi.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.marcio.shopapi.dto.ShopDTO;

// Define que esta classe fará o teste de uma aplicação Spring Boot.
@ExtendWith(SpringExtension.class)
public class KafkaClientTests {

	// Define o objeto da classe que será testada, a classe que receberá os mocks injetados.
	@InjectMocks
	private KafkaClient sendKafkaMessage;
	
	// Define as dependências, que devem ser mockadas, da classe que será testada.
	@Mock
	private KafkaTemplate<String, ShopDTO> kafkaTemplate;
	
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	
	// Define o método como sendo um teste de unidade do JUnit.
	@Test
	public void testSendMessage() {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setStatus("SUCCESS");
		shopDTO.setBuyerIdentifier("b-1");
		
		this.sendKafkaMessage.sendMessage(shopDTO);
		
		// Verifica se o método "send" de kafkaTemplate foi chamado exatamente 1 vez.
		Mockito
			.verify(this.kafkaTemplate, Mockito.times(1))
			.send(SHOP_TOPIC_NAME, "b-1", shopDTO); 
	}
	
}
