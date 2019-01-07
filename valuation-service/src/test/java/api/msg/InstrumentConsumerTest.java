package api.msg;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Instrument;
import domain.model.Instrument.INSTRUMENT_TYPE;
import domain.service.InstrumentRepository;

@ExtendWith(MockitoExtension.class)
class InstrumentConsumerTest {

	private static final AtomicLong id = new AtomicLong();

	@Mock
	private InstrumentRepository repository;

	@InjectMocks
	private InstrumentConsumer consumer;

	@Test
	void testUpdateExistingInstrument() {
		Instrument instrument = getRandomInstrument();
		when(repository.get(instrument.getId())).thenReturn(instrument);
		consumer.updateInstrument(instrument);
		verify(repository, times(1)).update(eq(instrument));
		verify(repository, times(0)).add(eq(instrument));
	}

	@Test
	void testUpdateNewInstrument() {
		Instrument instrument = getRandomInstrument();
		when(repository.get(instrument.getId())).thenReturn(null);
		consumer.updateInstrument(instrument);
		verify(repository, times(0)).update(eq(instrument));
		verify(repository, times(1)).add(eq(instrument));
	}

	private Instrument getRandomInstrument() {
		Instrument instrument = new Instrument();
		instrument.setAmountInOriginalCurrency(new BigDecimal("2.0"));
		instrument.setBrokerLei("LEI1");
		instrument.setCounterpartyLei("LEI2");
		instrument.setDealDate(new Date());
		instrument.setDirection("Call");
		instrument.setId(String.valueOf(id.incrementAndGet()));
		instrument.setInstrumentType(INSTRUMENT_TYPE.WARRANT.getCode());
		instrument.setIsin("ISIN1");
		instrument.setMaturityDate(new Date());
		instrument.setOriginalCurrency("USD");
		instrument.setQuantity(1000l);
		instrument.setStrikeAmount(new BigDecimal("30.0"));
		instrument.setTracker("TRA");
		instrument.setValueDate(new Date());
		return instrument;
	}
}
