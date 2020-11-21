package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteUtenteServlet
 */
@WebServlet("/admin/ExecuteDeleteUtenteServlet")
public class ExecuteDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IdInput=request.getParameter("IdDaInviareComeParametro");
		try {
			Utente utenteAttribute = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElemento(Long.parseLong(IdInput));
			MyServiceFactory.getUtenteServiceInstance().rimuovi(utenteAttribute);
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().listAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/jsp/utente/listutenti.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
