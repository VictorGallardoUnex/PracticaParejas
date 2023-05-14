package es.unex.practicaparejas.BD.DAO;

import es.unex.practicaparejas.BD.BaseDatos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.sql.Connection;

public interface IDAO {
    Connection conn = BaseDatos.getInstance().getConn();

}
