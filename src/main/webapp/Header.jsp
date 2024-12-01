<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    // Verificar si la sesión del usuario está activa
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("InicioSession.jsp"); // Redirige si no hay sesión activa
        return; 
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Horóscopo Chino</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/css/styles.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #d9534f;">
		
		<div class="container-fluid">

			<a class="navbar-brand d-flex align-items-center" href="#"> <i
				class="fa-solid fa-dragon"></i> <span class="ms-2">Horóscopo
					Chino</span>
			</a>


			<div class="d-flex justify-content-end">
				<span class="navbar-text text-white me-3"> ${usuario.nombre}
				</span> <a href="IniciarSessionServlet" class="btn btn-light">Cerrar
					sesión</a>
			</div>
		</div>
	</nav>

	<script src="https://kit.fontawesome.com/b2231ac62a.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
