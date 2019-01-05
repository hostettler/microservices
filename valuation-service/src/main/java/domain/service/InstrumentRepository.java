package domain.service;

import java.util.Collection;

import domain.model.Instrument;

public interface InstrumentRepository {

	void add(Instrument instrument);
	void update(Instrument instrument);
	void delete(Instrument instrument);
	Instrument get(String id);
	void clear();
	Collection<Instrument> getAll();
	
}
