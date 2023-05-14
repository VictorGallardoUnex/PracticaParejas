package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.Modelos.Proyectos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectosDAO implements IDAO {
    static String TABLE_NAME  = "PRY_Proyectos";

    private ProyectosDAO() {
        // Private constructor to prevent instantiation
    }

    public static Proyectos getProyectoById(int id) {
        Proyectos proyecto = null;

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE PRY_id_proyecto = ?")) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idServicio = resultSet.getInt("PRY_id_servicio");
                    Date fechaInicio = resultSet.getDate("PRY_fechainicio");
                    String denominacionC = resultSet.getString("PRY_denominacionc");
                    String denominacionL = resultSet.getString("PRY_denominacionl");

                    proyecto = new Proyectos(idServicio, fechaInicio, denominacionC, denominacionL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyecto;
    }

    public static boolean actualizarProyecto(Proyectos proyecto) {
        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE " + TABLE_NAME + " SET PRY_id_servicio = ?, PRY_fechainicio = ?, PRY_denominacionc = ?, PRY_denominacionl = ? WHERE PRY_id_proyecto = ?"
        )) {
            statement.setInt(1, proyecto.getPRY_id_servicio());
            statement.setDate(2, new java.sql.Date(proyecto.getPRY_fechainicio().getTime()));
            statement.setString(3, proyecto.getPRY_denominacionc());
            statement.setString(4, proyecto.getPRY_denominacionl());
            statement.setInt(5, proyecto.getPRY_id_proyecto());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarProyecto(Proyectos proyecto) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE PRY_id_proyecto = ?");
            statement.setInt(1, proyecto.getIdProyecto());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

    }

    public static List<Proyectos> getAll() {
        List<Proyectos> proyectos = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("PRY_id_proyecto");
                int idServicio = resultSet.getInt("PRY_id_servicio");
                Date fechaInicio = resultSet.getDate("PRY_fechainicio");
                String denominacionC = resultSet.getString("PRY_denominacionc");
                String denominacionL = resultSet.getString("PRY_denominacionl");

                Proyectos proyecto = new Proyectos(id,idServicio, fechaInicio, denominacionC, denominacionL);
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos;
    }

    public static boolean insertarProyecto(Proyectos proyecto) {
        String sql = "INSERT INTO " + TABLE_NAME + " (PRY_id_servicio, PRY_fechainicio, PRY_denominacionc, PRY_denominacionl) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, proyecto.getPRY_id_servicio());
            statement.setDate(2, new java.sql.Date(proyecto.getPRY_fechainicio().getTime()));
            statement.setString(3, proyecto.getPRY_denominacionc());
            statement.setString(4, proyecto.getPRY_denominacionl());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
