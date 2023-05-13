package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.EstadosDAO;
import es.unex.practicaparejas.BD.Modelos.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabEstados {

    @FXML
    private TextField denominacionInput;

    @FXML
    private TableView<Estado> estadosTableView;

    @FXML
    private TableColumn<Estado, Integer> id;

    @FXML
    private TableColumn<Estado, String> denominacionColumn;

    @FXML
    private TextField filterInput;

    @FXML
    private ComboBox<String> filterField;

    // Keep a reference to the original data list
    private ObservableList<Estado> originalData;

    @FXML
    public void initialize() {
        // Configura las columnas de la tabla
        id.setCellValueFactory(new PropertyValueFactory<>("idEstado"));
        denominacionColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        // Initialize the original data list
        originalData = FXCollections.observableArrayList(EstadosDAO.getAll());

        // Create a filtered list that wraps the original data list
        FilteredList<Estado> filteredData = new FilteredList<>(originalData);

        // Bind the filtered list to the TableView items
        estadosTableView.setItems(filteredData);

        // Set default selection
        filterField.getSelectionModel().selectFirst();
    }

    @FXML
    public void estados_denominacion_Insert() {
        // Crea un nuevo estado con los datos introducidos por el usuario
        Estado nuevoEstado = new Estado(denominacionInput.getText());

        // Inserta el nuevo estado en la base de datos
        if(EstadosDAO.insert(nuevoEstado)) {
            // Limpia el campo de texto
            denominacionInput.clear();

            // Update the original data list and reset the filter
            originalData.setAll(EstadosDAO.getAll());
        } else {
            System.out.println("Error al insertar el estado");
        }
    }

    @FXML
    public void applyFilter() {
        if (filterInput.getText().isEmpty()) {
            // No filter text, show all data
            ((FilteredList<Estado>)estadosTableView.getItems()).setPredicate(estado -> true);
        } else {
            String filterFieldText = filterField.getValue();
            String filterInputText = filterInput.getText();

            // Set the predicate of the filtered list based on the filter criteria
            ((FilteredList<Estado>)estadosTableView.getItems()).setPredicate(estado -> {
                if (filterFieldText.equals("ID ESTADOS")) {
                    // If filtering by "ID ESTADOS", convert ID to String for comparison
                    return String.valueOf(estado.getIdEstado()).contains(filterInputText);
                } else if (filterFieldText.equals("DENOMINACION")) {
                    // If filtering by "DENOMINACION", use the denominacion field for comparison
                    return estado.getDenominacion().contains(filterInputText);
                } else {
                    // Unknown filter field, show all data
                    return true;
                }
            });
        }
    }
}
