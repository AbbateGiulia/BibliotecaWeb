package it.bibliotecaweb.dao.utente;


import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Utente getUser(String username) throws Exception ;
	
	public Set<Utente> searchUtente(Utente input) throws Exception;

}
