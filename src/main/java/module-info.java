module dev.atellezfx.taller {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.atellezfx.taller to javafx.fxml;
    exports dev.atellezfx.taller;
}