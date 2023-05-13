//package es.unex.practicaparejas.App.Controladores;
//
//import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
//import es.unex.practicaparejas.BD.DAO.ServiciosDAO;
//import es.unex.practicaparejas.BD.Modelos.Direcciones;
//import es.unex.practicaparejas.BD.Modelos.Servicios;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.util.List;
//
//public class TabServicios {
//    @FXML
//    private ComboBox<Integer> idDireccionComboBox;
//    @FXML
//    private TableView<Servicios> serviciosTableView;
//    @FXML
//    private TableColumn<Servicios, Integer> idColumn;
//    @FXML
//    private TableColumn<Servicios, Integer> idDireccionColumn;
//
//    @FXML
//    protected void añadirDatos() {
//        int idDireccion = idDireccionComboBox.getValue();
//
//        // Create a new Servicios object
//        Servicios servicio = new Servicios(idDireccion);
//
//        // Insert the Servicios object into the database
//        boolean success = ServiciosDAO.insertServicio(servicio);
//
//        if (success) {
//            System.out.println("Servicio added successfully");
//
//            // Refresh the ComboBox and table view with updated data
//            loadDireccionData();
//            loadServiciosData();
//        } else {
//            System.out.println("Failed to add servicio");
//        }
//    }
//
//    @FXML
//    protected void initialize() {
//        // Initialize the ComboBox with an empty list
//        idDireccionComboBox.setItems(FXCollections.emptyObservableList());
//
//        // Set the event handler for the "onShowing" event
//        idDireccionComboBox.setOnShowing(event -> {
//            // Populate the ComboBox when it is being shown
//            List<Direcciones> direccionesList = DireccionesDAO.getAll();
//            ObservableList<Integer> items = convertDireccionesToIds(direccionesList);
//            idDireccionComboBox.setItems(items);
//        });
//    }
//    private ObservableList<Integer> convertDireccionesToIds(List<Direcciones> direccionesList) {
//        ObservableList<Integer> ids = FXCollections.observableArrayList();
//        for (Direcciones direccion : direccionesList) {
//            ids.add(direccion.getId());
//        }
//        return ids;
//    }
//    private void loadDireccionData() {
//        // Retrieve all the direcciones from the database
//        List<Servicios> serviciosList = ServiciosDAO.getAll();
//
//        // Create an observable list to store the direccion IDs
//        ObservableList<Integer> idDireccionList = FXCollections.observableArrayList();
//        for (Servicios servicio : serviciosList) {
//            idDireccionList.add(servicio.getId_direccion());
//        }
//
//        // Set the data in the ComboBox
//        idDireccionComboBox.setItems(idDireccionList);
//    }
//
//    private void loadServiciosData() {
//        // Retrieve all the servicios from the database
//        List<Servicios> serviciosList = ServiciosDAO.getAll();
//
//        // Create an observable list to store the servicios data
//        ObservableList<Servicios> serviciosObservableList = FXCollections.observableArrayList(serviciosList);
//
//        // Set the data in the table view
//        serviciosTableView.setItems(serviciosObservableList);
//    }
//}
