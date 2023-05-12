package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
import es.unex.practicaparejas.BD.Modelos.Direcciones;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabDirecciones {
    @FXML
    private TableView<Direcciones> direccionesTableView;
    @FXML
    private TableColumn<Direcciones, Integer> idColumn;
    @FXML
    private TableColumn<Direcciones, String> direccionesColumn;
    @FXML
    private TextField direcciones_denominacion_insert;

    @FXML
    protected void onDireccionesInsert() {
        String denominacion = direcciones_denominacion_insert.getText();
        if (denominacion.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Inserte una dirección para poder guardar.");
            alert.showAndWait();
        } else{
            DireccionesDAO.insertDireccion(denominacion);
        }

        // Repopulate the table with updated data
        direccionesTableView.setItems(DireccionesDAO.getAllDirecciones());
    }

    @FXML
    protected void initialize() {
        // Set up the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        direccionesColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        // Populate the table with initial data
        direccionesTableView.setItems(DireccionesDAO.getAllDirecciones());
    }
}
