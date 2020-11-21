package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.util.Util;

/**
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/update/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//input form inserimente
				String nomeInput = request.getParameter("nome");
				String cognomeInput = request.getParameter("cognome");
				String dataInput = request.getParameter("dataNascita");
				LocalDate data = !Util.isEmptyOrNull(dataInput)?LocalDate.parse(dataInput): null;;
				String idInput =request.getParameter("id");
				
				Autore autoreInvalid = new Autore(nomeInput,cognomeInput,data);
				
				//blocco il tentativo di eliminare dei campi e genero lista errori
				
				if (Util.isEmptyOrNull(nomeInput)|| Util.isEmptyOrNull(cognomeInput)
						|| Util.isEmptyOrNull(dataInput)) {
					
					List<String> listaErrori= new ArrayList<String>();
					
					if(Util.isEmptyOrNull(nomeInput)) {
						listaErrori.add("Inserisci un nome valido");
					}
					
					if(Util.isEmptyOrNull(cognomeInput)) {
						listaErrori.add("Inserisci un cognome valido");
					}
					
					if(Util.isEmptyOrNull(dataInput)) {
						listaErrori.add("Inserisci una data valida");
					}
					
					request.setAttribute("autoreAttribute", autoreInvalid);
					request.setAttribute("listaErroriAttribute", listaErrori);
					request.setAttribute("id", Long.parseLong(idInput)); //deve tornare id dell'entità
					request.getRequestDispatcher("/jsp/autore/updateautore.jsp").forward(request, response);
					return;
				}
				
				//Dopo i controlli, cerco oggetto e poi modifico 
				
				Autore autoreUpdate= null;
				try {
					autoreUpdate = MyServiceFactory.getAutoreServiceInstance()
							.caricaSingoloElemento(Long.parseLong(idInput));
				} catch (NumberFormatException e1) {
					request.getRequestDispatcher("/jsp/autore/updateautore.jsp").forward(request, response);
					e1.printStackTrace();
				} catch (Exception e1) {
					request.getRequestDispatcher("/jsp/autore/updateautore.jsp").forward(request, response);
					e1.printStackTrace();
				}
				
				autoreUpdate.setNome(nomeInput);
				autoreUpdate.setCognome(cognomeInput);
				autoreUpdate.setDataNascita(data);
				
//				Autore autoreUpdate = new Autore(nomeInput,cognomeInput,data);
//				autoreUpdate.setId(Long.parseLong(idInput));
				try {
					MyServiceFactory.getAutoreServiceInstance().aggiorna(autoreUpdate);
				} catch (Exception e) {
					request.getRequestDispatcher("/jsp/autore/updateautore.jsp").forward(request, response);
					e.printStackTrace();
				}	
				
				
				request.getRequestDispatcher("/visualizza/ListAutoriServlet").forward(request, response);
				
	}

}
