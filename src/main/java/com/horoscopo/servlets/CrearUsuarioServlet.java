package com.horoscopo.servlets;

import java.io.IOException;

import com.horoscopo.dao.UsuarioDAO;
import com.horoscopo.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearUsuarioServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recibir datos del formulario
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validar longitud de los campos
        if (nombre.length() > 30 || username.length() > 30 || email.length() > 30 || password.length() > 30) {
            request.setAttribute("error", "Los campos no pueden tener más de 30 caracteres.");
            request.getRequestDispatcher("CreacionUsuario.jsp").forward(request, response);
            return;
        }

        // Validar contraseñas
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("CreacionUsuario.jsp").forward(request, response);
            return;
        }

        // Validar si el username ya está registrado
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.existeUsuarioPorUsername(username)) {
            request.setAttribute("error", "El nombre de usuario ya está en uso. Por favor, elige otro.");
            request.getRequestDispatcher("CreacionUsuario.jsp").forward(request, response);
            return;
        }

        // Crear un nuevo objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
        nuevoUsuario.setPassword(password);

        // Guardar el usuario en la base de datos
        try {
            boolean usuarioCreado = usuarioDAO.crearUsuario(nuevoUsuario);
            if (usuarioCreado) {
                request.setAttribute("mensajeExito", "Usuario creado con éxito.");
            } else {
                request.setAttribute("mensajeError", "Ocurrió un error al crear el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error al procesar la solicitud.");
        }
        request.getRequestDispatcher("CreacionUsuario.jsp").forward(request, response);
    }
}
