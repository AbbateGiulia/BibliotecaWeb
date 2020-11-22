<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>update utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

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
				<h5>Update Utente</h5>
			</div>
			<div class='card-body'>



				<h6 class="card-title">Update Utente</h6>

				<form method="post"
					action="${pageContext.request.contextPath}/admin/ExecuteUpdateUtenteServlet"
					class="needs-validation" id="form">

						
						<c:set var="utente" 
					value="${requestScope.utenteAttribute}" />
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" name="id" id="id" class="form-control"
								value="${utente.id}" required>
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome </label> 
							<input type="text" name="nome" id="nome"
								class="form-control" value="${utente.nome}" placeholder="Inserire il nome" required>
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Cognome </label> <input type="text" name="cognome"
								id="cognome" class="form-control" value="${utente.cognome}"
								placeholder="Inserire il cognome" required>
						</div>
					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="username" class="form-control" value="${utente.username}" placeholder="Inserire username" required>
						</div>
					</div>

					<div class="row">
					
						  <div class="form-check">
						  <label>Ruolo </label>
						  <c:forEach items="${requestScope.listaRuoliAttribute}" var="ruolo"> <br>						  
					    <input value="${ruolo.id}" id="ruolo" name="ruolo" type="checkbox" required
					    	<c:forEach items="${utente.ruoli}" var="ruoloUtente">
					    	
					    	${ruoloUtente.id eq ruolo.id ? 'checked' : ''}
					    	
					    	</c:forEach>
					    	
					    	>
						    <label >${ruolo.codice}</label>						   
						    </c:forEach>
						     </div>
						  </div>
							
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Stato </label>
							<div class="form-row">
								<div class="form-group col-lg-12">
									<select name="stato" id="stato" class="form-control" id="exampleFormControlSelect1" required>
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

				<script src="${pageContext.request.contextPath}/assets/js/myscript.js"></script>
				<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
				<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.slim.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js" integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg==" crossorigin="anonymous"></script>
				<script>
				$(document).ready(function () {

					$('#form').validate({
					    rules: {
					        nome: {
					            minlength: 2,
					            required: true
					        },
					        cognome: {
					        	minlength: 2,
					            required: true
					        },
					        username: {   
					        	minlength: 2,
					            required: true
					        },
					        ruolo: {   
					            required: true
					        },
					        stato: {   
						            required: true
						    },
					        
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