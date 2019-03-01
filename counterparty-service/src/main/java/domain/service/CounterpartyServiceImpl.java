package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Counterparty;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class CounterpartyServiceImpl implements CounterpartyService {

    @PersistenceContext(unitName = "CounterpartyPU")
    private EntityManager em;
    
	@Override
	public List<Counterparty> getAll() {
		log.info("retrieve all counterparties");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Counterparty> criteria = builder.createQuery( Counterparty.class );
		criteria.from(Counterparty.class);
		return em.createQuery( criteria ).getResultList();
	}
	
	@Override
	public Counterparty get(String lei) {
		return em.find(Counterparty.class, lei);
	}
}
