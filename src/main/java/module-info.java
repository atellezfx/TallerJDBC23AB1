module dev.atellezfx.taller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens dev.atellezfx.taller to javafx.fxml;
    exports dev.atellezfx.taller;
}