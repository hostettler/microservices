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
		if ("all".equals(message)) {
			producer.sendAllInstruments();
		} else {
			// interpret the instrument id
			try {
				Long instrumentId = Long.valueOf(message);
				producer.send(instrumentId);
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Message must be wither a numeric instrument identifier or 'all'");
			}
		}
	}
}
