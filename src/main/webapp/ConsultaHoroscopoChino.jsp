<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Horóscopo Chino</title>
    <link href="assets/css/styles.css" rel="stylesheet" type="text/css">
    <!-- Estilos de Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    
    <style>
        body {
            background-image: url('assets/img/bg-dragon.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }

        /* Estilo adicional para centrar el contenedor y agregar sombra */
        .content-container {
            margin-top: 100px;
            max-width: 350px;
            margin-left: auto;
            margin-right: auto;
            padding: 30px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .content-container h2 {
            font-size: 24px;
            margin-bottom: 30px;
        }

        .content-container h1 {
            font-size: 1.25rem;
            margin-top: 20px;
            color: #d9534f;
        }

        .btn {
            padding: 12px;
            font-size: 16px;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        /* Estilo para el link de volver */
        .btn-back {
            margin-top: 20px;
        }

        /* Estilo para el mensaje de error */
        .alert {
            margin-top: 20px;
        }
    </style>
</head>

<body>
<%@ include file="Header.jsp" %> 
    <div class="container content-container">
        <h2 class="text-center text-danger">
            <i class="fas fa-calendar-alt"></i> Bienvenido, ${usuario.nombre}
        </h2>

        <!-- Mostrar el mensaje de error si está presente -->
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">
                <strong>Error:</strong> ${requestScope.error}
            </div>
        </c:if>

        <!-- Mostrar el horóscopo si está presente -->
        <c:if test="${not empty horoscopo}">
            <h3>
                <i class="fas fa-paw"></i> Tu animal del horóscopo chino es: ${horoscopo.animal}
            </h3>
        </c:if>

        <!-- Enlace para volver al dashboard -->
        <a href="Dashboard.jsp" class="btn btn-secondary mt-3 btn-back">
            <i class="fas fa-arrow-left"></i> Volver al Dashboard
        </a>
    </div>

    <!-- Scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <%@ include file="Footer.jsp" %>
</body>

</html>
