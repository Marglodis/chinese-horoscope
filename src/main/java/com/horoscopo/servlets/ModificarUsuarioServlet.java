package com.horoscopo.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.horoscopo.dao.HoroscopoDAO;
import com.horoscopo.dao.UsuarioDAO;
import com.horoscopo.modelo.Horoscopo;
import com.horoscopo.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificarUsuarioServlet
 */
@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener los parámetros del formulario
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String username = request.getParameter("username");
		
		String email = request.getParameter("email");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		 // Obtener el usuario actual de la sesión
        Usuario usuarioActual = (Usuario) request.getSession().getAttribute("usuario");

		 // Verificar si el username ha cambiado
        if (!usuarioActual.getUsername().equals(username)) {
        	
        	// Si el username ha cambiado, mostrar un error
            request.setAttribute("Info", "No puedes cambiar tu nombre de usuario.");
            request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
            return;
        }

		if (password == null || password.isEmpty()) {
			// Manejar el caso de contraseña vacía o nula
			request.setAttribute("error", "La contraseña no puede estar vacía.");
			request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
			return;
		}

		// Validar que las contraseñas coincidan
		if (!password.equals(confirmPassword)) {
			response.sendRedirect("ModificacionUsuario.jsp?error=true");
			return;
		}

		// Crear un objeto Usuario con los datos recibidos
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(id));
		usuario.setNombre(nombre);
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuario.setPassword(password);

		// Validar que la fecha de nacimiento no esté vacía
		if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
			try {
				usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento)); // Convertir la fecha a java.sql.Date
			} catch (IllegalArgumentException e) {
				// Si la fecha es inválida, manejar el error
				request.setAttribute("error", "La fecha de nacimiento no es válida.");
				request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
				return;
			}
		} else {
			// Si la fecha está vacía, mostrar un error
			request.setAttribute("error", "La fecha de nacimiento no puede estar vacía.");
			request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
			return;
		}

		// Verificar si la fecha de nacimiento corresponde a un horóscopo
		boolean esValidoHoroscopo = false;
		List<Horoscopo> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
		for (Horoscopo h : listaHoroscopo) {
			if (!usuario.getFechaNacimiento().before(h.getFechaInicio())
					&& !usuario.getFechaNacimiento().after(h.getFechaFin())) {
				esValidoHoroscopo = true;
				usuario.setAnimal(h.getAnimal()); // Asignar el animal correspondiente
				break;
			}
		}

		// Si no corresponde a ningún animal, mostrar un mensaje de error
		if (!esValidoHoroscopo) {
			request.setAttribute("error", "El año de nacimiento no corresponde a ningún signo del horóscopo chino.");
			request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
			return;
		}

		// Intentar actualizar el usuario en la base de datos
		boolean exito = usuarioDAO.actualizarUsuario(usuario);

		if (exito) {
			// Actualizar el objeto en la sesión con los datos modificados
			request.getSession().setAttribute("usuario", usuario);
			request.setAttribute("success", "Usuario actualizado correctamente.");
			request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Ocurrió un error al actualizar los datos del usuario.");
		    request.getRequestDispatcher("ModificacionUsuario.jsp").forward(request, response);
		}
	}
}
