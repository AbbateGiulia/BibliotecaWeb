
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
		    	<h6 class="card-title">
				<c:if test = "${sessionScope.isAdmin ==true || sessionScope.isClassic ==true  }">
					<a class="btn btn-primary " href="${pageContext.request.contextPath}/insert/PrepareInsertLibroServlet">Add New Libro</a>
					</c:if>
				</h6>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>		                      
		                        
		                        <th>Titolo</th>
		                        <th>Genere</th>
		                        <th>Trama</th>
		                        <th>Autore</th>
		                        <th>Azioni</th>
		                        		                        
		                    </tr>
		                </thead>
		                <tbody>
		                		
		                			                		
		                		<c:forEach items ="${requestScope.listaLibriAttribute}" var ="libro"> 
		                    <tr >
		               
		                        <td><c:out value =" ${libro.titolo}"/></td>
		                        <td><c:out value =" ${libro.genere}"/></td>
		                        <td><c:out value =" ${libro.trama}"/></td>
		                        <td><c:out value =" ${libro.autore.nome} ${libro.autore.cognome}"/></td>
		                        <td>
									
									<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/visualizza/VisualizzaLibroServlet?IdDaInviareComeParametro=${libro.id}">Visualizza</a>
									<c:if test = "${sessionScope.isAdmin ==true || sessionScope.isClassic ==true  }">			
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/update/PrepareUpdateLibroServlet?IdDaInviareComeParametro=${libro.id}">Edit</a>									
									
									<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/delete/PrepareDeleteLibroServlet?IdDaInviareComeParametro=${libro.id}">Delete</a>
									</c:if>
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