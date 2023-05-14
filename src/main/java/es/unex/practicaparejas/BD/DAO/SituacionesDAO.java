package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Situaciones;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SituacionesDAO implements IDAO {
    private static final String TABLE_NAME = "PRY_Situaciones";

    private SituacionesDAO() {
        // Private constructor to prevent instantiation
    }

    public static boolean insertarSituacion(Situaciones situacion) {
        String sql = "INSERT INTO " + TABLE_NAME + " (STC_id_subproyecto, STC_id_estado, STC_fecharef) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, situacion.getIdSubproyecto());
            statement.setInt(2, situacion.getIdEstado());
            statement.setDate(3, new java.sql.Date(situacion.getFechaRef().getTime()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarSituacion(Situaciones situacion) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE STC_id_subproyecto = ? AND STC_id_estado = ?");
        statement.setInt(1, situacion.getIdSubproyecto());
        statement.setInt(2, situacion.getIdEstado());

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    }


    public static List<Situaciones> getAllSituaciones() {
        List<Situaciones> situaciones = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idSubproyecto = resultSet.getInt("STC_id_subproyecto");
                int idEstado = resultSet.getInt("STC_id_estado");
                Date fechaRef = resultSet.getDate("STC_fecharef");

                Situaciones situacion = new Situaciones(idSubproyecto, idEstado, fechaRef);
                situaciones.add(situacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return situaciones;
    }
    public static boolean actualizarSituaciones(Situaciones situacion) {
        String sql = "UPDATE " + TABLE_NAME + " SET STC_fecharef = ? WHERE STC_id_subproyecto = ? AND STC_id_estado = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(situacion.getFechaRef().getTime()));
            statement.setInt(2, situacion.getIdSubproyecto());
            statement.setInt(3, situacion.getIdEstado());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}