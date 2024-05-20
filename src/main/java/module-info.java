module ravintolaviisikreivia {

    requires javafx.controls;
    requires javafx.fxml;

	requires javafx.base;
	requires javafx.graphics;
	requires java.base;
	requires org.hibernate.orm.core;
	requires java.persistence;
	requires java.sql;
	requires java.naming;
	requires com.sun.istack.runtime;

	opens model;
    exports model;
    
    opens controller;
    exports controller;

	
    opens view;
    exports view;
}
