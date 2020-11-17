package it.bibliotecaweb.service.libro;

import java.util.Set;

import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.Libro;



public interface LibroService {
	
	public Set<Libro> listAll() throws Exception;

	public Libro caricaSingoloElemento(Long id) throws Exception;

	public boolean aggiorna(Libro libroInstance) throws Exception;

	public boolean inserisciNuovo(Libro libroInstance) throws Exception;

	public boolean rimuovi(Libro libroInstance) throws Exception;


	// per injection
	public void setLibroDAO(LibroDAO libroDAO);


}
