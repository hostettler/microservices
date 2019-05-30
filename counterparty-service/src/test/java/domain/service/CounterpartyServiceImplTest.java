package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Address;
import domain.model.Counterparty;
import domain.model.Registration;
import domain.model.STATUS;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class CounterpartyServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "CounterpartyPUTest")
	EntityManager em;

	@InjectMocks
	private CounterpartyServiceImpl counterpartyServiceImpl;

	@Test
	void testGetAll() {
		int size = initDataStore();
		assertEquals(size, counterpartyServiceImpl.getAll().size());
	}

	@Test
	void testGet() {
		initDataStore();
		List<Counterparty> counterparties = counterpartyServiceImpl.getAll();
		String lei = counterparties.get(0).getLei();
		Counterparty cpty= counterpartyServiceImpl.get(lei);
		assertEquals(counterparties.get(0).getLei(), cpty.getLei());
		assertEquals(counterparties.get(0).getLegalAddress(), cpty.getLegalAddress());
	}
	
	@Test
	void testCount() {
		long size = initDataStore();
		long count = counterpartyServiceImpl.count();
		assertEquals(size, count);
	}

	private List<Counterparty> getCounterparties() {

		List<Counterparty> counterparties = new ArrayList<>();
		long numberOfCpty = Math.round((Math.random() * 10)) + 5;
		for (int i = 0; i < numberOfCpty; i++) {
			counterparties.add(getRandomCounterparty());
		}
		return counterparties;

	}

	private int initDataStore() {
		int size = counterpartyServiceImpl.getAll().size();
		List<Counterparty> counterparties = getCounterparties();	
		for (Counterparty c : counterparties) {
			em.persist(c);
		}
		return size + counterparties.size();
	}

	private Counterparty getRandomCounterparty() {
		Counterparty c = new Counterparty();
		c.setLei(UUID.randomUUID().toString());
		c.setName(UUID.randomUUID().toString());
		c.setStatus(STATUS.ACTIVE);

		Address a = new Address();
		a.setFirstAddressLine(UUID.randomUUID().toString());
		a.setCity(UUID.randomUUID().toString());
		a.setRegion(UUID.randomUUID().toString());
		a.setCountry(UUID.randomUUID().toString());
		a.setPostalCode(UUID.randomUUID().toString());

		Registration r = new Registration();
		r.setRegistrationAuthorityEntityID(UUID.randomUUID().toString());
		r.setRegistrationAuthorityID(UUID.randomUUID().toString());
		r.setJurisdiction(UUID.randomUUID().toString());

		c.setLegalAddress(a);
		c.setRegistration(r);

		return c;
	}
}
