package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ListUtentiServlet
 */
@WebServlet("/admin/ListUtentiServlet")
public class ListUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//preparo la lista di utenti
				try {
					request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().listAll());
				} catch (Exception e) {
					request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
					e.printStackTrace();
					return;
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
