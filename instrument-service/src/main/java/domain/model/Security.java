package domain.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public abstract class Security extends Instrument {

	@NotNull
	private String isin;
	
	@NotNull
	private Long quantity;
}
