package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.EstadosDAO;
import es.unex.practicaparejas.BD.Modelos.Estado;
import es.unex.practicaparejas.BD.Modelos.Situaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TabSituaciones implements Initializable {
    @FXML
    private TableView<Situaciones> situacionesTableView;
    @FXML
    private TableColumn<Situaciones, Integer> idColumn;
    @FXML
    private TableColumn<Situaciones, String> id_estadoColumn;
    @FXML
    private TableColumn<Situaciones, Date> id_direccionColumn;

    @FXML
    private ComboBox<String> estadoComboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ComboBox with an empty list
        estadoComboBox.setItems(FXCollections.emptyObservableList());

        // Set the event handler for the "onShown" event
        estadoComboBox.setOnShowing(event -> {
            // Populate the ComboBox when it is being shown
            List<Estado> estados = getAllEstadosFromDatabase(); // Replace with your data retrieval logic
            ObservableList<String> items = convertEstadosToString(estados);
            estadoComboBox.setItems(items);
        });
    }

    private List<Estado> getAllEstadosFromDatabase() {
        // Replace this method with your own logic to retrieve data from the database
        // and return a List of Estado objects
        // Example:
        return EstadosDAO.getAll();
    }
    private ObservableList<String> convertEstadosToString(List<Estado> estados) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Estado estado : estados) {
            String item = estado.getId() + " | " + estado.getDenominacion();
            items.add(item);
        }
        return items;
    }


}
