package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;

public class AutoreDAOImpl implements AutoreDAO {

	private EntityManager entityManager;

	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore", Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {		
		TypedQuery<Autore> query = entityManager.createQuery("select a from Autore a  left join fetch a.libri l where a.id =?1", Autore.class);
		return query.setParameter(1, id).getSingleResult();
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

	@Override
	public Set<Autore> searchAutore(Autore input) throws Exception {
		String query1 = "FROM Autore a WHERE 1=1 ";
		if (input.getNome() != null) {
			query1 = query1 + " AND a.nome like :nome ";
		}
		if (input.getCognome()!= null) {
			query1 = query1 + " AND a.cognome like :cognome ";
		}	

		TypedQuery<Autore> query2 = entityManager.createQuery(query1, Autore.class);
		if (input.getNome() != null) {
			query2.setParameter("nome", '%' + input.getNome() + '%');
		}
		if (input.getCognome() != null) {
			query2.setParameter("cognome", '%' + input.getCognome() + '%');
		}
		if (input.equals(null)) {
			this.list().toString();
		}
		return query2.getResultList().stream().collect(Collectors.toSet());
	}

}
