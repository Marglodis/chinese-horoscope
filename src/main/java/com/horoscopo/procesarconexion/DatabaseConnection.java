package com.horoscopo.procesarconexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {

    // Constructor privado para evitar instanciación
    private DatabaseConnection() {}

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
    	Dotenv dotenv = Dotenv.load();
    	
    	// SUSTITUIR POR VARIABLES LOCALES, PLEASE!!!
    	
    	String dbUrl = "DB_URL"; //dotenv.get("DB_URL");
        String dbUser = "DB_USER";//dotenv.get("DB_USER");
        String dbPassword = "DB_PASSWORD";//dotenv.get("DB_PASSWORD");
        
        System.out.println("URL de la base de datos: " + dbUrl);
        System.out.println("Usuario de la base de datos: " + dbUser);
        System.out.println("Contraseña de la base de datos: " + dbPassword);
        
    	dataSource.setUrl(dbUrl);
    	dataSource.setUsername(dbUser);
    	dataSource.setPassword(dbPassword);
    	dataSource.setDriverClassName("org.postgresql.Driver");

    	//Configurar el pool
    	dataSource.setInitialSize(5); // define coexiones iniciales.
    	dataSource.setMaxTotal(20); //maximo de conexiones
    	dataSource.setMaxIdle(10); //maximo de conexiones en espera
    	dataSource.setMinIdle(5); // minio de conex en espera

    }

    public static DataSource getDataSource() {
    	return dataSource;
    }

    // Método para cerrar la conexión (normalmente no es necesario si usas un pool)
    public static void cerrarConexion(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
