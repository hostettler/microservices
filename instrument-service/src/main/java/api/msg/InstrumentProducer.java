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
	private SimpleKafkaProducer<String, Instrument> producer;

	@Inject
	private InstrumentService instrumentService;

	public void sendAllInstruments() {
		log.info("Send the current state of ALL instruments to the topic");
		for (Instrument instrument : instrumentService.getAll()) {
			producer.send("instruments", instrument);	
		}
	}

	public void send(Instrument instrument) {
		log.info("Send the state of an instrument to the topic with id " + instrument.getId() );
		producer.send("instruments", instrument);			
	}

	public void send(Long instrumentId) {
		log.info("Send the state of an instrument to the topic with id " + instrumentId);
		Instrument instrument = instrumentService.get(instrumentId);
		if (instrument != null) {
			send(instrument);
		}
	}
}
