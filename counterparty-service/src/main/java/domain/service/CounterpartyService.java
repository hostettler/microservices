package domain.service;

import java.util.List;

import domain.model.Counterparty;

public interface CounterpartyService {

	List<Counterparty> getAll();
	Counterparty get(String lei);
	Long count();

}