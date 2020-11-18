package it.bibliotecaweb.service.utente;

import java.util.Set;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;



public interface UtenteService  {
	
	public Set<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;
	
	public Utente cercaUser(String username) throws Exception;

	public boolean aggiorna(Utente utenteInstance) throws Exception;

	public boolean inserisciNuovo(Utente utenteInstance) throws Exception;

	public boolean rimuovi(Utente utenteInstance) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public Set<Utente> ricercaUtente (Utente input) throws Exception;


	//per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

}
