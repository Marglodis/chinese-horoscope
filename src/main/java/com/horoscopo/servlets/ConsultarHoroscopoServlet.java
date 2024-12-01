package com.horoscopo.servlets;

import java.io.IOException;
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
 * Servlet implementation class ConsultarHoroscopoServlet
 */
@WebServlet("/ConsultarHoroscopoServlet")
public class ConsultarHoroscopoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultarHoroscopoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            // Si no hay usuario en la sesión, redirigimos al login
            response.sendRedirect("InicioSession.jsp");
            return;
        }

        // Obtener lista de horóscopos desde la base de datos
        List<Horoscopo> listaHoroscopo = new HoroscopoDAO().obtenerHoroscopo();

        Horoscopo horoscopo = null;
        for (Horoscopo temp : listaHoroscopo) {
            // Asegurarse de que la fecha de nacimiento del usuario esté dentro del rango del horóscopo
            if (!usuario.getFechaNacimiento().before(temp.getFechaInicio()) && !usuario.getFechaNacimiento().after(temp.getFechaFin())) {
                horoscopo = temp;
                break;  // Si encontramos el horóscopo, no necesitamos seguir buscando
            }
        }

        if (horoscopo != null) {
            // Establecer el animal chino para el usuario
            usuario.setAnimal(horoscopo.getAnimal());

            // Actualizar el campo animal en la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean exito = usuarioDAO.actualizarUsuario(usuario);
            if (exito) {
                // Pasar el horóscopo y el animal chino a la JSP
                request.setAttribute("horoscopo", horoscopo);
                request.getRequestDispatcher("ConsultaHoroscopoChino.jsp").forward(request, response);
            } else {
                // Si no se pudo actualizar, mostrar un mensaje de error en la misma página
                request.setAttribute("error", "Hubo un problema al actualizar los datos del usuario.");
                request.getRequestDispatcher("ConsultaHoroscopoChino.jsp").forward(request, response);
            }
        } else {
            // Si no encontramos el horóscopo, mostrar un mensaje al usuario en vez de redirigir a error.jsp
            request.setAttribute("error", "El año de nacimiento no corresponde a un signo del horóscopo chino válido.");
            request.getRequestDispatcher("ConsultaHoroscopoChino.jsp").forward(request, response);
        }
    }
}
