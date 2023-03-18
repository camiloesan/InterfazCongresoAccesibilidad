module mx.uv.fei.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens mx.uv.fei.gui to javafx.fxml;
    exports mx.uv.fei.gui;
}