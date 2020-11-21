package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.model.Utente;

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
		String query1 = "FROM Libro l JOIN FETCH l.autore WHERE 1=1 ";
		if (input.getTitolo() != null) {
			query1 = query1 + " AND l.titolo like :titolo ";
		}
		if (input.getTrama() != null) {
			query1 = query1 + " AND l.trama like :trama ";
		}
		if (input.getGenere() != null) {
			query1 = query1 + " AND l.genere like :genere";
		}
		if (input.getAutore() != null) {
			query1 = query1 + " AND l.autore = :autore";
		}

		TypedQuery<Libro> query2 = entityManager.createQuery(query1, Libro.class);
		if (input.getTitolo() != null) {
			query2.setParameter("titolo", '%' + input.getTitolo() + '%');
		}
		if (input.getTrama() != null) {
			query2.setParameter("trama", '%' + input.getTrama() + '%');
		}
		if (input.getGenere() != null) {
			query2.setParameter("genere",  input.getGenere());
		}
		if (input.getAutore() != null) {
			query2.setParameter("autore", input.getAutore());
		}
		if (input.equals(null)) {
			this.list().toString();
		}
		return query2.getResultList().stream().collect(Collectors.toSet());

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
