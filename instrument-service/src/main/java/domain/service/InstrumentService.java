package domain.service;

import java.util.List;

import domain.model.Instrument;

public interface InstrumentService {

	List<Instrument> getAll();

	void update(Instrument instrument);

	void create(Instrument instrument);

	Instrument get(Long instrumentId);
}