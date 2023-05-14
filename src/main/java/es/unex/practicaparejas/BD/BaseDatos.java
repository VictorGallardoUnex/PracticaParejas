package es.unex.practicaparejas.BD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class BaseDatos {
        public Connection conn;

    public Connection getConn() {
        return conn;
    }

    protected BaseDatos() {
            String sql = "SELECT * FROM PRY_Direcciones";
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("proyectos");
            ds.setTrustServerCertificate(true);
            ds.setIntegratedSecurity(true); // habilitar autenticación integrada de Windows


        try {
            conn = ds.getConnection();
            conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Metodos singleton
    private static BaseDatos instance;

    public static BaseDatos getInstance() {
        if (instance == null) {
            instance = new BaseDatos();
        }
        return instance;
    }

}
