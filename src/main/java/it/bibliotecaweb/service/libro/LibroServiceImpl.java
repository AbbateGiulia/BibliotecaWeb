package it.bibliotecaweb.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.Libro;


public class LibroServiceImpl implements LibroService {

	private LibroDAO libroDAO;

	@Override
	public Set<Libro> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Libro caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Libro libroInstance) throws Exception {

		boolean b = false;

		for (Libro c : libroDAO.list()) {
			if (c.equals(libroInstance)) {
				System.out.println("trama già esistente");
				return b;
			}
		}
		// questo Ã¨ come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			b = libroDAO.update(libroInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return b;
	}

	@Override
	public boolean inserisciNuovo(Libro libroInstance) throws Exception {
		
		boolean b = false;

				// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			
			for (Libro c : libroDAO.list()) {
				if (c.equals(libroInstance)) {
					System.out.println("trama già esistente");
					return b;
				}
			} 
			
			if (libroInstance.getAutore()== null) {
				System.out.println("non puoi creare libro senza autore");
				return b;
			}

			// eseguo quello che realmente devo fare
			b = libroDAO.insert(libroInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return b;
	}

	@Override
	public boolean rimuovi(Libro libroInstance) throws Exception {
		boolean b = false;
		// questo Ã¨ come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			b = libroDAO.delete(libroInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return b;
	}

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;

	}

}
