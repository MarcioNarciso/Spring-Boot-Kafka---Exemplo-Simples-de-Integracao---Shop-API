package com.marcio.shopapi.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.marcio.shopapi.dto.ShopDTO;
import com.marcio.shopapi.dto.ShopItemDTO;
import com.marcio.shopapi.model.Shop;
import com.marcio.shopapi.repository.ShopRepository;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

	@InjectMocks
	private ReceiveKafkaMessage receiveKafkaMessage;
	
	@Mock
	private ShopRepository shopRepository;
	
	@Test
	public void testSuccessfulMessageReceived() {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setStatus("SUCCESS");
		
		ShopItemDTO shopItemDTO = new ShopItemDTO();
		shopItemDTO.setAmount(1000);
		shopItemDTO.setProductIdentifier("product-1");
		shopItemDTO.setPrice((float) 100);
		
		shopDTO.getItems().add(shopItemDTO);
		
		Shop shop = Shop.convert(shopDTO);
		
		Mockito
			.when(shopRepository.findByIdentifier(shopDTO.getIdentifier()))
			.thenReturn(shop);
		
		receiveKafkaMessage.listenShopEvents(shopDTO);
		
		//Verifica se o método "findByIdentifier" de shopRepository foi chamado exatamente 1 vez.
		Mockito
			.verify(shopRepository, Mockito.times(1))
			.findByIdentifier(shopDTO.getIdentifier());
		
		// Verifica se o método "save" de shopRepository foi chamado exatamente 1 vez.
		Mockito
			.verify(shopRepository, Mockito.times(1))
			.save(shop);
			
	}
	
}
