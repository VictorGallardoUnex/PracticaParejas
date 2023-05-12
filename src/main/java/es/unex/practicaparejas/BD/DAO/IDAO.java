package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.BaseDatos;

import java.sql.Connection;

public interface IDAO {
    Connection conn = BaseDatos.getInstance().getConn();
}
