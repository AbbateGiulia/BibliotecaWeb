package it.bibliotecaweb.service.autore;

import java.util.Set;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;


public interface AutoreService {
	
	public Set<Autore> listAll() throws Exception;

	public Autore caricaSingoloElemento(Long id) throws Exception;

	public boolean aggiorna(Autore autoreInstance) throws Exception;

	public boolean inserisciNuovo(Autore autoreInstance) throws Exception;

	public boolean rimuovi(Autore autoreInstance) throws Exception;


	// per injection
	public void setAutoreDAO(AutoreDAO autoreDAO);

}
