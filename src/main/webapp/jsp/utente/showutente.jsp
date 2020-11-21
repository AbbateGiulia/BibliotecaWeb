
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">		    	
		    	<c:set var= "articolo" value="${requestScope.utenteShow}"/>
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9"><c:out value="${utenteShow.nome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9"><c:out value="${utenteShow.cognome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">User:</dt>
				  <dd class="col-sm-9"><c:out value="${utenteShow.username}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruoli:</dt>
				  <dd class="col-sm-9"> 
				  <c:forEach items ="${utenteShow.getRuoli()}" var ="ruolo">
		               <c:out value =" ${ruolo.getDescrizione()}"/>
		          </c:forEach>
		          </dd>
		    	</dl>
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/admin/ListUtentiServlet"
		        	class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>