<%@page import="java.util.List"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>	   
		    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/PrepareInsertSoloArticoloServlet">Add New</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>		                      
		                        
		                        <th>Nome</th>
		                        <th>Cognome</th>
		                        <th>Username</th>		                 
		                        <th>Stato</th>
		                        <th>Ruolo</th>
		                        		                        
		                    </tr>
		                </thead>
		                <tbody>
		                		
		                			                		
		                		<c:forEach items ="${requestScope.listaUtentiAttribute}" var ="utente"> 
		                    <tr >
		               
		                        <td><c:out value =" ${utente.nome}"/></td>
		                        <td><c:out value =" ${utente.cognome}"/></td>
		                        <td><c:out value =" ${utente.username}"/></td>
		                        <td><c:out value =" ${utente.stato}"/></td>
		                        <c:forEach items ="${utente.getRuoli()}" var ="ruolo">
		                        <td><c:out value =" ${ruolo.getDescrizione()}"/></td>
		                         </c:forEach>
		                        <td>
									
									<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/VisualizzaLibroServlet?IdDaInviareComeParametro=${libro.id}">Visualizza</a>
												
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/PrepareUpdateSoloArticoloServlet?IdDaInviareComeParametro=${articolo.id}">Edit</a>									
									
									<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/ExecuteDeleteSoloArticoloServlet?id=${articolo.id}">Delete</a>
									
								</td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>