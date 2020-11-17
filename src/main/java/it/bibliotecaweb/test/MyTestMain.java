package it.bibliotecaweb.test;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.CodiceRuolo;
import it.bibliotecaweb.model.Genere;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.utente.UtenteService;

public class MyTestMain {

	public static void main(String[] args) {

		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		LibroService libroServiceInstance = MyServiceFactory.getLibroServiceInstance();
		AutoreService autoreServiceInstance = MyServiceFactory.getAutoreServiceInstance();
		// ora passo alle operazioni CRUD
		try {
			/* 
			//LocalDate data = LocalDate.of(1265, 10, 20);
			Libro libro= new Libro("La Divina Commedia",Genere.CLASSICI, "Il grande Viaggio di Dante");
			//Set<Libro> listaLibriAutore = new HashSet<Libro>();
			//Autore autore= new Autore ("Dante", "Alighieri", data );
			//listaLibriAutore.add(libro);
			//autore.setLibri(listaLibriAutore);
			Autore autoreDaDb = autoreServiceInstance.caricaSingoloElemento(4L);
			libro.setAutore(autoreDaDb);
			//autoreServiceInstance.inserisciNuovo(autore);
			libroServiceInstance.inserisciNuovo(libro);
			/*
			 
			 */
			 Ruolo RuoloDaDb = ruoloServiceInstance.caricaSingoloElemento(1L);
			 Ruolo RuoloDaDb2 = ruoloServiceInstance.caricaSingoloElemento(2L);
			 Utente utente = new Utente ("Mari", "mariopass","Mario","Rossi");
			 Set<Ruolo> listaRuoli = new HashSet<Ruolo>();
			 listaRuoli.add(RuoloDaDb);
			 listaRuoli.add(RuoloDaDb2);
			 utente.setRuoli(listaRuoli);
			 utenteServiceInstance.inserisciNuovo(utente);
			
					
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	/*private static void initRuoli(RuoloService ruoloServiceInstance) throws Exception {

		ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", CodiceRuolo.ADMIN_ROLE));

		ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", CodiceRuolo.GUEST_ROLE));

		ruoloServiceInstance.inserisciNuovo(new Ruolo("Premium User", CodiceRuolo.CLASSIC_ROLE));
	}
*/
}
