package domain.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class PortforlioStatistics {

	private Map<Instrument.INSTRUMENT_TYPE, BigDecimal> breakdownByInstrumentType;
	private Map<String, BigDecimal> breakdownByCurrency;
	private String reportingCurrency;
	private BigDecimal currentValue;
	private BigDecimal percentile95;
	private BigDecimal percentile99;
	
	public PortforlioStatistics(String reportingCurrency) {
		this.breakdownByInstrumentType = new EnumMap<>(Instrument.INSTRUMENT_TYPE.class);
		this.breakdownByCurrency = new HashMap<>();
		this.currentValue = new BigDecimal("0.0");
		this.percentile95 = new BigDecimal("0.0");
		this.percentile99 = new BigDecimal("0.0");
		this.reportingCurrency = reportingCurrency;
	}

	public void add(BigDecimal amount, Instrument.INSTRUMENT_TYPE type, String currency) {
		BigDecimal amountInReportingCurrency = amount;
		if (!currency.equals(reportingCurrency)) {
			BigDecimal rate = new BigDecimal("1.0");
			amountInReportingCurrency = amount.multiply(rate);
		}
		
		BigDecimal valueForCurrency = this.breakdownByCurrency.get(currency);
		if (valueForCurrency == null) {
			this.breakdownByCurrency.put(currency, amount);
		} else {
			this.breakdownByCurrency.put(currency, valueForCurrency.add(amount));
		}

		BigDecimal valueForType = this.breakdownByInstrumentType.get(type);
		if (valueForType == null) {
			this.breakdownByInstrumentType.put(type, amountInReportingCurrency);
		} else {
			this.breakdownByInstrumentType.put(type, valueForType.add(amount));
		}
		
		this.currentValue = this.currentValue.add(amountInReportingCurrency);
	}
}
