package com.marcio.shopapi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.marcio.shopapi.model.Shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {

	private String identifier;
	private LocalDate dateShop;
	private String status;
	private String buyerIdentifier;
	private List<ShopItemDTO> items = new ArrayList<>();
	
	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setIdentifier(shop.getIdentifier());
		shopDTO.setDateShop(shop.getDateShop());
		shopDTO.setStatus(shop.getStatus());
		shopDTO.setBuyerIdentifier(shop.getBuyerIdentifier());		
		// Converte os ShopItem para ShopItemDTO
		List<ShopItemDTO> itens = shop.getItems()
										.stream()
										.map(i -> ShopItemDTO.convert(i))
										.collect(Collectors.toList());
		
		shopDTO.setItems(itens);
		
		return shopDTO;
	}
	
}
