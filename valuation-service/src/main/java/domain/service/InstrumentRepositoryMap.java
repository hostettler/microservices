package domain.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import domain.model.Instrument;

/**
 * Very simple HashMap based repository. Ideally, it should move to a more
 * reliable no sql database that scales horizontally to deal with high volumes.
 * 
 * @author Steve.Hostettler
 *
 */
@ApplicationScoped
public class InstrumentRepositoryMap implements InstrumentRepository {

	private Map<String, Instrument> instruments = new HashMap<>();

	@Override
	public void add(Instrument instrument) {
		Instrument i = instruments.putIfAbsent(instrument.getId(), instrument);
		if (i != null) {
			throw new IllegalArgumentException("Duplicate instrument with id " + instrument.getId());
		}
	}

	@Override
	public void update(Instrument instrument) {
		Instrument i = instruments.putIfAbsent(instrument.getId(), instrument);
		if (i == null) {
			throw new IllegalArgumentException("Cannot update unknown instrument " + instrument.getId());
		}
	}

	@Override
	public void delete(Instrument instrument) {
		Instrument i = this.instruments.remove(instrument.getId());
		if (i == null) {
			throw new IllegalArgumentException("Cannot update unknown instrument " + instrument.getId());
		}
	}

	@Override
	public Collection<Instrument> getAll() {
		return this.instruments.values();
	}

	@Override
	public Instrument get(String id) {
		return this.instruments.get(id);
	}

	@Override
	public void clear() {
		this.instruments.clear();
	}

}
