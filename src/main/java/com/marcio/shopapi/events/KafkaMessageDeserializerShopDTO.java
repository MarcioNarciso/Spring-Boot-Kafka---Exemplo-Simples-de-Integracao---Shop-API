package com.marcio.shopapi.events;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marcio.shopapi.dto.ShopDTO;

public class KafkaMessageDeserializerShopDTO implements Deserializer<ShopDTO>{

	@Override
	public ShopDTO deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		try {
			
			Object object = objectMapper.readValue(data, Object.class);
			
			return objectMapper.readValue(data, ShopDTO.class);
		} catch (Exception e) {
			throw new SerializationException("Erro quando estava desserializando byte[] para ShopDTO.");
		}
	}

}
