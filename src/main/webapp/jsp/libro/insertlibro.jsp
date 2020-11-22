<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>aggiungi libro</title>

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
		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>New Libro</h5>
			</div>
			<div class='card-body'>
		
				
				<h6 class="card-title">
					Inserisci libro
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/insert/ExecuteInsertLibroServlet"
					class="needs-validation" id="form">
					
					<c:set var="libro"
					value="${requestScope.libroAttribute}" />


					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Titolo </label> 
							<input type="text" name="titolo" id="titolo" class="form-control"
								placeholder="Inserire il titolo" value="${libro.titolo}" required>
								<div class="invalid-tooltip">Inserisci un titolo valido.</div>
						</div>
						</div>

						<div class="form-row">
						<div class="form-group col-md-3">
							<label>Autore </label> 
							<div class="form-row">
								<div class="form-group col-lg-12">
							<select name="idAutore" id="idAutore" class="form-control" id="exampleFormControlSelect1" required>							
							<option selected value="${libro.autore.id}"> 
							<c:out value="${libro.autore.nome} ${libro.autore.cognome}"/>
							</option>							
							<c:forEach items ="${requestScope.listaAutoriAttribute}" var ="autore"> 
								<option value="${autore.id}">
								<c:out value="${autore.nome} ${autore.cognome}"/>  
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
								placeholder="Inserire trama" value="${libro.trama}" required>
								<div class="invalid-tooltip">Inserisci una trama valida.</div>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Genere </label> 
							<input type="text" class="form-control" name="genere" id="genere"
								placeholder="Inserire trama" value="${libro.genere}" required>
								<div class="invalid-tooltip">Inserisci un genere valido.</div>
						</div>				
					</div>
					
					

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>
				
				<script src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>
				<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
				<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.slim.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js" integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg==" crossorigin="anonymous"></script>
				<script>
				$(document).ready(function () {

					$('#form').validate({
					    rules: {
					        titolo: {
					            minlength: 2,
					            required: true
					        },
					        idAutore: {
					        	minlength: 2,
					            required: true
					        },
					        trama: {   
					        	minlength: 2
					            required: true
					        }
					        genere: {   
					        	minlength: 2
					            required: true
					        }
					        
					    },
					    highlight: function (element) {
					        $(element).closest('.form-control').removeClass('success').addClass('error');
					    },
					    success: function (element) {
					        element.text('OK!').addClass('valid')
					            .closest('.form-control').removeClass('error').addClass('success');
					    }
					});
					});
				</script>
				


				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>