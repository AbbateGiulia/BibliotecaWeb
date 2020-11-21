package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	@Override
	public Set<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente", Utente.class).getResultList().stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Utente get(Long id) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery("select distinct u from Utente u JOIN FETCH u.ruoli r where u.id = ?1 ", Utente.class);
		return query.setParameter(1, id).getSingleResult();
	}

	@Override
	public boolean update(Utente o) throws Exception {
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
	public boolean insert(Utente o) throws Exception {
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
	public boolean delete(Utente o) throws Exception {
		boolean b = false;
		if (o == null) {
			throw new Exception("Problema valore in input");
		} else {
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
	public Utente getUser(String username) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery("from Utente u where u.username= ?1 ", Utente.class);
		return query.setParameter(1, username).getSingleResult();
	}

	@Override
	public Set<Utente> searchUtente(Utente input) throws Exception {
		String query1 = "FROM Utente u JOIN FETCH u.ruoli WHERE 1=1 ";
		if (input.getNome() != null) {
			query1 = query1 + " AND u.nome like :nome ";
		}
		if (input.getCognome() != null) {
			query1 = query1 + " AND u.cognome like :cognome ";
		}
		if (input.getUsername() != null) {
			query1 = query1 + " AND u.username like :username";
		}
		if (input.getStato() != null) {
			query1 = query1 + " AND u.stato = :stato";
		}
//		if (input.getRuoli() != null) {
//			query1 = query1 + " AND u.ruoli = :ruoli";
//		}

		TypedQuery<Utente> query2 = entityManager.createQuery(query1, Utente.class);
		if (input.getNome() != null) {
			query2.setParameter("nome", '%' + input.getNome() + '%');
		}
		if (input.getCognome() != null) {
			query2.setParameter("cognome", '%' + input.getCognome() + '%');
		}
		if (input.getUsername() != null) {
			query2.setParameter("username", '%' + input.getUsername() + '%');
		}
		if (input.getStato() != null) {
			query2.setParameter("stato", input.getStato());
		}
//		if (input.getRuoli() != null) {
//			query2.setParameter("ruoli", input.getRuoli().iterator().next());
//		}
		if (input.equals(null)) {
			this.list().toString();
		}
		return query2.getResultList().stream().collect(Collectors.toSet());
		
	}

}
