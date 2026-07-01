module com.moon.net {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.sql;

    exports com.moon.controladores;
    opens com.moon.controladores to javafx.fxml;
}
