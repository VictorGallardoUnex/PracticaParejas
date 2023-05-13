//package es.unex.practicaparejas.App.Controladores;
//
//import es.unex.practicaparejas.BD.DAO.EstadosDAO;
//import es.unex.practicaparejas.BD.Modelos.Estado;
//import es.unex.practicaparejas.BD.Modelos.Situaciones;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class TabSituaciones implements Initializable {
//    @FXML
//    private TableView<Situaciones> situacionesTableView;
//    @FXML
//    private TableColumn<Situaciones, Integer> idColumn;
//    @FXML
//    private TableColumn<Situaciones, String> id_estadoColumn;
//    @FXML
//    private TableColumn<Situaciones, Integer> id_direccionColumn;
//
//    @FXML
//    private ComboBox<Estado> estadoComboBox;
//    @FXML
//    private ComboBox<String> direccionComboBox;
//    @FXML
//    private DatePicker datepicker;
//    @FXML
//    private Button insertarButton;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // Initialize the ComboBox with an empty list
//        estadoComboBox.setItems(FXCollections.emptyObservableList());
//
//        // Set the event handler for the "onShown" event
//        estadoComboBox.setOnShowing(event -> {
//            // Populate the ComboBox when it is being shown
//            List<Estado> estados = getAllEstadosFromDatabase();
//            ObservableList<Estado> items = convertEstadosToObservableList(estados);
//            estadoComboBox.setItems(items);
//        });
//
//        // Set up the cell factory to display the desired text in the ComboBox
//        estadoComboBox.setCellFactory(param -> new ListCell<Estado>() {
//            @Override
//            protected void updateItem(Estado item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    setText(item.getId() + " | " + item.getDenominacion());
//                }
//            }
//        });
//
//        // Set up the converter for the selected value
//        estadoComboBox.setConverter(new StringConverter<Estado>() {
//            @Override
//            public String toString(Estado estado) {
//                if (estado == null) {
//                    return null;
//                } else {
//                    return estado.getId() + " | " + estado.getDenominacion();
//                }
//            }
//
//            @Override
//            public Estado fromString(String string) {
//                // Not used in this case
//                return null;
//            }
//        });
//    }
//
//    private ObservableList<Estado> convertEstadosToObservableList(List<Estado> estados) {
//        return FXCollections.observableArrayList(estados);
//    }
//
//    private List<Estado> getAllEstadosFromDatabase() {
//        // Replace this method with your own logic to retrieve data from the database
//        // and return a List of Estado objects
//        // Example:
//        return EstadosDAO.getAll();
//    }
//
//    @FXML
//    private void insertarDatos() {
//        // Handle the "Insertar" button action here
//        // Retrieve the selected values from the ComboBoxes and DatePicker
//        Estado selectedEstado = estadoComboBox.getValue();
//        String selectedDireccion = direccionComboBox.getValue();
//        LocalDate selectedDate = datepicker.getValue();
//
//        // Create a new Situaciones object with the retrieved values
//        Situaciones situacion = new Situaciones();
//        situacion.setIdEstado(selectedEstado.getId());
//        situacion.setIdDireccion(Integer.parseInt(selectedDireccion));
//        situacion.setFechaRef(selectedDate);
//        // situacion.setFecha(selectedDate);
//
//        // Insert the Situaciones object into the database
//        boolean success = insertSituacionIntoDatabase(situacion);
//
//        // Handle the result of the insertion
//        if (success) {
//            // Clear the input fields or update the table view, if needed
//        } else {
//            // Display an error message or handle the failure case
//        }
//    }
//
//    private boolean insertSituacionIntoDatabase(Situaciones situacion) {
//        // Replace this method with your own logic to insert the Situaciones object into the database
//        // Return true if the insertion is successful, false otherwise
//        // Example:
//        // return SituacionesDAO.insert(situacion);
//        return false; // Placeholder return statement
//    }
//}
