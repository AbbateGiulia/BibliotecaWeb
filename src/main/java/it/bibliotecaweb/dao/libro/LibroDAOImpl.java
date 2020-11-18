package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Libro;

public class LibroDAOImpl implements LibroDAO {

	private EntityManager entityManager;

	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro l join fetch l.autore a", Libro.class).getResultList().stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		TypedQuery<Libro> query = entityManager.createQuery("from Libro l join fetch l.autore a where l.id= ?1",
				Libro.class);
		return query.setParameter(1, id).getSingleResult();
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

	public Set<Libro> searchLibro(Libro input) throws Exception {
		TypedQuery<Libro> query = entityManager.createQuery(
				"from Libro l join fetch l.autore a where l.titolo= ?1  and l.genere =?2 and l.trama = ?3 and a = ?4",
				Libro.class);
		query.setParameter(1, input.getTitolo());
		query.setParameter(2, input.getGenere());
		query.setParameter(3, input.getTrama());
		query.setParameter(4, input.getAutore());
		return query.getResultList().stream().collect(Collectors.toSet());

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
