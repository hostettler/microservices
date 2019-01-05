package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.Instrument;
import domain.service.InstrumentRepository;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class InstrumentConsumer {
	
	@Inject
	private InstrumentRepository repository;
	
	@Consumer(topics = "instruments", groupId = "pinfo-microservices" )
	public void updateInstrument(Instrument instrument) {
		log.info("Consumer got following message : " + instrument);
		if (repository.get(instrument.getId()) == null) {
			repository.add(instrument);	
		} else {
			repository.update(instrument);
		}
	}
}
