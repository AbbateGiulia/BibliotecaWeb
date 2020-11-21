package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteInsertAutoreServlet
 */
@WebServlet("/insert/ExecuteInsertAutoreServlet")
public class ExecuteInsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//input form inserimente
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String dataInput = request.getParameter("dataNascita");
		
		LocalDate data = !Util.isEmptyOrNull(dataInput)?LocalDate.parse(dataInput): null;
		Autore autoreInsert = new Autore(nomeInput,cognomeInput,data);
		
		//gestione ritorno parametri non valdiie messaggi errore associati
		if (Util.isEmptyOrNull(nomeInput)|| Util.isEmptyOrNull(cognomeInput)
				|| Util.isEmptyOrNull(dataInput)) {
			
			List<String> listaErrori= new ArrayList<String>();
			
			if(Util.isEmptyOrNull(nomeInput)) {
				listaErrori.add("Inserisci un nome valido");
			}
			
			if(Util.isEmptyOrNull(cognomeInput)) {
				listaErrori.add("Inserisci un cognome valido");
			}
			
			if(Util.isEmptyOrNull(dataInput)) {
				listaErrori.add("Inserisci una data valida");
			}
			
			request.setAttribute("autoreAttribute", autoreInsert);
			request.setAttribute("listaErroriAttribute", listaErrori);
			request.getRequestDispatcher("/jsp/autore/insertautore.jsp").forward(request, response);
			return;
		}
		
		//vecchi parametri di ricerca
		String nomeCriteria = request.getParameter("nomeCriteria");
		String cognomeCriteria = request.getParameter("cognomeCriteria");
		Autore autoreCriteria= new Autore (nomeCriteria, cognomeCriteria);
		
		
		try {
			MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(autoreInsert);
		} catch (Exception e) {
			request.getRequestDispatcher("/jsp/autore/insertautore.jsp").forward(request, response);
			e.printStackTrace();
		}	
		
		try {
			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().ricercaAutore(autoreCriteria));
			request.setAttribute("nomeCriteria", nomeCriteria);
			request.setAttribute("cognomeCriteria", cognomeCriteria);
		} catch (Exception e) {
			request.getRequestDispatcher("/jsp/autore/insertautore.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/jsp/autore/listautori.jsp").forward(request, response);
		
		
		
	}

}
