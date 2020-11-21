<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>cerca libro</title>

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
				<h5>Search Libro</h5>
			</div>
			<div class='card-body'>
		
				
				<h6 class="card-title">
					Cerca il tuo libro
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/cerca/ExecuteSearchLibroServlet"
					class="needs-validation" novalidate>


					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Titolo </label> 
							<input type="text" name="titolo" id="titolo" class="form-control"
								placeholder="Inserire il titolo">
								<div class="invalid-tooltip">Inserisci un titolo valido.</div>
						</div>
					</div>
					
					
					
						<div class="form-row">
						<div class="form-group col-md-3">
							<label>Autore <span class="text-danger">*</span></label> 
							<div class="form-row">
								<div class="form-group col-lg-12">
							<select name="idAutore" id="idAutore" class="form-control" id="exampleFormControlSelect1">
							<option selected value="-1"> Nessun Autore </option> 
							<c:forEach items ="${requestScope.listaAutoriAttribute}" var ="autore"> 
								<option value="${autore.id}">
								<c:out value="${autore.nome }"/>  
								</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</div>
					
					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Trama </label> <input
								type="text" class="form-control" name="trama" id="trama"
								placeholder="Inserire trama" >
								<div class="invalid-tooltip">Inserisci una trama valida.</div>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Genere <span class="text-danger">*</span></label> 
							<input type="text" class="form-control" name="genere" id="genere"
								placeholder="Inserire trama" >
								<div class="invalid-tooltip">Inserisci un genere valido.</div>
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