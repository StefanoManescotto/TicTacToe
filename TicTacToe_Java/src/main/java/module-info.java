module com.example.tictactoe_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.example.tictactoe_java to javafx.fxml;
    exports com.example.tictactoe_java;
}