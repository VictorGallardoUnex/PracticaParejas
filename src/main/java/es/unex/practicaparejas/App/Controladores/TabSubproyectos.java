package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.ProyectosDAO;
import es.unex.practicaparejas.BD.DAO.SubproyectosDAO;
import es.unex.practicaparejas.BD.Modelos.Proyectos;
import es.unex.practicaparejas.BD.Modelos.Subproyectos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static es.unex.practicaparejas.App.Controladores.Utils.setMaxCharacterLimit;

public class TabSubproyectos implements ITabController{

    @FXML
    private TextField denominacionCInput;

    @FXML
    private TextField denominacionLInput;

    @FXML
    private DatePicker fechaInicioPicker;

    @FXML
    private ComboBox<Integer> idProyectoComboBox;

    @FXML
    private TableView<Subproyectos> subproyectosTableView;

    @FXML
    private TableColumn<Subproyectos, Integer> idColumn;

    @FXML
    private TableColumn<Subproyectos, String> denominacionCColumn;

    @FXML
    private TableColumn<Subproyectos, String> denominacionLColumn;

    @FXML
    private TableColumn<Subproyectos, Date> fechaInicioColumn;

    @FXML
    private TableColumn<Subproyectos, Integer> idProyectoColumn;

    // Declare subproyectos at class level
    private ObservableList<Subproyectos> subproyectos;

    @FXML
    private TextField filterInput;

    @FXML
    private ComboBox<String> filterField;

    @FXML
    public void initialize() {
        setMaxCharacterLimit(denominacionCInput,50);
        setMaxCharacterLimit(denominacionLInput,250);


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        denominacionCColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionC"));
        denominacionLColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionL"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        idProyectoColumn.setCellValueFactory(new PropertyValueFactory<>("idProyecto"));

        // Initialize the original data list
        List<Subproyectos> subproyectosList = SubproyectosDAO.getAll();
        subproyectos = FXCollections.observableArrayList(subproyectosList);

        // Create a filtered list that wraps the original data list
        FilteredList<Subproyectos> filteredData = new FilteredList<>(subproyectos);

        // Bind the filtered list to the TableView items
        subproyectosTableView.setItems(filteredData);

        // Set default selection
        filterField.getSelectionModel().selectFirst();
    }

    @FXML
    public void añadirDatos() {
        String denominacionC = denominacionCInput.getText();
        String denominacionL = denominacionLInput.getText();
        LocalDate fechaInicioLocalDate = fechaInicioPicker.getValue();
        Date fechaInicio = Date.valueOf(fechaInicioLocalDate);
        int idProyecto = idProyectoComboBox.getValue();

        Subproyectos subproyecto = new Subproyectos(denominacionC, denominacionL, fechaInicio, idProyecto);
        if(SubproyectosDAO.insertarSubproyecto(subproyecto)) {
            Utils.showInsercionOk();
        }

        clearInputFields();
        loadTableData();
        loadProyectosComboBox();
    }

    private void loadTableData() {
        subproyectos.clear();
        subproyectos.addAll(SubproyectosDAO.getAll());
    }

    private void loadProyectosComboBox() {
        List<Proyectos> proyectos = ProyectosDAO.getAll();
        ObservableList<Integer> idProyectoList = FXCollections.observableArrayList();
        for (Proyectos proyecto : proyectos) {
            idProyectoList.add(proyecto.getIdProyecto());
        }
        idProyectoComboBox.setItems(idProyectoList);
    }

    @FXML
    public void applyFilter() {
        String filterFieldText = filterField.getValue();
        String filterInputText = filterInput.getText();

        // Set the predicate of the filtered list based on the filter criteria
        subproyectosTableView.setItems(subproyectos.filtered(subproyecto -> {
            switch (filterFieldText) {
                case "ID":
                    // If filtering by "ID", convert ID to String for comparison
                    return String.valueOf(subproyecto.getId()).contains(filterInputText);
                case "Denominación C":
                    // If filtering by "Denominación C", use the denominacionC field for comparison
                    return subproyecto.getDenominacionC().contains(filterInputText);
                case "Denominación L":
                    // If filtering by "Denominación L", use the denominacionL field for comparison
                    return subproyecto.getDenominacionL().contains(filterInputText);
                case "Fecha Inicio":
                    // If filtering by "Fecha Inicio", convert Date to String for comparison
                    return subproyecto.getFechaInicio().toString().contains(filterInputText);
                case "ID Proyecto":
                    // If filtering by "ID Proyecto", convert ID to String for comparison
                    return String.valueOf(subproyecto.getIdProyecto()).contains(filterInputText);
                default:
                    // Unknown filter field, show all data
                    return true;
            }
        }));
    }

    @FXML
    public void loadSelectedSubproyecto() {
        // Get the selected subproyecto from the TableView
        Subproyectos selectedSubproyecto = subproyectosTableView.getSelectionModel().getSelectedItem();

        if (selectedSubproyecto != null) {
            // Load the selected subproyecto's data into the input fields
            denominacionCInput.setText(selectedSubproyecto.getDenominacionC());
            denominacionLInput.setText(selectedSubproyecto.getDenominacionL());
            fechaInicioPicker.setValue(selectedSubproyecto.getFechaInicio().toLocalDate());
            idProyectoComboBox.setValue(selectedSubproyecto.getIdProyecto());
        }
    }



    @FXML
    public void updateSelectedSubproyecto() {
        // Get the selected subproyecto from the TableView
        Subproyectos selectedSubproyecto = subproyectosTableView.getSelectionModel().getSelectedItem();

        if (selectedSubproyecto != null) {
            // Update the selected subproyecto with the data from the input fields
            selectedSubproyecto.setDenominacionC(denominacionCInput.getText());
            selectedSubproyecto.setDenominacionL(denominacionLInput.getText());
            selectedSubproyecto.setFechaInicio(Date.valueOf(fechaInicioPicker.getValue()));
            selectedSubproyecto.setIdProyecto(idProyectoComboBox.getValue());

            // Update the subproyecto in the database
            SubproyectosDAO.updateSubproyecto(selectedSubproyecto);

            // Clear the input fields
            clearInputFields();

            // Reload the table data
            loadTableData();
        }
    }

    private void clearInputFields() {
        denominacionCInput.clear();
        denominacionLInput.clear();
        fechaInicioPicker.setValue(null);
        idProyectoComboBox.setValue(null);
    }
    @FXML
    private void deleteSelectedSubproyecto() {
        Subproyectos selectedSubproyecto = subproyectosTableView.getSelectionModel().getSelectedItem();

        if (selectedSubproyecto != null) {
            boolean confirmed = Utils.confirmDeletion(selectedSubproyecto);

            if (confirmed) {
                try {
                    SubproyectosDAO.eliminarSubproyecto(selectedSubproyecto);
                } catch (SQLException e) {
                    handleSqlException(e);
                }

                loadTableData(); // Reload the table data
            }
        } else {
            Utils.showNoSelected();
        }
    }
    @Override
    public void limpiarFormulario(){
        clearInputFields();
        subproyectosTableView.getSelectionModel().clearSelection();

    }

}
