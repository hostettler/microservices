package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Instrument;

@ApplicationScoped
public class InstrumentServiceImpl implements InstrumentService {

	@PersistenceContext(unitName = "InstrumentPU")
	private EntityManager em;

	public InstrumentServiceImpl() {
	}

	public InstrumentServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}

	@Override
	public List<Instrument> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Instrument> criteria = builder.createQuery(Instrument.class);
		criteria.from(Instrument.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void update(Instrument instrument) {
		Instrument i = em.find(Instrument.class, instrument.getId());
		if (i == null) {
			throw new IllegalArgumentException("Instrument does not exist : " + instrument.getId());
		}
		em.merge(instrument);
	}

	@Override
	public Instrument get(Long instrumentId) {
		return em.find(Instrument.class, instrumentId);
	}

	@Override
	public void create(Instrument instrument) {
		if (instrument.getId() != null) {
			throw new IllegalArgumentException("Instrument already exists : " + instrument.getId());
		}
		em.persist(instrument);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Instrument.class)));
		return em.createQuery(cq).getSingleResult();
	}
}
