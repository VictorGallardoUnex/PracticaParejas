module es.unex.practicaparejas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires com.microsoft.sqlserver.jdbc;

    opens es.unex.practicaparejas.App to javafx.fxml;
    exports es.unex.practicaparejas.App;

    opens es.unex.practicaparejas.App.Controladores to javafx.fxml;
    exports es.unex.practicaparejas.App.Controladores;

    opens es.unex.practicaparejas.BD.Modelos to javafx.fxml;
    exports es.unex.practicaparejas.BD.Modelos;
}

