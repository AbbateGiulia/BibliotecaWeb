package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.Ruolo;

public interface RuoloService {
	
	public Set<Ruolo> listAll() throws Exception;

	public Ruolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ruolo ruoloInstance) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public void rimuovi(Ruolo ruoloInstance) throws Exception;

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;

	// per injection
	public void setRuoloDAO(RuoloDAO ruoloDAO);
}
