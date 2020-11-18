package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}	
		UtenteService service= MyServiceFactory.getUtenteServiceInstance();
		Utente result = null;
		try {
			result=service.cercaUser(usernameInput);
			if(result== null||!result.getPassword().equals(passwordInput)) {
				request.setAttribute("errorMessage", "User o password errati!");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;	
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		 request.getSession().setAttribute("oggettoSessione", result);
		 
		 request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		
		
	}

}
