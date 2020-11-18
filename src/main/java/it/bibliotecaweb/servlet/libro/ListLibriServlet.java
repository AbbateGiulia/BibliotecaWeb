package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;


/**
 * Servlet implementation class ListLibriServlet
 */
@WebServlet("/visualizza/ListLibriServlet")
public class ListLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListLibriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//preparo la lista di articoli
		try {
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		request.getRequestDispatcher("../jsp/libro/listlibri.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
