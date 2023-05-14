package es.unex.practicaparejas.App.Controladores;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.Duration;

import java.util.Optional;

public class Utils {
    public static void setMaxCharacterLimit(TextField textField, int maxLength) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().length() > maxLength) {
                showErrorAlert("Límite de caracteres excedido", "El máximo permitido es " + maxLength + " caracteres.");
                return null; // Cancela el cambio si se excede el límite
            }
            return change;
        }));
    }

    private static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showInsercionOk() {
        String message = "La inserción se ha realizado correctamente.";
        double durationMillis = 500;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserción Exitosa");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Configurar la duración del mensaje emergente (en milisegundos)
        PauseTransition delay = new PauseTransition(Duration.millis(durationMillis));
        delay.setOnFinished(event -> alert.close());

        // Mostrar el mensaje emergente y comenzar la transición de pausa
        alert.show();
        delay.play();
    }
    public static <T> boolean confirmDeletion(T object) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminacion");
        alert.setHeaderText("Confirm Eliminacion");
        alert.setContentText("Estas seguro que quieres eliminar el siguiente elemento: " + object.toString() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    public static void showNoSelected(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se ha seleccionado ningun elemento");
        alert.setContentText("Por favor, seleccione un elemento de la tabla");
        alert.showAndWait();
    }


}
