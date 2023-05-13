//package es.unex.practicaparejas.App.Controladores;
//
//import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
//import es.unex.practicaparejas.BD.DAO.EstadosDAO;
//import es.unex.practicaparejas.BD.Modelos.Direcciones;
//import es.unex.practicaparejas.BD.Modelos.Estado;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.util.List;
//
//public class TabEstados {
//
//    @FXML
//    private TableView<Estado> estadosTableView;
//    @FXML
//    private TableColumn<Estado, Integer> id;
//    @FXML
//    private TableColumn<Estado, String> denominacionColumn;
//    @FXML
//    private TextField denominacionInput;
//
//
//    @FXML
//    protected void estados_denominacion_Insert() {
//        String denominacion = denominacionInput.getText();
//        if (denominacion.equals("")){
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Inserte un valor para poder guardar.");
//            alert.showAndWait();
//        } else {
//            EstadosDAO.insert(new Estado(denominacion));
//        }
//
//        // Repopulate the table with updated data
//        List<Estado> estadosList = EstadosDAO.getAll();
//        ObservableList<Estado> estadosObservableList = FXCollections.observableArrayList(estadosList);
//        estadosTableView.setItems(estadosObservableList);
//    }
//    @FXML
//    protected void initialize() {
//        // Set up the table columns
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        denominacionColumn.setCellValueFactory(new PropertyValueFactory<>("denominacion"));
//
//        // Populate the table with initial data
//        List<Estado> estadosList = EstadosDAO.getAll();
//        ObservableList<Estado> estadosObservableList = FXCollections.observableArrayList(estadosList);
//        estadosTableView.setItems(estadosObservableList);
//    }
//
//}
