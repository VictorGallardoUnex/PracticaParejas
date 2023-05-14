package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDAO implements IDAO {
    static String TABLE_NAME = "PRY_Servicios";

    private ServiciosDAO() {
        // Private constructor to prevent instantiation
    }

    public static Servicios getServicioById(int id) {
        Servicios servicio = null;

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE SRV_id_servicio = ?")) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idDireccion = resultSet.getInt("SRV_id_dirgen");

                    servicio = new Servicios(id, idDireccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicio;
    }

    public static boolean actualizarServicio(Servicios servicio) {
        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE " + TABLE_NAME + " SET SRV_id_dirgen = ? WHERE SRV_id_servicio = ?"
        )) {
            statement.setInt(1, servicio.getSRV_id_dirgen());
            statement.setInt(2, servicio.getSRV_id_servicio());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarServicio(Servicios servicio) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE SRV_id_servicio = ?");
            statement.setInt(1, servicio.getSRV_id_servicio());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

    }

    public static List<Servicios> getAll() {
        List<Servicios> servicios = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("SRV_id_servicio");
                int idDireccion = resultSet.getInt("SRV_id_dirgen");

                Servicios servicio = new Servicios(id, idDireccion);
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }

    public static boolean insertarServicio(Servicios servicio) {
        String sql = "INSERT INTO " + TABLE_NAME + " (SRV_id_dirgen) VALUES (?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, servicio.getSRV_id_dirgen());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
