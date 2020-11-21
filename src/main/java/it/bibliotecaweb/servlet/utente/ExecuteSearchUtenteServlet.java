package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/admin/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchUtenteServlet() {
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
			// validiamo input
			String nomeInputParam = request.getParameter("nome");
			String cognomeInputParam = request.getParameter("cognome");
			String userInputParam = request.getParameter("username");
			String IdruoloInputParam = request.getParameter("ruolo");
			String statoInputParam = request.getParameter("stato");
			try {
				Ruolo ruoloCriteria = !Util.isEmptyOrNull(IdruoloInputParam) ? MyServiceFactory.getRuoloServiceInstance()
						.caricaSingoloElemento(Long.parseLong(IdruoloInputParam)) : null;
				StatoUtente statoCriteria = !Util.isEmptyOrNull(statoInputParam) ? Enum.valueOf(StatoUtente.class, statoInputParam):null;
				
			//logica + costruzione oggetto input
				
			Utente utenteCriteria = new Utente (nomeInputParam, cognomeInputParam, userInputParam);
			Set<Ruolo> setRuoliCriteria = new HashSet<Ruolo>();
			setRuoliCriteria.add(ruoloCriteria);
			utenteCriteria.setRuoli(setRuoliCriteria);
			utenteCriteria.setStato(statoCriteria);
			
			request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().ricercaUtente(utenteCriteria));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// andiamo ai risultati
			request.getRequestDispatcher("/jsp/utente/listutenti.jsp").forward(request, response);

		
	}

}
