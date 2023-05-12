package es.unex.practicaparejas.App.Controladores;

import es.unex.practicaparejas.BD.DAO.DireccionesDAO;
import es.unex.practicaparejas.BD.DAO.EstadosDAO;
import es.unex.practicaparejas.BD.Modelos.Direcciones;
import es.unex.practicaparejas.BD.Modelos.Estado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TabEstados {

    @FXML
    private TableView<Estado> estados_TableView;
    @FXML
    private TableColumn<Estado, Integer> idColumn;
    @FXML
    private TableColumn<Estado, String> direccionColumn;
    @FXML
    private TextField denominacionInput;


    @FXML
    protected void estados_denominacion_Insert() {
        String denominacion = denominacionInput.getText();

        Estado estado = new Estado(denominacion);
        EstadosDAO.insert(estado);

        // Repopulate the table with updated data
        estados_TableView.setItems((ObservableList<Estado>) EstadosDAO.getAll());
    }

    public TextField estados_denominacion_TextField;
    public Button estados_denominacion_Insert;
}
