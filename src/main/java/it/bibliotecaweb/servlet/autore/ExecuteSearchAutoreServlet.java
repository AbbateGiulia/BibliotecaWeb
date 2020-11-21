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
 * Servlet implementation class ExecuteSearchAutoreServlet
 */
@WebServlet("/cerca/ExecuteSearchAutoreServlet")
public class ExecuteSearchAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		
		Autore autoreCriteria= new Autore (nomeInput,cognomeInput);
		
		try {
			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().ricercaAutore(autoreCriteria));
			request.setAttribute("nomeCriteria", nomeInput);
			request.setAttribute("cognomeCriteria", cognomeInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/jsp/autore/listautori.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String nomeInput = request.getParameter("nome");
//		String cognomeInput = request.getParameter("cognome");
//		
//		Autore autoreCriteria= new Autore (nomeInput,cognomeInput);
//		
//		try {
//			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().ricercaAutore(autoreCriteria));
//			request.setAttribute("nomeCriteria", nomeInput);
//			request.setAttribute("cognomeCriteria", cognomeInput);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		request.getRequestDispatcher("/jsp/autore/listautori.jsp").forward(request, response);
	}

}
