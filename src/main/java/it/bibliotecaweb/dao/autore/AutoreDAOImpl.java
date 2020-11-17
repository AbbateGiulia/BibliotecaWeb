package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.Autore;

public class AutoreDAOImpl implements AutoreDAO {

	private EntityManager entityManager;

	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore", Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {
		return entityManager.find(Autore.class, id);
	}

	@Override
	public boolean update(Autore o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		} else {
			o = entityManager.merge(o);
			b = true;
		}
		return b;
	}

	@Override
	public boolean insert(Autore o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		}else {
		entityManager.persist(o);
		b = true;		
	}
		return b;
	}

	@Override
	public boolean delete(Autore o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		}else {
		entityManager.remove(entityManager.merge(o));
		b = true;
	}
		return b;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
