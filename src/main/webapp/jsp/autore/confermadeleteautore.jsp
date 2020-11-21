<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Real delete</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">

	
		<div class='card'>
		<c:set var= "autore" value="${requestScope.autoreAttribute}"/>
		    <div class='card-header'>
		        <h5>Sei sicuro di voler eliminare l'autore ${autore.cognome} ?</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class=" btn btn-danger" href="${pageContext.request.contextPath}/delete/ExecuteDeleteAutoreServlet?IdDaInviareComeParametro=${autore.id}">Delete</a>
		    	<a class="btn btn-primary" href="${pageContext.request.contextPath}/visualizza/ListAutoriServlet">Back</a>
		    </div>
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>