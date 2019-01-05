/**
 * 
 */
package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Instrument;
import domain.model.PortforlioStatistics;

/**
 * @author Steve.Hostettler
 *
 */
@ExtendWith(MockitoExtension.class)
class ValuationServiceImplTest {

	@InjectMocks
	private ValuationServiceImpl valuationServiceImpl;

	@Mock
	private InstrumentRepository repository;

	/**
	 * Test method for
	 * {@link domain.service.ValuationServiceImpl#valuatePortfolio(java.lang.String)}.
	 */
	@Test
	private void testValuatePortfolio() {
		List<Instrument> instruments = getInstruments();
		when(repository.getAll()).thenReturn(instruments);
		PortforlioStatistics statistics = valuationServiceImpl.valuatePortfolio("CHF");
		assertEquals(statistics.getCurrentValue().compareTo(new BigDecimal("5.0")), 0);
		assertEquals(statistics.getBreakdownByCurrency().get("CHF").compareTo(new BigDecimal("1.0")), 0);
		assertEquals(statistics.getBreakdownByCurrency().get("USD").compareTo(new BigDecimal("4.0")), 0);
		assertEquals(statistics.getBreakdownByInstrumentType().get(Instrument.INSTRUMENT_TYPE.BOND)
				.compareTo(new BigDecimal("3.0")), 0);
		assertEquals(statistics.getBreakdownByInstrumentType().get(Instrument.INSTRUMENT_TYPE.STOCK)
				.compareTo(new BigDecimal("2.0")), 0);
	}

	private List<Instrument> getInstruments() {
		List<Instrument> instruments = new ArrayList<>();
		instruments.add(Instrument.builder().amountInOriginalCurrency(new BigDecimal("1.0"))
				.instrumentType(Instrument.INSTRUMENT_TYPE.BOND.getCode()).originalCurrency("CHF").build());
		instruments.add(Instrument.builder().amountInOriginalCurrency(new BigDecimal("2.0"))
				.instrumentType(Instrument.INSTRUMENT_TYPE.BOND.getCode()).originalCurrency("USD").build());
		instruments.add(Instrument.builder().amountInOriginalCurrency(new BigDecimal("2.0"))
				.instrumentType(Instrument.INSTRUMENT_TYPE.STOCK.getCode()).originalCurrency("USD").build());
		return instruments;
	}

}
