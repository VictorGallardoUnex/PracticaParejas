package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.SubproyectosDAO;
import es.unex.practicaparejas.BD.Modelos.Subproyectos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;

public class TabSubproyectos {

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
    @FXML
    private TextField idSubproyectoInput;
    @FXML
    private TextField denominacionCInput;
    @FXML
    private TextField denominacionLInput;
    @FXML
    private TextField fechaInicioInput;
    @FXML
    private TextField idProyectoInput;

    @FXML
    protected void añadirDatos() {
        String idSubproyecto = idSubproyectoInput.getText();
        String denominacionC = denominacionCInput.getText();
        String denominacionL = denominacionLInput.getText();
        String fechaInicioText = fechaInicioInput.getText();
        String idProyectoText = idProyectoInput.getText();

        // Create a new Subproyectos object with the input values
        Subproyectos subproyecto = new Subproyectos(
                Integer.parseInt(idSubproyecto),
                denominacionC,
                denominacionL,
                Date.valueOf(fechaInicioText),
                Integer.parseInt(idProyectoText)
        );

        // Call the insertSubproyecto method in the DAO class
        boolean success = SubproyectosDAO.insertSubproyecto(subproyecto);

        if (success) {
            // Repopulate the table with updated data
            List<Subproyectos> subproyectosList = SubproyectosDAO.getAll();
            ObservableList<Subproyectos> subproyectosObservableList = FXCollections.observableArrayList(subproyectosList);
            subproyectosTableView.setItems(subproyectosObservableList);
        } else {
            // Show an error message or handle the failure
        }
    }

    @FXML
    protected void initialize() {
        // Set up the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        denominacionCColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionC"));
        denominacionLColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionL"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        idProyectoColumn.setCellValueFactory(new PropertyValueFactory<>("idProyecto"));

        // Populate the table with initial data
        List<Subproyectos> subproyectosList = SubproyectosDAO.getAll();
        ObservableList<Subproyectos> subproyectosObservableList = FXCollections.observableArrayList(subproyectosList);
        subproyectosTableView.setItems(subproyectosObservableList);
    }
}