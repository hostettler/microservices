package domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Counterparty {

	@Id
	private String lei;
	@NotNull
	private String name;
	@Embedded	
	private Address legalAddress;
	@Embedded	
	private Registration registration;
	@NotNull
	private STATUS status;

	

}
