package it.bibliotecaweb.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;

public class AutoreServiceImpl implements AutoreService {
	
	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> listAll() throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					autoreDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return autoreDAO.list();

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					entityManager.close();
				}
	}

	@Override
	public Autore caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					autoreDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return autoreDAO.get(id);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					entityManager.close();
				}
	}

	@Override
	public boolean aggiorna(Autore autoreInstance) throws Exception {
		boolean b = false;
		// questo Ã¨ come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo Ã¨ come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					autoreDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					b =autoreDAO.update(autoreInstance);
				

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				}
				return b;
	}

	@Override
	public boolean inserisciNuovo(Autore autoreInstance) throws Exception {	
		boolean b = false;
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			

			// eseguo quello che realmente devo fare
			b=autoreDAO.insert(autoreInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return b;
		
	}

	@Override
	public boolean rimuovi(Autore autoreInstance) throws Exception {
		
		boolean b = false;
		// questo Ã¨ come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo Ã¨ come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					autoreDAO.setEntityManager(entityManager);
					
					if (autoreInstance.getLibri().size() != 0) {
						throw new Exception("non puoi eliminare autore con libri");
					}

					// eseguo quello che realmente devo fare
					b=autoreDAO.delete(autoreInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				}
		return b;
	}

	@Override
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO=autoreDAO;
		
	}

}
