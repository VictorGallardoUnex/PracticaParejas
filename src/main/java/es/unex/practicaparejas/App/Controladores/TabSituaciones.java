package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.Modelos.Direcciones;
import es.unex.practicaparejas.BD.Modelos.Situaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class TabSituaciones {
    @FXML
    private TableView<Situaciones> situacionesTableView;
    @FXML
    private TableColumn<Situaciones, Integer> idColumn;
    @FXML
    private TableColumn<Situaciones, String> id_estadoColumn;
    @FXML
    private TableColumn<Situaciones, Date> id_direccionColumn;

    @FXML
    private ComboBox<String> comboBox;

    public void getEstados(URL location, ResourceBundle resources) {
        // Create a list of items for the ComboBox
        ObservableList<String> items = FXCollections.observableArrayList(
                "Item 1",
                "Item 2",
                "Item 3"
        );

        // Set the items in the ComboBox
        comboBox.setItems(items);
    }


}
