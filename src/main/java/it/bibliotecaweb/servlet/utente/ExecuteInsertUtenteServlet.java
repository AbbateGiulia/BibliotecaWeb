package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/admin/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// validiamo input
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String userInputParam = request.getParameter("username");
		String passInputParam = request.getParameter("password");
		Set<Ruolo> setRuoliInsert = new HashSet<Ruolo>();

		// validazione campi required e creazione utente "non valido"
		// con relativi mess di errore
		if (Util.isEmptyOrNull(nomeInputParam) || Util.isEmptyOrNull(cognomeInputParam)
				|| Util.isEmptyOrNull(userInputParam) || Util.isEmptyOrNull(passInputParam)) {

			List<String> listaErrori = new ArrayList<String>();

			Utente utenteInvalid = new Utente(userInputParam, passInputParam, nomeInputParam, cognomeInputParam);

			if (Util.isEmptyOrNull(nomeInputParam)) {
				listaErrori.add("Inserisci un nome valido");
			}

			if (Util.isEmptyOrNull(cognomeInputParam)) {
				listaErrori.add("Inserisci una cognome valido");
			}

			if (Util.isEmptyOrNull(userInputParam)) {
				listaErrori.add("Inserisci un username valido");
			}

			if (Util.isEmptyOrNull(passInputParam)) {
				listaErrori.add("Inserisci una password valida");
			}

			for (String s : request.getParameterValues("ruolo")) {
				if (Util.isEmptyOrNull(s)) {
					listaErrori.add("Inserisci almeno un ruolo");
				} else {
					Ruolo ruoloInsert = null;
					try {
						ruoloInsert = MyServiceFactory.getRuoloServiceInstance()
								.caricaSingoloElemento(Long.parseLong(s));
					} catch (NumberFormatException e) {

						listaErrori.add("Problemi inserimento ruolo");
						request.getRequestDispatcher("/jsp/utente/insertutente.jsp").forward(request, response);
						e.printStackTrace();

					} catch (Exception e) {

						listaErrori.add("Problemi inserimento ruolo");
						request.getRequestDispatcher("/jsp/utente/insertutente.jsp").forward(request, response);
						e.printStackTrace();
					}
					setRuoliInsert.add(ruoloInsert);
				}
			}
			
			utenteInvalid.setRuoli(setRuoliInsert);
			try {
				request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
			} catch (Exception e) {
				listaErrori.add("Problemi caricamento ruoli");
				request.getRequestDispatcher("/jsp/utente/insertutente.jsp").forward(request, response);
				e.printStackTrace();
			}
			request.setAttribute("utenteAttribute", utenteInvalid);
			request.setAttribute("listaErroriAttribute", listaErrori);
			request.getRequestDispatcher("/jsp/utente/insertutente.jsp").forward(request, response);
			return;
		}

		// logica + costruzione oggetto input

		Utente utenteInsert = new Utente(userInputParam, passInputParam, nomeInputParam, cognomeInputParam);
		utenteInsert.setRuoli(setRuoliInsert);

		try {
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInsert);
			request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().listAll());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/jsp/utente/listutenti.jsp").forward(request, response);

	}
}
