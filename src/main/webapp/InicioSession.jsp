<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <!-- Link a Bootstrap 4 desde el CDN -->
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
        .login-container {
            margin-top: 100px;
            max-width: 450px;
            margin-left: auto;
            margin-right: auto;
            padding: 30px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .login-container h2 {
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
    <div class="container login-container">
        <h2 class="text-center text-danger">
            <i class="fas fa-user-circle"></i> Iniciar Sesión
        </h2>

        <!-- Formulario de login con sombra y bordes -->
        <form action="IniciarSessionServlet" method="POST">
            <!-- Campo de Usuario -->
            <div class="form-group">
                <label for="username">Nombre de Usuario</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" id="username" name="username" required placeholder="Ingresa tu nombre de usuario">
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

            <!-- Botón de Inicio de Sesión -->
            <button type="submit" class="btn btn-danger btn-block">Iniciar Sesión</button>

            <!-- Mensaje de error en caso de fallo -->
            <c:if test="${param.error != null}">
                <div class="alert alert-danger mt-3" role="alert">
                    <strong>Error:</strong> Usuario o contraseña incorrectos.
                </div>
            </c:if>

            <c:if test="${param.mensaje != null}">
                <div class="alert alert-info mt-3" role="alert">
                    <strong>Mensaje:</strong> ${param.mensaje}
                </div>
            </c:if>

        </form>

        <!-- Enlace a la página de registro -->
        <div class="text-center mt-3">
            <p>
                ¿No tienes una cuenta? <a href="CreacionUsuario.jsp">Regístrate aquí</a>
            </p>
        </div>
    </div>

    <!-- Scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>

</html>
