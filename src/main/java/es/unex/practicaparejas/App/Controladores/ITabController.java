package es.unex.practicaparejas.App.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public interface ITabController {
    @FXML
    void limpiarFormulario();

    default void handleSqlException(SQLException e) {
        System.out.println("Error: " + e.getMessage());
        String exceptionMessage = e.getMessage();

        // Extraer el nombre de conflicto de restriccion
        String constraintName = "";
        int startIndex = exceptionMessage.indexOf("REFERENCE '") + 11;
        int endIndex = exceptionMessage.indexOf("'", startIndex);
        if (startIndex >= 0 && endIndex >= 0) {
            constraintName = exceptionMessage.substring(startIndex, endIndex);
        }

        // Extraer informacion de la tabal y columna
        String table = "";
        String column = "";
        startIndex = exceptionMessage.indexOf("tabla '") + 7;
        endIndex = exceptionMessage.indexOf("'", startIndex);
        if (startIndex >= 0 && endIndex >= 0) {
            table = exceptionMessage.substring(startIndex, endIndex);
        }

        startIndex = exceptionMessage.indexOf("column '") + 8;
        endIndex = exceptionMessage.indexOf("'", startIndex);
        if (startIndex >= 0 && endIndex >= 0) {
            column = exceptionMessage.substring(startIndex, endIndex);
        }

        // Crear dialogo de alerta
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Excepción SQL");
        alert.setHeaderText("Ha ocurrido un error al intentar realizar la operación.");
        alert.setContentText("Error: " + e.getMessage() + "\n\n" +
                "Restricciones en conflicto: " + constraintName + "\n" +
                "Tabla: " + table + "\n" +
                "Columna: " + column);
        alert.showAndWait();
    }

}
