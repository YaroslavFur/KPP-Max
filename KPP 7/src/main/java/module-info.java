module com.example.kpp_7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kpp_7 to javafx.fxml;
    exports com.example.kpp_7;
}