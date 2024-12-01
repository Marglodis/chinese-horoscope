<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    // Verificar si la sesión del usuario está activa
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("InicioSession.jsp"); // Redirige si no hay sesión activa
        return; 
    }
%>

<!-- footer.jsp -->
<footer class="bg-dark text-white text-center py-4 fixed-bottom">
    <div class="container">
        <p>&copy; 2024 Horóscopo Chino. Todos los derechos reservados.</p>
        <div class="social-icons">
            <a href="https://www.facebook.com" target="_blank" class="text-white mx-2">
                <i class="fab fa-facebook-f"></i> Facebook
            </a>
            <a href="https://twitter.com" target="_blank" class="text-white mx-2">
                <i class="fab fa-twitter"></i> Twitter
            </a>
            <a href="https://www.instagram.com" target="_blank" class="text-white mx-2">
                <i class="fab fa-instagram"></i> Instagram
            </a>
        </div>
        <p class="mt-2">
            <a href="privacy-policy.jsp" class="text-white">Política de privacidad</a> |
            <a href="terms-of-service.jsp" class="text-white">Términos de servicio</a>
        </p>
    </div>
</footer>

<!-- Añadir los íconos de FontAwesome (si no los has añadido ya) -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
