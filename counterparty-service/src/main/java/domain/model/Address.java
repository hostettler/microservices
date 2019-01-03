package domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String firstAddressLine;
	private String city;
	private String region;
	private String country;
	private String postalCode;

	
}
