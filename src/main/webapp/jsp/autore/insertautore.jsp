<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Insert Autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show ${requestScope.listaErroriAttribute == null?'d-none': ''}"
			role="alert">
			<c:forEach items="${requestScope.listaErroriAttribute}" var="errore">
				<ul>
					<li> <c:out value="${errore}"></c:out> </li>
				</ul>			
			</c:forEach>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Insert Autore</h5>
			</div>
			<div class='card-body'>
		
				
				<h6 class="card-title">
					Inserisci Autore
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/insert/ExecuteInsertAutoreServlet"
					class="needs-validation" novalidate>
					
					<c:set var="autore"
					value="${requestScope.autoreAttribute}" />
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" name="nomeCriteria"
								id="nomeCriteria" class="form-control"
								value="${requestScope.nomeCriteria}" required>
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" name="cognomeCriteria"
								id="cognomeCriteria" class="form-control"
								value="${requestScope.cognomeCriteria}" required>
						</div>
					</div>
					


					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Nome </label> 
							<input type="text" name="nome" id="nome" class="form-control"
								placeholder="Inserire il nome" value="${autore.nome}" required>
								<div class="invalid-tooltip">Inserisci un nome valido.</div>
						</div>
					</div>
					
					
					
					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Cognome </label> <input
								type="text" class="form-control" name="cognome" id="cognome"
								placeholder="Inserire cognome" value="${autore.cognome}" required >
								<div class="invalid-tooltip">Inserisci una trama valida.</div>
						</div>
					</div>
				
				<div class="form-row">	
				<div class="form-group col-md-3">
				<label for="example-date-input">Date</label>				
					<div class="form-group row"> 		 
				  			<div class="col-10">
				    	<input class="form-control" type="date" name="dataNascita" id="dataNascita" value="${autore.dataNascita}"  required>
				    	</div>
				  	</div>
					</div>
				</div>
					

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>
				
				<script src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>