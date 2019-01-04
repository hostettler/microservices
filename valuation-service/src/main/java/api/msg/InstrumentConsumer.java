package api.msg;

import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Instrument;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class InstrumentConsumer {
	
	@Consumer(topics = "instruments", groupId = "pinfo-microservices" )
	public void sendAllInstruments(Instrument instrument) {
		log.info("Consumer got following message : " + instrument);
	}
}
