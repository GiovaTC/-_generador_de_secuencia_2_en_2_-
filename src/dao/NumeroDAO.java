package dao;

import conexion.ConexionMySQL;
import modelo.Numero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NumeroDAO {

    public void guardar(Numero numero) {

        String sql =
                "INSERT INTO numeros(numero) VALUES(?)";

        try(Connection con = ConexionMySQL.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero.getNumero());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();

        }
    }

    public void mostrarTodos() {

        String sql =
                "SELECT * FROM numeros";

        try(Connection con = ConexionMySQL.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            System.out.println();
            System.out.println("NUMEROS ALMACENADOS");
            System.out.println("-----------------------");

            while(rs.next()) {
                System.out.println(
                        rs.getInt("id")
                        +" -> "
                        +rs.getInt("numero"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
