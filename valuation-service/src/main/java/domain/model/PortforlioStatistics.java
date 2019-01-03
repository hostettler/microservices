package domain.model;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class PortforlioStatistics {

	private Map<Instrument.INSTRUMENT_TYPE, BigDecimal> breakdownByInstrumentType;
	private Map<String, BigDecimal> breakdownByCurrency;
	private BigDecimal currentValue;
	private BigDecimal percentile95;
	private BigDecimal percentile99;

}
