package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.SituacionesDAO;
import es.unex.practicaparejas.BD.DAO.EstadosDAO;
import es.unex.practicaparejas.BD.DAO.SubproyectosDAO;
import es.unex.practicaparejas.BD.Modelos.Estado;
import es.unex.practicaparejas.BD.Modelos.Subproyectos;
import es.unex.practicaparejas.BD.Modelos.Situaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TabSituaciones implements ITabController {

    @FXML
    private ComboBox<Integer> estadoComboBox;

    @FXML
    private ComboBox<Integer> subproyectoComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Situaciones> situacionesTableView;

    @FXML
    private TableColumn<Situaciones, Integer> idSubproyectoColumn;

    @FXML
    private TableColumn<Situaciones, Integer> idEstadoColumn;

    @FXML
    private TableColumn<Situaciones, String> fechaRefColumn;

    private ObservableList<Situaciones> situaciones;
    @FXML
    private ComboBox<String> filterField;
    @FXML
    private TextField filterInput;

    @FXML
    public void initialize() {
        idSubproyectoColumn.setCellValueFactory(new PropertyValueFactory<>("idSubproyecto"));
        idEstadoColumn.setCellValueFactory(new PropertyValueFactory<>("idEstado"));
        fechaRefColumn.setCellValueFactory(new PropertyValueFactory<>("fechaRef"));
        subproyectoComboBox.setOnShown(event -> loadSubproyectosComboBox());
        estadoComboBox.setOnShown(event -> loadEstadosComboBoxe());
        loadTableData();
    }

    @FXML
    public void insertarDatos() {
        int idSubproyecto = subproyectoComboBox.getValue();
        int idEstado = estadoComboBox.getValue();
        LocalDate fechaRefLocalDate = datePicker.getValue();

        // Convertir LocalDate a java.sql.Date
        Date fechaRef = Date.valueOf(fechaRefLocalDate);

        Situaciones situacion = new Situaciones(idSubproyecto, idEstado, fechaRef);
        if (SituacionesDAO.insertarSituacion(situacion)){
            Utils.showInsercionOk();
        }

        loadTableData();
        clearInputFields();
    }

    private void loadEstadosComboBoxe() {
        List<Estado> estadosList = EstadosDAO.getAll();
        estadoComboBox.getItems().clear(); // Clear the combo box before adding new items
        for (Estado estado : estadosList) {
            estadoComboBox.getItems().add(estado.getIdEstado());
        }
    }

    private void loadSubproyectosComboBox() {
        List<Subproyectos> subproyectosList = SubproyectosDAO.getAll();
        subproyectoComboBox.getItems().clear(); // Limpia el combo box antes de añadir nuevos items
        for (Subproyectos subproyecto : subproyectosList) {
            subproyectoComboBox.getItems().add(subproyecto.getIdSubproyecto());
        }
    }

    private void loadTableData() {
        List<Situaciones> situacionesList = SituacionesDAO.getAllSituaciones();
        situaciones = FXCollections.observableList(situacionesList);
        situacionesTableView.setItems(situaciones);
    }
    @FXML
    public void loadSelectedEstado() {
        // Obtenemos el estado seleccionado
        Situaciones selectedSituacion = situacionesTableView.getSelectionModel().getSelectedItem();

        if (selectedSituacion != null) {
            int idSubproyecto = selectedSituacion.getIdSubproyecto();
            int idEstado = selectedSituacion.getIdEstado();
            LocalDate fechaRef = selectedSituacion.getFechaRef().toLocalDate();

            subproyectoComboBox.setValue(idSubproyecto);
            estadoComboBox.setValue(idEstado);
            datePicker.setValue(fechaRef);
        }
    }


    @FXML
    public void aplicarFiltro() {
        String filterFieldText = filterField.getValue();
        String filterInputText = filterInput.getText();

        situacionesTableView.setItems(situaciones.filtered(situacion -> {
            switch (filterFieldText) {
                case "ID Subproyecto":
                    return String.valueOf(situacion.getIdSubproyecto()).contains(filterInputText);
                case "ID Estado":
                    return String.valueOf(situacion.getIdEstado()).contains(filterInputText);
                case "Fecha":
                    return situacion.getFechaRef().toString().contains(filterInputText);
                default:
                    return true;
            }
        }));
    }

    @FXML
    public void actualizarFila() {
        Situaciones selectedSituacion = situacionesTableView.getSelectionModel().getSelectedItem();

        if (selectedSituacion != null) {
            int idSubproyecto = subproyectoComboBox.getValue();
            int idEstado = estadoComboBox.getValue();
            LocalDate fechaRefLocalDate = datePicker.getValue();

            Date fechaRef = Date.valueOf(fechaRefLocalDate);

            selectedSituacion.setIdSubproyecto(idSubproyecto);
            selectedSituacion.setIdEstado(idEstado);
            selectedSituacion.setFechaRef(fechaRef);

            SituacionesDAO.actualizarSituaciones(selectedSituacion);

            loadTableData();
            clearInputFields();
        }
    }

    private void clearInputFields() {
        estadoComboBox.getSelectionModel().clearSelection();
        subproyectoComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
    }
    @FXML
    public void deleteSelectedSituacion() {
        Situaciones selectedSituacion = situacionesTableView.getSelectionModel().getSelectedItem();

        if (selectedSituacion != null) {
            boolean confirmed = Utils.confirmDeletion(selectedSituacion);

            if (confirmed) {
                try {
                    SituacionesDAO.eliminarSituacion(selectedSituacion);
                } catch (SQLException e) {
                    handleSqlException(e);
                }

                loadTableData();
                clearInputFields();
            }
        } else {
            Utils.showNoSelected();
        }
    }
    @Override
    public void limpiarFormulario() {
        clearInputFields();
        situacionesTableView.getSelectionModel().clearSelection();
    }
}
