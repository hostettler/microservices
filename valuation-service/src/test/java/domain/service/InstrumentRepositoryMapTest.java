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
import domain.model.Instrument.INSTRUMENT_TYPE;

/**
 * @author Steve.Hostettler
 *
 */
@ExtendWith(MockitoExtension.class)
class InstrumentRepositoryMapTest {

	private static final AtomicLong id = new AtomicLong();

	@InjectMocks
	private InstrumentRepositoryMap instrumentRepositoryMap;

	@Test
	public void testAdd() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);
	}

	@Test
	public void testAddDuplicate() {
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
	public void testUpdate() {
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
	public void testUpdateNonExistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrument.setOriginalCurrency("GBP");
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentRepositoryMap.update(instrument);
		});
	}

	@Test
	public void testDelete() {
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
	public void testDeleteNonExistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentRepositoryMap.delete(instrument);
		});
	}

	@Test
	public void testGet() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		instrumentRepositoryMap.add(instrument);
		Instrument returnedInstrument = instrumentRepositoryMap.get(instrument.getId());
		assertEquals(instrument, returnedInstrument);
		assertEquals(INSTRUMENT_TYPE.WARRANT.getDescription(), returnedInstrument.getType().getDescription());
	}

	@Test
	public void testGetNonInexistant() {
		instrumentRepositoryMap.clear();
		Instrument instrument = getRandomInstrument();
		assertNull(instrumentRepositoryMap.get(instrument.getId()));

	}

	@Test
	public void testGetAll() {
		instrumentRepositoryMap.clear();
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		instrumentRepositoryMap.add(getRandomInstrument());
		Collection<Instrument> instruments = instrumentRepositoryMap.getAll();
		assertEquals(6, instruments.size());
	}

	private Instrument getRandomInstrument() {

		Instrument instrument = new Instrument();
		instrument.setAmountInOriginalCurrency(new BigDecimal("3.0"));
		instrument.setBrokerLei("LEI1");
		instrument.setCounterpartyLei("LEI2");
		instrument.setDealDate(new Date());
		instrument.setDirection("Call");
		instrument.setId(String.valueOf(id.incrementAndGet()));
		instrument.setInstrumentType(Instrument.INSTRUMENT_TYPE.WARRANT.getCode());
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
