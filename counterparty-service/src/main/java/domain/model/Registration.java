package domain.model;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Registration {

	private String registrationAuthorityID;
	private String registrationAuthorityEntityID;
	private String jurisdiction;
	private String legalFormCode;
	private String category;
	private Date registrationDate;
	private Date lastUpdated;
	private String registrationStatus;
	private Date nextRenewalDate;
	
}