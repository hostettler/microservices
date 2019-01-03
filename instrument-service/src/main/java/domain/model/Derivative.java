package domain.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public abstract class Derivative extends Security {

	@NotNull
	private Date maturityDate;
}
