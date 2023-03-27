module com.example.javafxstdnts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxstdnts to javafx.fxml;
    exports com.example.javafxstdnts;
    exports Model;
    opens Model to javafx.fxml;
    exports View;
    opens View to javafx.fxml;
}