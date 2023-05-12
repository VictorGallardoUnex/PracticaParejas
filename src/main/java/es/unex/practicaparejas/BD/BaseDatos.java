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
            String url = "jdbc:sqlserver://localhost:1433;databaseName=proyectos;integratedSecurity=true;trustServerCertificate=true;encrypt=false;";
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

//            String connectionUrl =
//                    "jdbc:sqlserver://localhost:1433;"
//                            + "database=proyectos;"
//                            + "encrypt=false;"
//                            + "trustServerCertificate=false;"
//                            + "loginTimeout=30;"
//                            + "integratedSecurity=true;";
//            String userName = "usuario";
//            String password = "usuario";


        }


    public ResultSet ejecutarSql(String sentencia)   {
        ResultSet res;
        try {
            res = conn.createStatement().executeQuery(sentencia);
        } catch (SQLException e) {
            Logger.getLogger(String.valueOf(this.getClass()), "Error al ejecutar la sentencia: " + sentencia);
            e.printStackTrace();
            return null;
        }
        return res;
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
