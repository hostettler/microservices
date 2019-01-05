package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class InstrumentConsumer {
	
	@Inject
	private InstrumentProducer producer;

	@Consumer(topics = "instrumentsReq", groupId = "pinfo-microservices")
	public void updateInstrument(final String message) {
		log.info("Consumer got following message : " + message);
		if (message.equals("all")) {
			producer.sendAllInstruments();
		} else {
			producer.send(message);
		}
	}
}
