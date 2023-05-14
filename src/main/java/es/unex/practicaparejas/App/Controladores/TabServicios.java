package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
import es.unex.practicaparejas.BD.DAO.ServiciosDAO;
import es.unex.practicaparejas.BD.Modelos.Direcciones;
import es.unex.practicaparejas.BD.Modelos.Servicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TabServicios {

    @FXML
    private TableView<Servicios> serviciosTableView;

    @FXML
    private TableColumn<Servicios, Integer> idColumn;

    @FXML
    private TableColumn<Servicios, Integer> idDireccionColumn;

    @FXML
    private ComboBox<String> filterFieldComboBox;

    @FXML
    private TextField filterInputTextField;
    @FXML
    private ComboBox<Integer> idDireccionComboBox;
    @FXML
    public void initialize() {
        // Initialize the table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("SRV_id_servicio"));
        idDireccionColumn.setCellValueFactory(new PropertyValueFactory<>("SRV_id_dirgen"));

        // Initialize the filter ComboBox
        filterFieldComboBox.getItems().addAll("ID", "ID DIRECCION");

        loadTableData();
    }


    @FXML
    public void añadirDatos() {
        Integer idDireccion = idDireccionComboBox.getValue();
        Servicios newServicio = new Servicios(idDireccion);
        if (ServiciosDAO.insertServicio(newServicio)) {
            loadTableData();
        }
        idDireccionComboBox.getSelectionModel().clearSelection();
    }

    private void loadTableData() {
        ObservableList<Servicios> servicios = FXCollections.observableArrayList(ServiciosDAO.getAll());
        serviciosTableView.setItems(servicios);
    }
    @FXML
    public void loadComboBoxData() {
        List<Direcciones> direccionesList = DireccionesDAO.getAll();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (Direcciones direccion : direccionesList) {
            ids.add(direccion.getIdDireccion());
        }
        idDireccionComboBox.setItems(ids);
    }
    @FXML
    public void addServicio() {
        int idDireccion = idDireccionComboBox.getValue();

        Servicios newServicio = new Servicios(idDireccion);
        if (ServiciosDAO.insertServicio(newServicio)) {
            loadTableData(); // Refresh table data after successful insert
        } else {
            System.out.println("Error inserting new servicio");
        }
    }

    @FXML
    public void updateServicio() {
        Servicios selectedServicio = serviciosTableView.getSelectionModel().getSelectedItem();
        if (selectedServicio == null) {
            System.out.println("No servicio selected for update");
            return;
        }

        int idDireccion = idDireccionComboBox.getValue();
        selectedServicio.setSRV_id_dirgen(idDireccion);

        if (ServiciosDAO.updateServicio(selectedServicio)) {
            loadTableData(); // Refresh table data after successful update
        } else {
            System.out.println("Error updating servicio");
        }
    }

    @FXML
    public void deleteServicio() {
        Servicios selectedServicio = serviciosTableView.getSelectionModel().getSelectedItem();
        if (selectedServicio == null) {
            System.out.println("No servicio selected for delete");
            return;
        }

        if (ServiciosDAO.deleteServicio(selectedServicio.getSRV_id_dirgen())) {
            loadTableData(); // Refresh table data after successful delete
        } else {
            System.out.println("Error deleting servicio");
        }
    }
    @FXML
    public void applyFilter() {
        String filterValue = filterInputTextField.getText();
        String filterFieldString = filterFieldComboBox.getValue();

        if (filterValue.isEmpty() || filterFieldString.isEmpty()) {
            loadTableData(); // Si no se establece ningún filtro, cargar todos los datos.
            return;
        }

        FilteredList<Servicios> filteredData = new FilteredList<>(serviciosTableView.getItems(), b -> true);
        filteredData.setPredicate(servicio -> {
            switch (filterFieldString) {
                case "ID":
                    // Si el filtro es por ID
                    return String.valueOf(servicio.getSRV_id_servicio()).contains(filterValue);
                case "ID DIRECCION":
                    // Si el filtro es por ID de Dirección
                    return String.valueOf(servicio.getSRV_id_dirgen()).contains(filterValue);
            }
            return true; // Si no se establece ningún filtro, mostrar todos los datos.
        });

        SortedList<Servicios> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(serviciosTableView.comparatorProperty());
        serviciosTableView.setItems(sortedData);
    }



}
