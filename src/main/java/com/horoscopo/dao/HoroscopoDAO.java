package com.horoscopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.horoscopo.modelo.Horoscopo;
import com.horoscopo.procesarconexion.DatabaseConnection;

public class HoroscopoDAO {

	// Método para obtener los horóscopos
	public List<Horoscopo> obtenerHoroscopo() {
		List<Horoscopo> horoscopos = new ArrayList<>();
		String sql = "SELECT * FROM horoscopo";

		// Usamos la misma conexión que en el UsuarioDAO
		try (Connection cnx = DatabaseConnection.getDataSource().getConnection(); // Conexión obtenida desde el pool
				PreparedStatement ps = cnx.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Creando un objeto Horoscopo a partir del resultado de la consulta
				Horoscopo h = new Horoscopo(rs.getString("animal"), rs.getDate("fecha_inicio"),
						rs.getDate("fecha_fin"));
				horoscopos.add(h);
			}
		} catch (SQLException e) {
			// Imprimir el error en caso de que ocurra una excepción
			e.printStackTrace();
		}

		// Retornar la lista de horóscopos
		return horoscopos;
	}
}
