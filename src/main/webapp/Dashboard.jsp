<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Header.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link href="assets/css/styles.css" rel="stylesheet" type="text/css">
<style>

/* Estilo para los botones */
.btn-block {
	padding: 20px;
	font-size: 16px;
	font-weight: bold;
}

.row {
	margin-top: 120px;
}

.col-md-3 {
	margin-bottom: 20px;
}

.text-white {
	color: #fff !important;
}
</style>
</head>
<body>

	<div class="container mt-5">
		<h2 class="text-center text-danger">
			Bienvenido,
			<c:out value="${sessionScope.usuario.nombre}" />
		</h2>
		<p class="text-center text-danger mb-4">¿Qué te gustaría hacer?</p>

		<div class="row justify-content-center">
			<div class="col-md-3 mb-3">
				<form action="ConsultarHoroscopoServlet">
					<button type="submit" class="btn btn-primary btn-block shadow-sm">
						<i class="fa-solid fa-wand-magic-sparkles"></i> Conoce tu animal
					</button>
				</form>
			</div>

			<div class="col-md-3 mb-3">
				<form action="BuscarUsuariosServlet">
					<button type="submit" class="btn btn-success btn-block shadow-sm">
						<i class="fas fa-users mr-2"></i> Buscar usuarios
					</button>
				</form>
			</div>

			<div class="col-md-3 mb-3">
				<form action="ModificarUsuarioServlet" method="POST">
					<button type="submit" class="btn btn-warning btn-block shadow-sm">
						<i class="fas fa-edit mr-2"></i> Modificar datos
					</button>
				</form>
			</div>

			<div class="col-md-3 mb-3">
				<form action="EliminacionUsuario.jsp">
					<button type="submit" class="btn btn-danger btn-block shadow-sm">
						<i class="fas fa-trash-alt mr-2"></i> Eliminar cuenta
					</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
	<!-- Scripts de Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
