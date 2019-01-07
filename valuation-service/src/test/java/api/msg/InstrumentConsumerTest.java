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
		Instrument instrument = Instrument.builder().amountInOriginalCurrency(new BigDecimal("3.0")).brokerLei("LEI1")
				.counterpartyLei("LEI2").dealDate(new Date()).direction("Call").id(String.valueOf(id.incrementAndGet()))
				.instrumentType(Instrument.INSTRUMENT_TYPE.WARRANT.getCode()).isin("ISIN1").maturityDate(new Date())
				.originalCurrency("USD").quantity(1000l).strikeAmount(new BigDecimal("30.0")).tracker("TRA")
				.valueDate(new Date()).build();
		return instrument;
	}
}
