package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Subproyectos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubproyectosDAO implements IDAO {
    private static final String TABLE_NAME = "PRY_Subproyectos";

    private SubproyectosDAO() {
        // Private constructor to prevent instantiation
    }

    public static Subproyectos getSubproyectoById(int id) {
        Subproyectos subproyecto = null;

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE SBP_id_subproyecto = ?")) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String denominacionC = resultSet.getString("SBP_denominacionc");
                    String denominacionL = resultSet.getString("SBP_denominacionl");
                    Date fechaInicio = resultSet.getDate("SBP_fechainicio");
                    int idProyecto = resultSet.getInt("SBP_id_proyecto");

                    subproyecto = new Subproyectos(id, denominacionC, denominacionL, fechaInicio, idProyecto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subproyecto;
    }

    public static boolean updateSubproyecto(Subproyectos subproyecto) {
        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE " + TABLE_NAME + " SET SBP_denominacionc = ?, SBP_denominacionl = ?, SBP_fechainicio = ?, SBP_id_proyecto = ? WHERE SBP_id_subproyecto = ?"
        )) {
            statement.setString(1, subproyecto.getDenominacionC());
            statement.setString(2, subproyecto.getDenominacionL());
            statement.setDate(3, new java.sql.Date(subproyecto.getFechaInicio().getTime()));
            statement.setInt(4, subproyecto.getIdProyecto());
            statement.setInt(5, subproyecto.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSubproyecto(int id) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE SBP_id_subproyecto = ?")) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Subproyectos> getAll() {
        List<Subproyectos> subproyectos = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("SBP_id_subproyecto");
                String denominacionC = resultSet.getString("SBP_denominacionc");
                String denominacionL = resultSet.getString("SBP_denominacionl");
                Date fechaInicio = resultSet.getDate("SBP_fechainicio");
                int idProyecto = resultSet.getInt("SBP_id_proyecto");

                Subproyectos subproyecto = new Subproyectos(id, denominacionC, denominacionL, fechaInicio, idProyecto);
                subproyectos.add(subproyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subproyectos;
    }

    public static boolean insertSubproyecto(Subproyectos subproyecto) {
        String sql = "INSERT INTO " + TABLE_NAME + " (SBP_denominacionc, SBP_denominacionl, SBP_fechainicio, SBP_id_proyecto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, subproyecto.getDenominacionC());
            statement.setString(2, subproyecto.getDenominacionL());
            statement.setDate(3, new java.sql.Date(subproyecto.getFechaInicio().getTime()));
            statement.setInt(4, subproyecto.getIdProyecto());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}