package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadosDAO implements IDAO {
    private static final String TABLE_NAME = "PRY_Estados";

    private EstadosDAO() {
        // Private constructor to prevent instantiation
    }

    public static boolean insertarEstado(Estado estado) {
        String sql = "INSERT INTO " + TABLE_NAME + " (STD_denominacion) VALUES (?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado.getDenominacion());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarEstado(Estado estado) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE STD_id_estado = ?");
            statement.setInt(1, estado.getIdEstado());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

    }

    public static List<Estado> getAll() {
        List<Estado> estados = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idEstado = resultSet.getInt("STD_id_estado");
                String denominacion = resultSet.getString("STD_denominacion");

                Estado estado = new Estado(idEstado, denominacion);
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estados;
    }
    public static void actualizarEstado(Estado estado){
        String sql = "UPDATE " + TABLE_NAME + " SET STD_denominacion = ? WHERE STD_id_estado = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado.getDenominacion());
            statement.setInt(2, estado.getIdEstado());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing estado was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}