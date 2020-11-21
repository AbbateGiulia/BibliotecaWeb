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
import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/admin/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateUtenteServlet() {
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
				String idInputParam = request.getParameter("id");
				String nomeInputParam = request.getParameter("nome");
				String cognomeInputParam = request.getParameter("cognome");
				String userInputParam = request.getParameter("username");		
				Set<Ruolo> setRuoliUpdate = new HashSet<Ruolo>();
				String statoInput = request.getParameter("stato");
				
				
				// validazione campi required e creazione utente "non valido"
				// con relativi mess di errore
				if (Util.isEmptyOrNull(nomeInputParam) || Util.isEmptyOrNull(cognomeInputParam)
						|| Util.isEmptyOrNull(userInputParam)) {

					List<String> listaErrori = new ArrayList<String>();

					Utente utenteInvalid = new Utente(userInputParam, nomeInputParam, cognomeInputParam);

					if (Util.isEmptyOrNull(nomeInputParam)) {
						listaErrori.add("Inserisci un nome valido");
					}

					if (Util.isEmptyOrNull(cognomeInputParam)) {
						listaErrori.add("Inserisci una cognome valido");
					}

					if (Util.isEmptyOrNull(userInputParam)) {
						listaErrori.add("Inserisci un username valido");
					}
					if(Util.isEmptyOrNull(request.getParameter("ruolo")) ) {
						listaErrori.add("Inserisci almeno un ruolo");
					}else {
					for (String s : request.getParameterValues("ruolo")) {
						
							Ruolo ruoloUpdate = null;
							try {
								ruoloUpdate = MyServiceFactory.getRuoloServiceInstance()
										.caricaSingoloElemento(Long.parseLong(s));
							} catch (NumberFormatException e) {

								listaErrori.add("Problemi inserimento ruolo");
								request.getRequestDispatcher("/jsp/utente/updateutente.jsp").forward(request, response);
								e.printStackTrace();

							} catch (Exception e) {

								listaErrori.add("Problemi inserimento ruolo");
								request.getRequestDispatcher("/jsp/utente/updateutente.jsp").forward(request, response);
								e.printStackTrace();
							}
							
							setRuoliUpdate.add(ruoloUpdate);
						}
					}		
					utenteInvalid.setRuoli(setRuoliUpdate);
					try {
						request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
					} catch (Exception e) {
						listaErrori.add("Problemi caricamento ruoli");
						request.getRequestDispatcher("/jsp/utente/updateutente.jsp").forward(request, response);
						e.printStackTrace();
					}
					utenteInvalid.setId(Long.parseLong(idInputParam));
					utenteInvalid.setStato(Enum.valueOf(StatoUtente.class, statoInput ));
					request.setAttribute("ListaStatoAttribute", StatoUtente.allStato);
					request.setAttribute("utenteAttribute", utenteInvalid);
					request.setAttribute("listaErroriAttribute", listaErrori);
					request.getRequestDispatcher("/jsp/utente/updateutente.jsp").forward(request, response);
					return;
				}
				
				for (String s : request.getParameterValues("ruolo")) {
					if (Util.isEmptyOrNull(s)) {
						request.setAttribute("errorMessage", "devi inserire almeno un ruolo");
						try {
							request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
						request.getRequestDispatcher("/jsp/utente/insertutente.jsp").forward(request, response);
						return;
					} else {
						Ruolo ruoloInsert= null;
						try {
							ruoloInsert = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(Long.parseLong(s));
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setRuoliUpdate.add(ruoloInsert);
					}
				}
				
				// logica + costruzione oggetto input
				
				Utente utenteUpdate = null;
				try {
					utenteUpdate = MyServiceFactory.getUtenteServiceInstance()
							.caricaSingoloElemento(Long.parseLong(idInputParam));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				utenteUpdate.setNome(nomeInputParam); 
				utenteUpdate.setCognome(cognomeInputParam);
				utenteUpdate.setUsername(userInputParam);
				utenteUpdate.setRuoli(setRuoliUpdate);
				utenteUpdate.setStato(Enum.valueOf(StatoUtente.class, statoInput ));
				

				try {
					MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteUpdate);
					request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().listAll());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				// andiamo ai risultati
				request.getRequestDispatcher("/jsp/utente/listutenti.jsp").forward(request, response);

	}

}
