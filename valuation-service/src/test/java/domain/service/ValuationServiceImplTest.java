/**
 * 
 */
package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Instrument;
import domain.model.Instrument.INSTRUMENT_TYPE;
import domain.model.PortforlioStatistics;

/**
 * @author Steve.Hostettler
 *
 */
@ExtendWith(MockitoExtension.class)
class ValuationServiceImplTest {

	private static final AtomicLong id = new AtomicLong();

	@InjectMocks
	private ValuationServiceImpl valuationServiceImpl;

	@Mock
	private InstrumentRepository repository;

	/**
	 * Test method for
	 * {@link domain.service.ValuationServiceImpl#valuatePortfolio(java.lang.String)}.
	 */
	@Test
	public void testValuatePortfolio() {
		List<Instrument> instruments = getInstruments();
		when(repository.getAll()).thenReturn(instruments);
		PortforlioStatistics statistics = valuationServiceImpl.valuatePortfolio("CHF");
		assertEquals(0, statistics.getCurrentValue().compareTo(new BigDecimal("5.0")));
		assertEquals(0, statistics.getBreakdownByCurrency().get("CHF").compareTo(new BigDecimal("1.0")));
		assertEquals(0, statistics.getBreakdownByCurrency().get("USD").compareTo(new BigDecimal("4.0")));
		assertEquals(0, statistics.getBreakdownByInstrumentType().get(Instrument.INSTRUMENT_TYPE.BOND)
				.compareTo(new BigDecimal("3.0")));
		assertEquals(0, statistics.getBreakdownByInstrumentType().get(Instrument.INSTRUMENT_TYPE.STOCK)
				.compareTo(new BigDecimal("2.0")));
	}

	private List<Instrument> getInstruments() {
		List<Instrument> instruments = new ArrayList<>();
		instruments.add(getInstrument(INSTRUMENT_TYPE.BOND, "CHF", new BigDecimal("1.0")));
		instruments.add(getInstrument(INSTRUMENT_TYPE.BOND, "USD", new BigDecimal("2.0")));
		instruments.add(getInstrument(INSTRUMENT_TYPE.STOCK, "USD", new BigDecimal("2.0")));
		return instruments;
	}

	private Instrument getInstrument(Instrument.INSTRUMENT_TYPE type, String currency, BigDecimal amount) {
		Instrument instrument = new Instrument();
		instrument.setAmountInOriginalCurrency(amount);
		instrument.setBrokerLei("LEI1");
		instrument.setCounterpartyLei("LEI2");
		instrument.setDealDate(new Date());
		instrument.setDirection("Call");
		instrument.setId(String.valueOf(id.incrementAndGet()));
		instrument.setInstrumentType(type.getCode());
		instrument.setIsin("ISIN1");
		instrument.setMaturityDate(new Date());
		instrument.setOriginalCurrency(currency);
		instrument.setQuantity(1000l);
		instrument.setStrikeAmount(new BigDecimal("30.0"));
		instrument.setTracker("TRA");
		instrument.setValueDate(new Date());
		return instrument;
	}

}
