package com.marcio.shopapi.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.marcio.shopapi.dto.ShopDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String identifier;
	private String status;
	
	@Column(name = "date_shop")
	@Temporal(TemporalType.DATE)
	private LocalDate dateShop;
	
	@Column(name = "buyer_identifier")
	private String buyerIdentifier;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shop")
	private List<ShopItem> items;
	
	public static Shop convert(ShopDTO shopDto) {
		Shop shop = new Shop();
		shop.setIdentifier(shopDto.getIdentifier());
		shop.setStatus(shopDto.getStatus());
		shop.setDateShop(shopDto.getDateShop());
		shop.setBuyerIdentifier(shopDto.getBuyerIdentifier());
		
		// Converte os ShopItemDTO para ShopItem
		List<ShopItem> itens = shopDto.getItems()
										.stream()
										.map(i -> ShopItem.convert(i))
										.collect(Collectors.toList());
									
		
		shop.setItems(itens);
		
		return shop;
	}
	
}
