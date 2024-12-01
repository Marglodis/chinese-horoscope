package com.horoscopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.horoscopo.modelo.Usuario;
import com.horoscopo.procesarconexion.DatabaseConnection;

public class UsuarioDAO {

	public Usuario validarUsuario(String username, String password) {
		String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
		try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("username"),
						rs.getString("email"), rs.getDate("fecha_nacimiento"), rs.getString("password"),
						rs.getString("animal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean crearUsuario(Usuario usuario) {

		PreparedStatement pstmt = null;
		try {
			// Obtener la conexión

			Connection cnx = DatabaseConnection.getDataSource().getConnection();
			String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password) VALUES (?, ?, ?, ?, ?)";
			pstmt = cnx.prepareStatement(sql);

			pstmt.setString(1, usuario.getNombre());
			pstmt.setString(2, usuario.getUsername());
			pstmt.setString(3, usuario.getEmail());
			pstmt.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
			pstmt.setString(5, usuario.getPassword());

			int filasAfectadas = pstmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar usuario: " + e.getMessage());
			return false;
		} finally {
			// Cerrar conexiones
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				System.err.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
	}

	// Nuevo método para obtener todos los usuarios
	public List<Usuario> obtenerUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuarios";
		try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("username"),
						rs.getString("email"), rs.getDate("fecha_nacimiento"), rs.getString("password"),
						rs.getString("animal"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	// Método para actualizar un usuario en la base de datos
	public boolean actualizarUsuario(Usuario usuario) {
		boolean exito = false;

		// Validar que el usuario con el ID proporcionado exista en la base de datos
		if (!existeUsuarioPorId(usuario.getId())) {
			System.out.println("El usuario con ID " + usuario.getId() + " no existe.");
			return false; // No se puede actualizar un usuario que no existe
		}

		String sql = "UPDATE usuarios SET nombre = ?, username = ?, email = ?, fecha_nacimiento = ?, password = ?, animal = ?  WHERE id = ?";

		try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getUsername());
			ps.setString(3, usuario.getEmail());
			ps.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime())); // Aseguramos que la fecha esté en
																						// formato SQL
			ps.setString(5, usuario.getPassword());
			ps.setString(6, usuario.getAnimal());

			ps.setInt(7, usuario.getId()); // El ID no se actualiza, solo se usa como filtro

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				exito = true; // Si se actualizó al menos una fila, la operación fue exitosa
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exito;
	}

	public boolean eliminarUsuario(int id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método genérico para verificar la existencia de un usuario basado en un campo
	// y un valor
	private boolean existeUsuarioPorCampo(String campo, Object valor) {
		String sql = "SELECT COUNT(*) FROM usuarios WHERE " + campo + " = ?";
		try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {
			if (valor instanceof String) {
				ps.setString(1, (String) valor); // Para valores tipo String
			} else if (valor instanceof Integer) {
				ps.setInt(1, (Integer) valor); // Para valores tipo Integer (como ID)
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Si hay filas con el valor, el usuario existe
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Si no hay filas, el usuario no existe
	}

	// Método para verificar si un usuario existe por su nombre de usuario
	public boolean existeUsuarioPorUsername(String username) {
		return existeUsuarioPorCampo("username", username); // Reutilizamos el método genérico
	}

	// Método para verificar si un usuario existe por su ID
	public boolean existeUsuarioPorId(int id) {
		return existeUsuarioPorCampo("id", id); // ANTES: String.valueOf(id) que da el error
	}

	public List<Usuario> buscarUsuarios(String busqueda) {
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuarios WHERE nombre LIKE ? OR email LIKE ? OR username LIKE ? OR animal LIKE ?";

	    try (Connection cnx = DatabaseConnection.getDataSource().getConnection();
	         PreparedStatement ps = cnx.prepareStatement(sql)) {

	        String busquedaLike = "%" + busqueda + "%"; // Utilizamos el comodín "%" para buscar coincidencias parciales
	        ps.setString(1, busquedaLike);
	        ps.setString(2, busquedaLike);
	        ps.setString(3, busquedaLike);
	        ps.setString(4, busquedaLike); // Agregamos el campo 'animal' para que también se busque por Animal Chino

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("username"),
	                        rs.getString("email"), rs.getDate("fecha_nacimiento"), rs.getString("password"),
	                        rs.getString("animal"));
	                usuarios.add(usuario);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuarios;
	}

}
