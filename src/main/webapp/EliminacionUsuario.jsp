<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eliminar Cuenta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-image: url('assets/img/bg-dragon.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }

        /* Contenedor centrado con sombra y bordes redondeados */
        .content-container {
            margin-top: 100px;
            max-width: 500px;
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
            font-weight: bold;
            margin-bottom: 30px;
            color: #d9534f;
        }

        .btn {
            padding: 12px;
            font-size: 16px;
        }

        .btn-danger {
            background-color: #d9534f;
            border-color: #d9534f;
        }

        .btn-danger:hover {
            background-color: #c9302c;
            border-color: #c12e2a;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        .btn-group {
            display: flex;
            justify-content: center;
            gap: 15px; /* Espacio entre los botones */
        }

        .btn i {
            margin-right: 8px;
        }
    </style>
</head>

<body>
    <div class="container content-container">
        <h2 class="text-center">
            <i class="fas fa-exclamation-triangle"></i> ¿Estás seguro de que quieres eliminar tu cuenta?
        </h2>

        <form action="EliminarUsuarioServlet" method="POST" class="text-center mt-4">
            <!-- Botones alineados con Flexbox -->
            <div class="btn-group">
                <button type="submit" class="btn btn-danger">
                    <i class="fas fa-trash-alt"></i> Eliminar mi cuenta
                </button>
                <a href="Dashboard.jsp" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Cancelar
                </a>
            </div>
        </form>
    </div>

    <!-- Scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>

</html>
