package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    private static final String URL =
            "jdbc:mysql://localhost:3306/secuencia_db";

    private static final String USER = "root";
    private static final String PASSWORD = "Tapiero123";

    public static Connection conectar() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }   // clase conexión!
}
