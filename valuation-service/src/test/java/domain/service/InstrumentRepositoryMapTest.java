/**
 * 
 */
package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Instrument;

/**
 * @author Steve.Hostettler
 *
 */
@ExtendWith(MockitoExtension.class)
class InstrumentRepositoryMapTest {

	
	
	@InjectMocks
	private InstrumentRepositoryMap instrumentRepositoryMap;

	@Test
	void testAdd() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);
	}

	@Test
	void testAddDuplicate() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);

		assertThrows(IllegalArgumentException.class, () -> {
			instrumentRepositoryMap.add(instrument);
		});

	}

	@Test
	void testUpdate() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);

		instrument.setOriginalCurrency("GBP");
		instrumentRepositoryMap.update(instrument);

		returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument.getOriginalCurrency(), returnedInstrument.getOriginalCurrency());
	}

	@Test
	void testUpdateNonExistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrument.setOriginalCurrency("GBP");
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentRepositoryMap.update(instrument);
		});
	}

	@Test
	void testDelete() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);

		instrumentRepositoryMap.delete(instrument);

		returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertNull(returnedInstrument);
	}

	@Test
	void testDeleteNonExistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentRepositoryMap.delete(instrument);
		});
	}

	@Test
	void testGet() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);
	}

	@Test
	void testGetNonInexistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		assertNull(instrumentRepositoryMap.get(instrument.getId()));

	}

	@Test
	void testGetAll() {
		instrumentRepositoryMap.clear();
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		Collection<Instrument> instruments = instrumentRepositoryMap.getAll();
		assertEquals(instruments.size(), 6);
	}

	
	private static final AtomicLong id = new AtomicLong();
	private Instrument getRandomInstrument() {

		Instrument instrument = Instrument.builder().amountInOriginalCurrency(new BigDecimal("3.0")).brokerLei("LEI1")
				.counterpartyLei("LEI2").dealDate(new Date()).direction("Call").id(String.valueOf(id.incrementAndGet()))
				.instrumentType(Instrument.INSTRUMENT_TYPE.WARRANT.getCode()).isin("ISIN1").maturityDate(new Date())
				.originalCurrency("USD").quantity(1000l).strikeAmount(new BigDecimal("30.0")).tracker("TRA")
				.valueDate(new Date()).build();
		return instrument;
	}

}
