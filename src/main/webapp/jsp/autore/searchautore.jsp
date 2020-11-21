<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			${requestScope.errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>


		<div class='card'>
			<div class='card-header'>
				<h5>Search Autore</h5>
			</div>
			<div class='card-body'>
		
				
				<h6 class="card-title">
					Cerca Autore
				</h6>

				<form method="get" action="${pageContext.request.contextPath}/cerca/ExecuteSearchAutoreServlet"
					class="needs-validation" novalidate>


					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Nome </label> 
							<input type="text" name="nome" id="nome" class="form-control"
								placeholder="Inserire il nome">
								<div class="invalid-tooltip">Inserisci un nome valido.</div>
						</div>
					</div>
					
					
					
					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Cognome </label> <input
								type="text" class="form-control" name="cognome" id="cognome"
								placeholder="Inserire cognome" >
								<div class="invalid-tooltip">Inserisci una trama valida.</div>
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