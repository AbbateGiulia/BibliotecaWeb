package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Genere;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;

/**
 * Servlet implementation class ExecuteSearchLibroServlet
 */
@WebServlet("/cerca/ExecuteSearchLibroServlet")
public class ExecuteSearchLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchLibroServlet() {
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
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere").toUpperCase();
		String idAutoreParam = request.getParameter("idAutore");
		
		AutoreService service =MyServiceFactory.getAutoreServiceInstance();
		Autore autoreCriteria;

		try {
			Genere genere =  !genereInputParam.isEmpty()  ? Enum.valueOf(Genere.class, genereInputParam) : null;
			Libro libroCriteria = new Libro(titoloInputParam, genere, tramaInputParam);
			// SE ID ARRIVA -1?
			autoreCriteria = service.caricaSingoloElemento(Long.parseLong(idAutoreParam));
			libroCriteria.setAutore(autoreCriteria);
			request.setAttribute("listaLibriAttribute",
					MyServiceFactory.getLibroServiceInstance().ricercaLibro(libroCriteria));
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/jsp/libro/searchlibro.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/jsp/libro/listlibri.jsp").forward(request, response);

	}

}
