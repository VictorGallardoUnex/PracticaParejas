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

import static es.unex.practicaparejas.App.Controladores.Utils.setMaxCharacterLimit;
import static es.unex.practicaparejas.App.Controladores.Utils.showInsercionOk;

public class TabDirecciones implements ITabController {

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

    private ObservableList<Direcciones> direcciones;

    @FXML
    public void initialize() {
        setMaxCharacterLimit(denominacionInput,150);
        id.setCellValueFactory(new PropertyValueFactory<>("idDireccion"));
        denominacionColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));

        loadTableData();

        filterField.getItems().addAll("ID DIRECCIONES", "DENOMINACION");
    }

    @FXML
    public void direcciones_denominacion_Insert() {
        String denominacion = denominacionInput.getText();
        if(DireccionesDAO.insertarDireccion(denominacion)){
            showInsercionOk();
        }
        loadTableData();
        denominacionInput.clear();
    }

    @FXML
    public void applyFilter() {
        String filterValue = filterInput.getText();
        String filterFieldString = filterField.getValue();

        if(filterValue.isEmpty() || filterFieldString.isEmpty()){
            loadTableData();
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

    @FXML
    public void loadSelectedDireccion() {
        // Get the selected direccion from the TableView
        Direcciones selectedDireccion = direccionesTableView.getSelectionModel().getSelectedItem();

        if (selectedDireccion != null) {
            // Load the selected direccion's data into the input fields
            denominacionInput.setText(selectedDireccion.getDenominacion());
        }
    }

    @FXML
    public void updateSelectedDireccion() {
        // Get the selected direccion from the TableView
        Direcciones selectedDireccion = direccionesTableView.getSelectionModel().getSelectedItem();

        if (selectedDireccion != null) {
            // Update the selected direccion with the data from the input fields
            selectedDireccion.setDenominacion(denominacionInput.getText());

            // Update the direccion in the database
            DireccionesDAO.actualizarDireccion(selectedDireccion.getIdDireccion(), selectedDireccion.getDenominacion());

            //
            // Clear the input fields
            denominacionInput.clear();

            // Update the original data list and reset the filter
            loadTableData();
        }
    }
    @FXML
    public void deleteSelectedDireccion() {
        // Obtener la dirección seleccionada de TableView
        Direcciones selectedDireccion = direccionesTableView.getSelectionModel().getSelectedItem();

        if (selectedDireccion != null) {
            // Confirmar al eliminacion
            boolean confirmed = Utils.confirmDeletion(selectedDireccion);

            if (confirmed) {
                // Eliminar la dirección de la base de datos
                DireccionesDAO.eliminarDireccion(selectedDireccion.getIdDireccion());

                // Limpiar los campos de entrada
                denominacionInput.clear();

                // Actualizar la lista de datos originales y restablecer el filtro
                loadTableData();
            }
        } else {
            Utils.showNoSelected();
        }
    }


    private void loadTableData() {
        direcciones = DireccionesDAO.getAll();
        direccionesTableView.setItems(direcciones);
    }
    @Override
    public void limpiarFormulario(){
        denominacionInput.clear();
        direccionesTableView.getSelectionModel().clearSelection();
    }
}
