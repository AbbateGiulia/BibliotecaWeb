package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/admin/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idInput =request.getParameter("IdDaInviareComeParametro");
		try {
			Utente utenteAttribute = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idInput));
			request.setAttribute("utenteAttribute", utenteAttribute);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			request.setAttribute("listaRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ListaStatoAttribute", StatoUtente.allStato);
		request.getRequestDispatcher("/jsp/utente/updateutente.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
