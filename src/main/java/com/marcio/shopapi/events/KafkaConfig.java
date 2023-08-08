package com.marcio.shopapi.events;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.marcio.shopapi.dto.ShopDTO;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Value(value = "${kafka.boostrapAddress : localhost:9092}")
	private String bootstrapAddress;
	
	@Bean
	public ProducerFactory<String, ShopDTO> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		
		// Define o endereço e porta HTTP do Kafka
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		// Define o formato que os dados enviados serão serializados
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// Define um identificador pro produtor
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "shop-api");
		
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public KafkaTemplate<String, ShopDTO> kafkaTemplate() {
		return new KafkaTemplate<String, ShopDTO>(this.producerFactory());
	}
	
	public ConsumerFactory<String, ShopDTO> consumerFactory() {
		JsonDeserializer<ShopDTO> deserializer = new JsonDeserializer<>(ShopDTO.class);
		
		HashMap<String, Object> props = new HashMap();
		
		// Define o endereço e porta HTTP do Kafka
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapAddress);
		
		return new DefaultKafkaConsumerFactory(props, new StringDeserializer(), deserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ShopDTO>	kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ShopDTO> factory = new ConcurrentKafkaListenerContainerFactory();
		
		factory.setConsumerFactory(this.consumerFactory());
		
		return factory;
	}
}
