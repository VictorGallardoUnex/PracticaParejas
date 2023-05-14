package es.unex.practicaparejas.App;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Create the tab pane
        TabPane tabPane = new TabPane();

        // Load the FXML files for each tab
        Tab tab_Direcciones = new Tab("Direcciones");
        tab_Direcciones.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabDirecciones.fxml"))));
        Tab tab_Estados = new Tab("Estados");
        tab_Estados.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabEstados.fxml"))));
        Tab tab_Situaciones = new Tab("Situaciones");
        tab_Situaciones.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabSituaciones.fxml"))));
//
//
        Tab tab_Proyectos = new Tab("Proyectos");
        tab_Proyectos.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabProyectos.fxml"))));
        Tab tab_Subproyectos = new Tab("Subproyectos");
        tab_Subproyectos.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabSubproyectos.fxml"))));
        Tab tab_Servicios = new Tab("Servicios");
        tab_Servicios.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/es/unex/practicaparejas/App/TabServicios.fxml"))));

//        Tab tab2 = new Tab("Tab 2");
//        tab2.setContent(FXMLLoader.load(getClass().getResource("Tab2.fxml")));

        // Add the tabs to the tab pane
        tabPane.getTabs().addAll(tab_Direcciones,tab_Estados,tab_Servicios,tab_Proyectos,tab_Subproyectos,tab_Situaciones);//,tab_Situaciones,,tab_Subproyectos, tab_Servicios);
        tabPane.setStyle("-fx-padding: 20px;");
        // Create a scene using the tab pane
        Scene scene = new Scene(tabPane, 800, 600);

        // Set the scene on the stage and show the stage
        stage.setScene(scene);
        stage.setTitle("Gestor Base de Datos - Proyectos");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





