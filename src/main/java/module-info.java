module csci205_final_project {
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports minesweepermvc;
    opens minesweepermvc to javafx.fxml;
    exports minesweepermvc.model;
    opens minesweepermvc.model to javafx.fxml;
}