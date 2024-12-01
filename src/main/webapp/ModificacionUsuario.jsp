<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Usuario</title>
    <!-- Link a Bootstrap 4 desde el CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="Header.jsp" %>
    <div class="container form-container">
        <h2 class="text-center text-danger">
            <i class="fas fa-user-circle"></i> Modificar Usuario
        </h2>

        <!-- Mostrar mensaje de éxito o error -->
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> Las contraseñas no coinciden o hubo un problema en la actualización.
            </div>
        </c:if>
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> ${requestScope.error}
            </div>
        </c:if>
        <c:if test="${not empty requestScope.success}">
            <div class="alert alert-success" role="alert">
                <strong>Éxito:</strong> Usuario actualizado correctamente.
            </div>
        </c:if>
   
        <form action="ModificarUsuarioServlet" method="POST">
            <input type="hidden" name="id" value="${usuario.id}" />
            
            <div class="form-group">
                <label for="nombre">Nombre Completo</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" required>
            </div>
            <div class="form-group">
                <label for="username">Nombre de Usuario</label>
                <input type="text" class="form-control" id="username" name="username" value="${usuario.username}" readonly required>
            </div>
            <div class="form-group">
                <label for="email">Correo Electrónico</label>
                <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required>
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${usuario.fechaNacimiento != null ? usuario.fechaNacimiento.toLocalDate() : ''}" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" value="${usuario.password}" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirmar Contraseña</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="${usuario.password}" required>
            </div>

            <div class="d-flex">
                <button type="submit" class="btn btn-danger">Actualizar</button>
                <a href="Dashboard.jsp" class="btn btn-secondary">Volver al Dashboard</a>
            </div>

        </form>
    </div>
<%@ include file="Footer.jsp" %>
</body>
</html>
