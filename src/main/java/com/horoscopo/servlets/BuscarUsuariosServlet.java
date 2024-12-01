package com.horoscopo.servlets;

import java.io.IOException;
import java.util.List;

import com.horoscopo.dao.UsuarioDAO;
import com.horoscopo.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuscarUsuariosServlet
 */
@WebServlet("/BuscarUsuariosServlet")
public class BuscarUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BuscarUsuariosServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el término de búsqueda desde la solicitud
        String busqueda = request.getParameter("busqueda");

        // Si hay un término de búsqueda, buscar usuarios
        List<Usuario> usuarios;
        if (busqueda != null && !busqueda.isEmpty()) {
            usuarios = new UsuarioDAO().buscarUsuarios(busqueda); // Llamada al método de búsqueda
        } else {
            // Si no hay término de búsqueda, obtener todos los usuarios
            usuarios = new UsuarioDAO().obtenerUsuarios();
        }

        // Agregar la lista de usuarios al request
        request.setAttribute("usuarios", usuarios);

        // Redirigir al JSP de listado
        request.getRequestDispatcher("ListarUsuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
