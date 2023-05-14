package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.EstadosDAO;
import es.unex.practicaparejas.BD.Modelos.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

import static es.unex.practicaparejas.App.Controladores.Utils.setMaxCharacterLimit;

public class TabEstados implements ITabController {

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

    // Mantener una referencia a los datos originales para usarlos para filtrar
    private ObservableList<Estado> originalData;

    @FXML
    public void initialize() {


        setMaxCharacterLimit(denominacionInput,25);
        // Configura las columnas de la tabla
        id.setCellValueFactory(new PropertyValueFactory<>("idEstado"));
        denominacionColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        // Initializa la tabla con los datos de la base de datos
        originalData = FXCollections.observableArrayList(EstadosDAO.getAll());

        // Crear una lista filtrada con todos los datos
        FilteredList<Estado> filteredData = new FilteredList<>(originalData);

        // Enlaza la lista filtrada con la tabla
        estadosTableView.setItems(filteredData);

        // Establece el comparador de la lista filtrada
        filterField.getSelectionModel().selectFirst();
    }

    @FXML
    public void estados_denominacion_Insert() {
        // Crea un nuevo estado con los datos introducidos por el usuario
        Estado nuevoEstado = new Estado(denominacionInput.getText());

        // Inserta el nuevo estado en la base de datos
        if (EstadosDAO.insertarEstado(nuevoEstado)) {
            // Muestra un mensaje de inserción correcta
            Utils.showInsercionOk();
            // Limpia el campo de texto
            denominacionInput.clear();

            // Update the original data list and reset the filter
            originalData.setAll(EstadosDAO.getAll());
        } else {
            System.out.println("Error al insertar el estado");
        }
    }

    @Override
    public void aplicarFiltro() {
        if (filterInput.getText().isEmpty()) {
            ((FilteredList<Estado>) estadosTableView.getItems()).setPredicate(estado -> true);
        } else {
            String filterFieldText = filterField.getValue();
            String filterInputText = filterInput.getText();

            // Establece el predicado de la lista filtrada según el campo de filtro seleccionado
            ((FilteredList<Estado>) estadosTableView.getItems()).setPredicate(estado -> {
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

    @FXML
    public void loadSelectedEstado() {
        // Obtiene el estado seleccionado de la tabla
        Estado selectedEstado = estadosTableView.getSelectionModel().getSelectedItem();

        if (selectedEstado != null) {
            // Cargo los datos del estado seleccionado en los campos de texto
            denominacionInput.setText(selectedEstado.getDenominacion());
        }
    }

    @FXML
    public void updateSelectedEstado() {
        // Obtiene el estado seleccionado de la tabla
        Estado selectedEstado = estadosTableView.getSelectionModel().getSelectedItem();

        if (selectedEstado != null) {
            // Actualiza los datos del estado seleccionado con los datos introducidos por el usuario
            selectedEstado.setDenominacion(denominacionInput.getText());

            // Actualiza el estado en la base de datos
            EstadosDAO.actualizarEstado(selectedEstado);

            // Limpia el campo de texto
            denominacionInput.clear();

            // Actualiza la lista de datos originales y resetea el filtro
            originalData.setAll(EstadosDAO.getAll());
        }
    }
    @FXML
    public void deleteSelectedEstado() {
        Estado selectedEstado = estadosTableView.getSelectionModel().getSelectedItem();

        if (selectedEstado != null) {
            boolean confirmed = Utils.confirmDeletion(selectedEstado);

            if (confirmed) {
                try {
                    EstadosDAO.eliminarEstado(selectedEstado);
                } catch (SQLException e) {
                    handleSqlException(e);
                }
                denominacionInput.clear();
                originalData.setAll(EstadosDAO.getAll());
            }
        } else {
            Utils.showNoSelected();
        }
    }


    @Override
    public void limpiarFormulario() {
        denominacionInput.clear();
        estadosTableView.getSelectionModel().clearSelection();
    }
}
