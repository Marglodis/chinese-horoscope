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
 * Servlet implementation class EliminarUsuarioServlet
 */
@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener el usuario de la sesión
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		if (usuario != null) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			boolean eliminado = usuarioDAO.eliminarUsuario(usuario.getId());
			if (eliminado) {
				// Invalidar la sesión y redirigir al login
				request.getSession().invalidate(); // Cerrar la sesión
				response.sendRedirect("InicioSession.jsp?eliminado=true"); // Redirigir al login
			} else {
				// Si la eliminación falla, redirigir al dashboard con un mensaje de error
				response.sendRedirect("Dashboard.jsp?errorEliminar=true");
			}
		} else {
			// Si no hay un usuario en la sesión, redirigir al login
			response.sendRedirect("InicioSession.jsp");
		}
	}

}
