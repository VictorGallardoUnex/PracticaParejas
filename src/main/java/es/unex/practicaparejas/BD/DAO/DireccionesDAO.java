package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Direcciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDAO implements IDAO {
    private static final String TABLE_NAME = "PRY_Direcciones";

    private DireccionesDAO() {
        // Private constructor to prevent instantiation
    }

    public static Direcciones getDireccionById(int idDireccion) {
        Direcciones direccion = null;

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE DGN_id_dirgen = ?")) {

            statement.setInt(1, idDireccion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String denominacion = resultSet.getString("DGN_denominacion");
                    direccion = new Direcciones(idDireccion, denominacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return direccion;
    }

    public static boolean updateDireccion(int idDireccion, String denominacion) {
        try (PreparedStatement statement = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET DGN_denominacion = ? WHERE DGN_id_dirgen = ?")) {

            statement.setString(1, denominacion);
            statement.setInt(2, idDireccion);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDireccion(int idDireccion) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE DGN_id_dirgen = ?")) {

            statement.setInt(1, idDireccion);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<Direcciones> getAll() {
        ObservableList<Direcciones> direcciones = FXCollections.observableArrayList();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idDireccion = resultSet.getInt("DGN_id_dirgen");
                String denominacion = resultSet.getString("DGN_denominacion");
                Direcciones direccion = new Direcciones(idDireccion, denominacion);
                direcciones.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return direcciones;
    }

    public static boolean insertDireccion(String denominacion) {
        String sql = "INSERT INTO " + TABLE_NAME + " (DGN_denominacion) VALUES (?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, denominacion);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
