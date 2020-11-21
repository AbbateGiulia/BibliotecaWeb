package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteAutoreServlet
 */
@WebServlet("/delete/PrepareDeleteAutoreServlet")
public class PrepareDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idInput=request.getParameter("IdDaInviareComeParametro");
		Autore autoreAttribute= null;
		try {
			autoreAttribute=MyServiceFactory.getAutoreServiceInstance()
			.caricaSingoloElemento(Long.parseLong(idInput));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!autoreAttribute.getLibri().isEmpty()) {
			request.setAttribute("errorMessage", "l'autore contiene libri , non puoi cancellarlo");
			request.getRequestDispatcher("/visualizza/ListAutoriServlet").forward(request, response);
			return;
		}
		request.setAttribute("autoreAttribute", autoreAttribute);		
		
		request.getRequestDispatcher("/jsp/autore/confermadeleteautore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
