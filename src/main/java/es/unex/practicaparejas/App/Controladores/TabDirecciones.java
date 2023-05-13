package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
import es.unex.practicaparejas.BD.Modelos.Direcciones;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabDirecciones {

    @FXML
    private TextField denominacionInput;

    @FXML
    private TableView<Direcciones> direccionesTableView;

    @FXML
    private TableColumn<Direcciones, Integer> id;

    @FXML
    private TableColumn<Direcciones, String> denominacionColumn;

    @FXML
    private TextField filterInput;

    @FXML
    private ComboBox<String> filterField;

    // Declare direcciones at class level
    private ObservableList<Direcciones> direcciones;

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("idDireccion"));
        denominacionColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        loadTableData();

        filterField.getItems().addAll("ID DIRECCIONES", "DENOMINACION");
    }

    @FXML
    public void direcciones_denominacion_Insert() {
        String denominacion = denominacionInput.getText();
        DireccionesDAO.insertDireccion(denominacion);
        loadTableData();
        denominacionInput.clear();
    }

    @FXML
    public void applyFilter() {
        String filterValue = filterInput.getText();
        String filterFieldString = filterField.getValue();

        if(filterValue.isEmpty() || filterFieldString.isEmpty()){
            loadTableData(); // If no filter is set, load all data.
            return;
        }

        FilteredList<Direcciones> filteredData = new FilteredList<>(direcciones, b -> true);
        filteredData.setPredicate(direcciones -> {
            switch (filterFieldString) {
                case "ID DIRECCIONES":
                    // If filter is by ID
                    return String.valueOf(direcciones.getIdDireccion()).contains(filterValue);
                case "DENOMINACION":
                    // If filter is by Denominacion
                    return direcciones.getDenominacion().toLowerCase().contains(filterValue.toLowerCase());
            }
            return true; // If no filter is set, show all data.
        });

        SortedList<Direcciones> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(direccionesTableView.comparatorProperty());
        direccionesTableView.setItems(sortedData);
    }

    private void loadTableData() {
        direcciones = DireccionesDAO.getAll();
        direccionesTableView.setItems(direcciones);
    }
}
