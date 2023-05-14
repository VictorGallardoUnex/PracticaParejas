package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.ProyectosDAO;
import es.unex.practicaparejas.BD.DAO.ServiciosDAO;
import es.unex.practicaparejas.BD.Modelos.Proyectos;
import es.unex.practicaparejas.BD.Modelos.Servicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static es.unex.practicaparejas.App.Controladores.Utils.setMaxCharacterLimit;

public class TabProyectos implements ITabController {

    @FXML
    private ComboBox<Integer> PRY_id_servicioInput;

    @FXML
    private DatePicker PRY_fechainicioInput;

    @FXML
    private TextField PRY_denominacioncInput;

    @FXML
    private TextField PRY_denominacionlInput;

    @FXML
    private TableView<Proyectos> proyectosTableView;

    @FXML
    private TableColumn<Proyectos, Integer> PRY_id_servicioColumn;

    @FXML
    private TableColumn<Proyectos, Date> PRY_fechainicioColumn;

    @FXML
    private TableColumn<Proyectos, String> PRY_denominacioncColumn;

    @FXML
    private TableColumn<Proyectos, String> PRY_denominacionlColumn;

    @FXML
    private TextField filterInput;

    @FXML
    private ComboBox<String> filterField;

    private Proyectos selectedProyecto; // Variable para almacenar el proyecto seleccionado

    @FXML
    public void initialize() {

        setMaxCharacterLimit(PRY_denominacioncInput,50);
        setMaxCharacterLimit(PRY_denominacionlInput,250);

        PRY_id_servicioColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_id_servicio"));
        PRY_fechainicioColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_fechainicio"));
        PRY_denominacioncColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_denominacionc"));
        PRY_denominacionlColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_denominacionl"));

        loadTableData();

        filterField.getItems().addAll("ID Servicio", "Fecha Inicio", "Denominación C", "Denominación L");

        PRY_id_servicioInput.setOnShown(event -> loadComboBoxData());

        // Configurar el manejador de eventos para la selección de filas en la tabla
        proyectosTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Al seleccionar una fila, rellenar los campos con los datos del proyecto
                selectedProyecto = newSelection;
                PRY_id_servicioInput.setValue(selectedProyecto.getPRY_id_servicio());
                PRY_fechainicioInput.setValue(selectedProyecto.getPRY_fechainicio().toLocalDate());
                PRY_denominacioncInput.setText(selectedProyecto.getPRY_denominacionc());
                PRY_denominacionlInput.setText(selectedProyecto.getPRY_denominacionl());
            } else {
                // Si no hay fila seleccionada, limpiar los campos
                selectedProyecto = null;
                PRY_id_servicioInput.getSelectionModel().clearSelection();
                PRY_fechainicioInput.setValue(null);
                PRY_denominacioncInput.clear();
                PRY_denominacionlInput.clear();
            }
        });
    }

    private void loadComboBoxData() {
        List<Servicios> serviciosList = ServiciosDAO.getAll();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (Servicios servicio : serviciosList) {
            ids.add(servicio.getSRV_id_servicio());
        }
        PRY_id_servicioInput.setItems(ids);
    }

    @FXML
    public void proyectos_Insert() {
        int id_servicio = PRY_id_servicioInput.getValue();
        LocalDate fecha_inicio_local = PRY_fechainicioInput.getValue();
        Date fecha_inicio = Date.valueOf(fecha_inicio_local);
        String denominacionc = PRY_denominacioncInput.getText();
        String denominacionl = PRY_denominacionlInput.getText();

        Proyectos proyecto = new Proyectos(id_servicio, fecha_inicio, denominacionc, denominacionl);
        if (ProyectosDAO.insertarProyecto(proyecto)){
            Utils.showInsercionOk();
        }

        loadTableData();

        PRY_id_servicioInput.getSelectionModel().clearSelection();
        PRY_fechainicioInput.getEditor().clear();
        PRY_denominacioncInput.clear();
        PRY_denominacionlInput.clear();
    }

    @FXML
    public void actualizarProyecto() {
        if (selectedProyecto != null) {
            int id_servicio = PRY_id_servicioInput.getValue();
            LocalDate fecha_inicio_local = PRY_fechainicioInput.getValue();
            Date fecha_inicio = Date.valueOf(fecha_inicio_local);
            String denominacionc = PRY_denominacioncInput.getText();
            String denominacionl = PRY_denominacionlInput.getText();

            // Actualizar los datos del proyecto seleccionado
            selectedProyecto.setPRY_fechainicio(fecha_inicio);
            selectedProyecto.setPRY_denominacionc(denominacionc);
            selectedProyecto.setPRY_denominacionl(denominacionl);

            // Guardar los cambios en la base de datos
            ProyectosDAO.actualizarProyecto(selectedProyecto);

            // Actualizar la tabla
            loadTableData();

            // Limpiar la selección
            proyectosTableView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void applyFilter() {
        String filterValue = filterInput.getText();
        String filterFieldString = filterField.getValue();

        if (filterValue.isEmpty() || filterFieldString.isEmpty()) {
            loadTableData(); // Si no se establece ningún filtro, cargar todos los datos.
            return;
        }

        List<Proyectos> proyectosList = ProyectosDAO.getAll();
        ObservableList<Proyectos> proyectos = FXCollections.observableArrayList(proyectosList);

        FilteredList<Proyectos> filteredData = new FilteredList<>(proyectos, b -> true);
        filteredData.setPredicate(proyecto -> {
            switch (filterFieldString) {
                case "ID Servicio":
                    // Si el filtro es por ID Servicio
                    return String.valueOf(proyecto.getPRY_id_servicio()).contains(filterValue);
                case "Fecha Inicio":
                    // Si el filtro es por Fecha Inicio
                    return String.valueOf(proyecto.getPRY_fechainicio()).contains(filterValue);
                case "Denominación C":
                    // Si el filtro es por Denominación C
                    return proyecto.getPRY_denominacionc().toLowerCase().contains(filterValue.toLowerCase());
                case "Denominación L":
                    // Si el filtro es por Denominación L
                    return proyecto.getPRY_denominacionl().toLowerCase().contains(filterValue.toLowerCase());
            }
            return true; // Si no se establece ningún filtro, mostrar todos los datos
        });

        SortedList<Proyectos> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(proyectosTableView.comparatorProperty());
        proyectosTableView.setItems(sortedData);
    }


    private void loadTableData() {
        List<Proyectos> proyectosList = ProyectosDAO.getAll();
        ObservableList<Proyectos> proyectos = FXCollections.observableArrayList(proyectosList);
        proyectosTableView.setItems(proyectos);
    }
    @FXML
    public void deleteSelectedProyecto() {
        Proyectos selectedProyecto = proyectosTableView.getSelectionModel().getSelectedItem();

        if (selectedProyecto != null) {
            boolean confirmed = Utils.confirmDeletion(selectedProyecto);

            if (confirmed) {
                try {
                    ProyectosDAO.eliminarProyecto(selectedProyecto);
                } catch (SQLException e) {
                    handleSqlException(e);
                }
                loadTableData();
                proyectosTableView.getSelectionModel().clearSelection();
            }
        } else {
            Utils.showNoSelected();
        }
    }
    @Override
    public void limpiarFormulario(){
        PRY_id_servicioInput.getSelectionModel().clearSelection();
        PRY_fechainicioInput.getEditor().clear();
        PRY_denominacioncInput.clear();
        PRY_denominacionlInput.clear();
        proyectosTableView.getSelectionModel().clearSelection();
    }
}