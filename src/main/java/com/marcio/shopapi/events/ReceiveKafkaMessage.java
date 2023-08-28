package com.marcio.shopapi.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.marcio.shopapi.dto.ShopDTO;
import com.marcio.shopapi.model.Shop;
import com.marcio.shopapi.repository.ShopRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReceiveKafkaMessage {

	private final ShopRepository shopRepository;
	
	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
	
	@KafkaListener(topics=SHOP_TOPIC_EVENT_NAME, groupId="group")
	public void listenShopEvents(ShopDTO shopDto) {
		
		try {
			log.info("Status da compra recebida no tópico: {}", shopDto.getIdentifier());
			
			Shop shop = this.shopRepository.findByIdentifier(shopDto.getIdentifier());
			
			shop.setStatus(shopDto.getStatus());
			
			this.shopRepository.save(shop);
		} catch (Exception e) {
			log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
		}
		
	}
	
}
