package api.msg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Bond;
import domain.model.Instrument;
import domain.service.InstrumentService;

@ExtendWith(MockitoExtension.class)
class InstrumentProducerTest {

	@Mock
	private SimpleKafkaProducer<String, Instrument> kafkaProducer;
	@Mock
	private InstrumentService instrumentService;

	@InjectMocks
	private InstrumentProducer producer;

	@Test
	void testSendAllInstruments() {
		List<Instrument> instruments = getRandomInstrumentCollection();
		when(instrumentService.getAll()).thenReturn(instruments);
		producer.sendAllInstruments();
		verify(kafkaProducer, times(instruments.size())).send(eq("instruments"), any(Instrument.class));
	}

	@Test
	void testSendInstrument() {
		Instrument instrument = getRandomInstrument();
		producer.send(instrument);
		verify(kafkaProducer, times(1)).send("instruments", instrument);
	}

	@Test
	void testSendLong() {
		Instrument instrument = getRandomInstrument();
		when(instrumentService.get(instrument.getId())).thenReturn(instrument);
		producer.send(instrument.getId());
		verify(kafkaProducer, times(1)).send("instruments", instrument);
	}

	@Test
	void testSendLongNull() {
		Instrument instrument = getRandomInstrument();
		when(instrumentService.get(instrument.getId())).thenReturn(null);
		producer.send(instrument.getId());
		verify(kafkaProducer, times(0)).send("instruments", instrument);
	}

	private List<Instrument> getRandomInstrumentCollection() {
		List<Instrument> instruments = new ArrayList<>();
		long numberOfInstrument = Math.round((Math.random() * 1000));
		for (int i = 0; i < numberOfInstrument; i++) {
			instruments.add(getRandomInstrument());
		}
		return instruments;
	}

	private Instrument getRandomInstrument() {
		Bond b = new Bond();
		b.setBrokerLei(UUID.randomUUID().toString());
		b.setCounterpartyLei(UUID.randomUUID().toString());
		b.setAmountInOriginalCurrency(new BigDecimal("0.0"));
		b.setOriginalCurrency("USD");
		b.setValueDate(new Date());
		b.setMaturityDate(new Date());
		b.setIsin(UUID.randomUUID().toString());
		b.setQuantity(Long.valueOf(Math.round(Math.random() * 1000)));
		return b;
	}

	
}
