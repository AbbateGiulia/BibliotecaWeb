package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteInsertLibroServlet
 */
@WebServlet("/insert/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertLibroServlet() {
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
		AutoreService service = MyServiceFactory.getAutoreServiceInstance();
		Autore result = null;

		// se la validazione fallisce torno in pagina con lista errori
		if (Util.isEmptyOrNull(titoloInputParam) || Util.isEmptyOrNull(tramaInputParam)
				|| Util.isEmptyOrNull(genereInputParam)) {

			List<String> listaErrori = new ArrayList<String>();

			// carico autore e genere e creo libro "non valido"
			try {
				result = !Util.isEmptyOrNull(idAutoreParam)? service.caricaSingoloElemento(Long.parseLong(idAutoreParam)):null;
			} catch (NumberFormatException e1) {
				listaErrori.add("caricamento autori fallito");
				request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
				e1.printStackTrace();
			} catch (Exception e1) {
				listaErrori.add("caricamento autori fallito");
				request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
				e1.printStackTrace();
			}
			Genere genere = !Util.isEmptyOrNull(genereInputParam)?Enum.valueOf(Genere.class, genereInputParam):null;
			Libro libroInvalid = new Libro(titoloInputParam, genere, tramaInputParam);
			libroInvalid.setAutore(result);

			if (Util.isEmptyOrNull(titoloInputParam)) {
				listaErrori.add("Inserisci un titolo valido");
			}

			if (Util.isEmptyOrNull(tramaInputParam)) {
				listaErrori.add("Inserisci una trama valida");
			}

			if (Util.isEmptyOrNull(genereInputParam)) {
				listaErrori.add("Inserisci un genere valido");
			}
			
			if (Util.isEmptyOrNull(idAutoreParam)) {
				listaErrori.add("Inserisci un autore valido");
			}

			try {
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e) {
				listaErrori.add("caricamento autori fallito");
				e.printStackTrace();
			}
			request.setAttribute("listaGeneriAttribute", Genere.conversioneGenere.values());
			request.setAttribute("libroAttribute", libroInvalid);
			request.setAttribute("listaErroriAttribute", listaErrori);

			request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
			return;
		}

		try {
			result = service.caricaSingoloElemento(Long.parseLong(idAutoreParam));
			Genere genere = Enum.valueOf(Genere.class, genereInputParam);

			Libro libroInsert = new Libro(titoloInputParam, genere, tramaInputParam);

			libroInsert.setAutore(result);

			MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libroInsert);
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			
			try {
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			} catch (Exception e1) {
				
				request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
				e1.printStackTrace();
			}
			request.setAttribute("listaGeneriAttribute", Genere.conversioneGenere.values());
			
			request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
			
		}

		// andiamo ai risultati
		try {
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
		} catch (Exception e) {
			request.getRequestDispatcher("/jsp/libro/insertlibro.jsp").forward(request, response);
			e.printStackTrace();
		}
		request.getRequestDispatcher("/jsp/libro/listlibri.jsp").forward(request, response);

	}
}
