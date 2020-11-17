package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.Libro;

public class LibroDAOImpl implements LibroDAO {

	private EntityManager entityManager;

	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro", Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		return entityManager.find(Libro.class, id);
	}

	@Override
	public boolean update(Libro o) throws Exception {
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
	public boolean insert(Libro o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.persist(o);
			b = true;
		}
		return b;
	}

	@Override
	public boolean delete(Libro o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.remove(entityManager.merge(o));
		}
		return b;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
