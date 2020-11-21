<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>cerca utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

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
				<h5>Search Utente</h5>
			</div>
			<div class='card-body'>


			
				<h6 class="card-title">Cerca Utente</h6>

				<form method="post"
					action="${pageContext.request.contextPath}/admin/ExecuteSearchUtenteServlet"
					class="needs-validation" novalidate>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome </label> <input type="text" name="nome" id="nome"
								class="form-control" placeholder="Inserire il nome">
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Cognome </label> <input type="text" name="cognome"
								id="cognome" class="form-control"
								placeholder="Inserire il cognome">
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="username" class="form-control" placeholder="Inserire username">
						</div>
					</div>
<!--  
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Ruolo </label>
							<div class="form-row">
								<div class="form-group col-lg-12">
									<select name="ruolo" id="ruolo" class="form-control" id="exampleFormControlSelect1">
										<option selected value="">Nessun Ruolo</option>
										<c:forEach items="${requestScope.listaRuoliAttribute}" var="ruolo">
										<option value="${ruolo.id}">
										<c:out value="${ruolo.codice}" />
										</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
	-->				
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Stato </label>
							<div class="form-row">
								<div class="form-group col-lg-12">
									<select name="stato" id="stato" class="form-control" id="exampleFormControlSelect1">
										<option selected value="">Nessuno Stato</option>
										<c:forEach items="${requestScope.ListaStatoAttribute}" var="stato">
										<option value="${stato}">
										<c:out value="${stato}" />
										</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					



					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>

				<script
					src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>