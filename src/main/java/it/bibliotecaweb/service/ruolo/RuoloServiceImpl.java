package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.Ruolo;




public class RuoloServiceImpl implements RuoloService {
	
	private RuoloDAO ruoloDAO;

	@Override
	public Set<Ruolo> listAll() throws Exception {
		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return ruoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					ruoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return ruoloDAO.get(id);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					entityManager.close();
				}
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi aggiornare il ruolo");		
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi inserire il ruolo");
	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi rimuovere il ruolo");
	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception {
		return null;	
	}

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO=ruoloDAO;
		
	}

	
}
