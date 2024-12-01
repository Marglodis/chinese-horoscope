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
 * Servlet implementation class IniciarSession
 */
@WebServlet("/IniciarSessionServlet")
public class IniciarSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public IniciarSessionServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Verifica si el usuario existe
	    boolean usuarioExiste = new UsuarioDAO().existeUsuarioPorUsername(username);

	    if (!usuarioExiste) {

	        // Si el usuario no existe, redirige al formulario de registro
	        response.sendRedirect("InicioSession.jsp?mensaje=Usuario no encontrado. Por favor, registrate.");
	        return;
	    }

		// Verifica las credenciales
		Usuario usuario = new UsuarioDAO().validarUsuario(username, password);
		if (usuario != null) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("Dashboard.jsp");
		} else {
			response.sendRedirect("InicioSession.jsp?error=true");
		}
	}

	/**
	 * Si la solicitud es GET, cierra la sesión del usuario.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Invalidar la sesión actual (cerrar sesión)
		request.getSession().invalidate();
		// Redirigir al inicio de sesión
		response.sendRedirect("InicioSession.jsp");
	}
}