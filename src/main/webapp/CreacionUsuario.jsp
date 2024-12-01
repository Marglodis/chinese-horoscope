<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creación de Usuario</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Link a Font Awesome para los iconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-image: url('assets/img/bg-dragon.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }

        /* Estilo adicional para centrar el formulario */
        .form-container {
            margin-top: 100px;
            max-width: 450px;
            margin-left: auto;
            margin-right: auto;
            padding: 30px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .form-control {
            height: 45px;
            font-size: 16px;
        }

        .btn-block {
            padding: 12px;
            font-size: 18px;
        }

        .alert {
            font-size: 14px;
        }

        .text-center a {
            font-weight: bold;
            color: #d9534f;
        }

        .text-center a:hover {
            text-decoration: underline;
        }

    </style>
</head>

<body>
    <!-- Contenedor principal con sombra y borde -->
    <div class="container form-container">
        <h2 class="text-center text-danger">
            <i class="fas fa-user-circle"></i> Registro de Usuario
        </h2>

        <!-- Mostrar mensajes de error -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3" role="alert">
                ${error}
            </div>
        </c:if>

        <form action="CrearUsuarioServlet" method="POST">
            <!-- Campo de Nombre Completo -->
            <div class="form-group">
                <label for="nombre">Nombre Completo</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" id="nombre" name="nombre" required placeholder="Ingresa tu nombre completo">
                </div>
            </div>

            <!-- Campo de Nombre de Usuario -->
            <div class="form-group">
                <label for="username">Nombre de Usuario</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" id="username" name="username" required placeholder="Ingresa tu nombre de usuario">
                </div>
            </div>

            <!-- Campo de Correo Electrónico -->
            <div class="form-group">
                <label for="email">Correo Electrónico</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    </div>
                    <input type="email" class="form-control" id="email" name="email" required placeholder="Ingresa tu correo electrónico">
                </div>
            </div>

            <!-- Campo de Fecha de Nacimiento -->
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
                    </div>
                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                </div>
            </div>

            <!-- Campo de Contraseña -->
            <div class="form-group">
                <label for="password">Contraseña</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    </div>
                    <input type="password" class="form-control" id="password" name="password" required placeholder="Ingresa tu contraseña">
                </div>
            </div>

            <!-- Campo de Confirmar Contraseña -->
            <div class="form-group">
                <label for="confirmPassword">Confirmar Contraseña</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    </div>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required placeholder="Confirma tu contraseña">
                </div>
            </div>

            <!-- Botón de Registro -->
            <button type="submit" class="btn btn-danger btn-block">Registrar</button>

            <c:if test="${not empty mensajeExito}">
                <div class="alert alert-success mt-3" role="alert">
                    ${mensajeExito}
                </div>
            </c:if>
        </form>

        <!-- Enlace a la página de inicio de sesión -->
        <div class="text-center mt-3">
            <p>
                ¿Ya tienes una cuenta? <a href="InicioSession.jsp">Inicia sesión aquí</a>
            </p>
        </div>
    </div>

    <!-- Scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>

</html>
