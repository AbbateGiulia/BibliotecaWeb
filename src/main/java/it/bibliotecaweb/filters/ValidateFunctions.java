package it.bibliotecaweb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class ValidateFunctions
 */
@WebFilter(urlPatterns = { "/insert/*", "/update/*", "/delete/*" })
public class ValidateFunctions implements Filter {

	private String contesto;

	/**
	 * Default constructor.
	 */
	public ValidateFunctions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; // cast della ServletRequest a
																				// HttpServletRequest
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; // cast della ServletResponse a
																					// HttpServletResponse

		
		if((boolean) httpServletRequest.getSession().getAttribute("isAdmin") ||
				(boolean) httpServletRequest.getSession().getAttribute("isClassic")) {
			chain.doFilter(request, response);
		}else {
			httpServletResponse.sendRedirect(contesto);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath()+ "/jsp/home.jsp"; // configuro il path che dovrà seguire la catena di
																	// filtri
	}

}
