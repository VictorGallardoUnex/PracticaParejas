//package es.unex.practicaparejas.App.Controladores;
//
//import es.unex.practicaparejas.BD.DAO.ProyectosDAO;
//import es.unex.practicaparejas.BD.DAO.ServiciosDAO;
//import es.unex.practicaparejas.BD.Modelos.Proyectos;
//import es.unex.practicaparejas.BD.Modelos.Servicios;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.util.List;
//
//public class TabProyectos {
//    @FXML
//    private TextField denominacionCInput;
//    @FXML
//    private TextField denominacionLInput;
//    @FXML
//    private TextField fechaInicioInput;
//    @FXML
//    private ComboBox<Integer> idServicioComboBox;
//    @FXML
//    private TableView<Proyectos> proyectosTableView;
//    @FXML
//    private TableColumn<Proyectos, String> denominacionCColumn;
//    @FXML
//    private TableColumn<Proyectos, String> denominacionLColumn;
//    @FXML
//    private TableColumn<Proyectos, String> fechaInicioColumn;
//    @FXML
//    private TableColumn<Proyectos, Integer> idServicioColumn;
//
//    @FXML
//    protected void añadirDatos() {
//        String denominacionC = denominacionCInput.getText();
//        String denominacionL = denominacionLInput.getText();
//        String fechaInicioText = fechaInicioInput.getText();
//        int idServicio = idServicioComboBox.getValue();
//
//        if (denominacionC.isEmpty() || denominacionL.isEmpty() || fechaInicioText.isEmpty() || idServicio == 0) {
//            // Display an error message if any field is empty
//            System.out.println("Please fill in all the fields");
//            return;
//        }
//
//        try {
//            // Parse the date input
//            java.sql.Date fechaInicio = java.sql.Date.valueOf(fechaInicioText);
//
//            // Create a new Proyectos object
//            Proyectos proyecto = new Proyectos(idServicio, fechaInicio, denominacionC, denominacionL);
//
//            // Insert the Proyectos object into the database
//            boolean success = ProyectosDAO.insertProyecto(proyecto);
//
//            if (success) {
//                System.out.println("Proyecto added successfully");
//
//                // Clear the input fields
//                denominacionCInput.clear();
//                denominacionLInput.clear();
//                fechaInicioInput.clear();
//                idServicioComboBox.getSelectionModel().clearSelection();
//
//                // Refresh the table view with updated data
//                loadProyectosData();
//            } else {
//                System.out.println("Failed to add proyecto");
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid date format");
//        }
//    }
//
//    @FXML
//    protected void initialize() {
//        // Set up the table columns
//        denominacionCColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionC"));
//        denominacionLColumn.setCellValueFactory(new PropertyValueFactory<>("denominacionL"));
//        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
//        idServicioColumn.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
//
//        // Populate the ComboBox with servicios IDs
//        List<Servicios> serviciosList = ServiciosDAO.getAll();
//        ObservableList<Integer> idServicioList = FXCollections.observableArrayList();
//        for (Servicios servicio : serviciosList) {
//            idServicioList.add(servicio.getId());
//        }
//        idServicioComboBox.setItems(idServicioList);
//
//        // Populate the table with initial data
//        loadProyectosData();
//    }
//
//
//
//
//    private void loadProyectosData() {
//        // Retrieve all the proyectos from the database
//        List<Proyectos> proyectosList = ProyectosDAO.getAll();
//
//// Create an observable list to store the proyectos data
//        ObservableList<Proyectos> proyectosObservableList = FXCollections.observableArrayList(proyectosList);
//
//// Set the data in the table view
//        proyectosTableView.setItems(proyectosObservableList);
//    }
//}
