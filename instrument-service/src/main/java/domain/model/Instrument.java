package domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Entity
@DiscriminatorColumn(name = "instrumentType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ApiModel(subTypes = {Bond.class, Stock.class, Deposit.class, Loan.class, Warrant.class}, 
discriminator = "instrumentType")
public abstract class Instrument {

	@Id
	@SequenceGenerator(name = "INSTRUMENT_SEQ", sequenceName = "INSTRUMENT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTRUMENT_SEQ")	
	private Long id;

	@NotNull
	private String brokerLei;

	@NotNull
	private String counterpartyLei;

	@NotNull
	private String originalCurrency;

	@NotNull
	private BigDecimal amountInOriginalCurrency;

	@NotNull
	private Date dealDate;

	@NotNull
	private Date valueDate;
	
	@NotNull
	@Column(name="instrumentType", insertable=false, updatable=false)
	private String instrumentType;

}
