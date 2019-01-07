package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Bond;
import domain.model.Instrument;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class InstrumentServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "InstrumentPUTest")
	EntityManager em;

	@InjectMocks
	private InstrumentServiceImpl instrumentService;

	@Test
	void testGetAll() {
		List<Instrument> instruments = instrumentService.getAll();
		int size = instruments.size();
		
		instrumentService.create(getRandomInstrument());
		instrumentService.create(getRandomInstrument());
		instrumentService.create(getRandomInstrument());
		instrumentService.create(getRandomInstrument());
		
		assertEquals(size + 4, instrumentService.getAll().size());
	}

	@Test
	void testUpdate() {
		instrumentService.create(getRandomInstrument());
		Instrument instrument = instrumentService.getAll().get(0);
		assertNotNull(instrument);
		Long id = instrument.getId();
		instrument.setOriginalCurrency("XXX");
		instrumentService.update(instrument);
		instrument = instrumentService.get(id);
		assertEquals("XXX", instrument.getOriginalCurrency());
	}

	@Test
	void testUpdateNonExistant() {
		Instrument i = new Instrument() {
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentService.update(i);
		});
	}

	@Test
	void testGet() {
		instrumentService.create(getRandomInstrument());
		Instrument instrument = instrumentService.getAll().get(0);
		assertNotNull(instrument);
		Long id = instrument.getId();
		Instrument getInstrument = instrumentService.get(id);
		assertEquals(instrument.getOriginalCurrency(), getInstrument.getOriginalCurrency());
	}

	@Test
	void testGetNonExistant() {
		List<Instrument> instruments = instrumentService.getAll();
		System.out.println("testGetNonExistant:" + instruments.size());

		assertNull(instrumentService.get(Long.MAX_VALUE));
	}

	@Test
	void testCreate() {
		Instrument instrument = getRandomInstrument();
		instrumentService.create(instrument);
		assertNotNull(instrument.getId());
	}


	@Test
	void testCreateDuplicate() {
		Instrument instrument = getRandomInstrument();
		instrumentService.create(instrument);
		assertThrows(IllegalArgumentException.class, () -> {
			instrumentService.create(instrument);
		});
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
		b.setQuantity(Long.valueOf(Math.round(Math.random()*1000)));
		return b;
	}
}
