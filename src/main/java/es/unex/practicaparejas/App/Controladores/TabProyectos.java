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
import java.time.LocalDate;
import java.util.List;

public class TabProyectos {

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

    @FXML
    public void initialize() {
        PRY_id_servicioColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_id_servicio"));
        PRY_fechainicioColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_fechainicio"));
        PRY_denominacioncColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_denominacionc"));
        PRY_denominacionlColumn.setCellValueFactory(new PropertyValueFactory<>("PRY_denominacionl"));

        loadTableData();

        filterField.getItems().addAll("ID Servicio", "Fecha Inicio", "Denominación C", "Denominación L");

        PRY_id_servicioInput.setOnShown(event -> loadComboBoxData());
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
        ProyectosDAO.insertProyecto(proyecto);

        loadTableData();

        PRY_id_servicioInput.getSelectionModel().clearSelection();
        PRY_fechainicioInput.getEditor().clear();
        PRY_denominacioncInput.clear();
        PRY_denominacionlInput.clear();
    }

    @FXML
    public void applyFilter() {
        String filterValue = filterInput.getText();
        String filterFieldString = filterField.getValue();

        if (filterValue.isEmpty() || filterFieldString.isEmpty()) {
            loadTableData(); // If no filter is set, load all data.
            return;
        }

        List<Proyectos> proyectosList = ProyectosDAO.getAll();
        ObservableList<Proyectos> proyectos = FXCollections.observableArrayList(proyectosList);

        FilteredList<Proyectos> filteredData = new FilteredList<>(proyectos, b -> true);
        filteredData.setPredicate(proyecto -> {
            switch (filterFieldString) {
                case "ID Servicio":
                    // If filter is by ID Servicio
                    return String.valueOf(proyecto.getPRY_id_servicio()).contains(filterValue);
                case "Fecha Inicio":
                    // If filter is by Fecha Inicio
                    return String.valueOf(proyecto.getPRY_fechainicio()).contains(filterValue);
                case "Denominación C":
                    // If filter is by Denominación C
                    return proyecto.getPRY_denominacionc().toLowerCase().contains(filterValue.toLowerCase());
                case "Denominación L":
                    // If filter is by Denominación L
                    return proyecto.getPRY_denominacionl().toLowerCase().contains(filterValue.toLowerCase());
            }
            return true; // If no filter is set, show all data.
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
}
