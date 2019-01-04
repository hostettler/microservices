package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Instrument;
import domain.service.InstrumentService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class InstrumentProducer {

	@Producer
	SimpleKafkaProducer<String, Instrument> producer;

	@Inject
	private InstrumentService instrumentService;

	public void sendAllInstruments() {
		log.info("Producer produced a message");
		for (Instrument instrument : instrumentService.getAll()) {
			producer.send("instruments", instrument);	
		}
		
	}
}
