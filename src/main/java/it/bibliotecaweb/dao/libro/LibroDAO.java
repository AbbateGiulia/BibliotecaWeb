package it.bibliotecaweb.dao.libro;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Libro;

public interface LibroDAO extends IBaseDAO<Libro> {

	public Set<Libro> searchLibro (Libro input) throws Exception;
}
