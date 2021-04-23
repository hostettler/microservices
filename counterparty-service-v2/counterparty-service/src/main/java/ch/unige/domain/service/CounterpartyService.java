package ch.unige.domain.service;

import java.util.List;

import ch.unige.domain.model.Counterparty;

public interface CounterpartyService {

	List<Counterparty> getAll();
	Counterparty get(String lei);
	Long count();

}