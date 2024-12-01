<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Listado de Usuarios</title>
<link href="assets/css/styles.css" rel="stylesheet" type="text/css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="Header.jsp"%>

	<div class="container mt-4"
		style="height: calc(100vh - 280px); overflow-y: auto;">
		<h2 class="text-center mb-4">Lista de Usuarios</h2>

		<!-- Formulario de búsqueda -->
		<form action="BuscarUsuariosServlet" method="GET" class="mb-4">
			<div class="input-group">
				<input type="text" name="busqueda" class="form-control"
					placeholder="Buscar por nombre, correo o username" required>
				<div class="input-group-append">
					<button class="btn btn-danger" type="submit">
						<i class="fas fa-search"></i> Buscar
					</button>
				</div>
			</div>
		</form>

		<!-- Verificación si hay usuarios disponibles -->
		<c:if test="${empty usuarios}">
			<c:if test="${not empty param.busqueda}">
				<div class="alert alert-warning text-center">
					<strong>No hay resultados para "<c:out
							value="${param.busqueda}" />".
					</strong>
				</div>
			</c:if>
		</c:if>

		<!-- Tabla de usuarios -->
		<c:if test="${not empty usuarios}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Username</th>
						<th>Email</th>
						<th>Fecha de Nacimiento</th>
						<th>Animal Chino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td>${usuario.id}</td>
							<td>${usuario.nombre}</td>
							<td>${usuario.username}</td>
							<td>${usuario.email}</td>
							<td>${usuario.fechaNacimiento}</td>
							<td>${usuario.animal}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<!-- Botón para volver a listar todos los usuarios -->
		<div class="text-center mt-3">
			<c:if test="${not empty param.busqueda}">
				<a href="BuscarUsuariosServlet" class="btn btn-secondary">Volver
					a listar todos los usuarios</a>
			</c:if>
			<a href="Dashboard.jsp" class="btn btn-secondary">Volver al
				Dashboard</a>
		</div>
	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>
