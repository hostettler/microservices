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

	@Override
	public List<Instrument> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Instrument> criteria = builder.createQuery(Instrument.class);
		criteria.from(Instrument.class);
		return em.createQuery(criteria).getResultList();
	}
}
