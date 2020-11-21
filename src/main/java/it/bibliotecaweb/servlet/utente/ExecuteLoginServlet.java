package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.CodiceRuolo;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;


/**
 * Servlet implementation class ExecuteLoginServlet
 */
@WebServlet("/ExecuteLoginServlet")
public class ExecuteLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");

		 
		if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			return;
		}	
		UtenteService service= MyServiceFactory.getUtenteServiceInstance();
		Utente result = null;
		try {
			result=service.cercaUser(usernameInput);
			if(result== null||!result.getPassword().equals(passwordInput)) {
				request.setAttribute("errorMessage", "User o password errati!");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				return;	
			}
			if(StatoUtente.DISABILITATO.equals(result.getStato())) {
				request.setAttribute("errorMessage", "Utente disabilitato!");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				return;	
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		
		boolean admin = false;
		boolean classic = false;
		boolean guest = false;

		for(Ruolo ruolo : result.getRuoli()) {
			if (CodiceRuolo.ADMIN_ROLE == ruolo.getCodice()) {
				admin = true;
			}
			if(CodiceRuolo.CLASSIC_ROLE == ruolo.getCodice()) {
				classic = true;
			}
			if(CodiceRuolo.GUEST_ROLE == ruolo.getCodice()) {
				guest = true;
			}
		}
		 request.getSession().setAttribute("isGuest", guest);
		 request.getSession().setAttribute("isClassic", classic);
		 request.getSession().setAttribute("isAdmin", admin);
		 request.getSession().setAttribute("utente", result);
		 
		 request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		
		
	}
			
		}

		



