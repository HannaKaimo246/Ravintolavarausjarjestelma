package view;


import javafx.application.Application; 

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import model.Kayttaja;
import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * 
 * JavaFX App applikaation kaynnitys ja aloitus- näkyman ja kielen asetus
 * @author R27
 * @since 2021/12/17
 * 
 */
public class App extends Application {
	

    private static Scene scene;
    private static Stage primaryStage;
    private BorderPane rootLayout;
    private static Stage stage2;
    
    public static Locale l;
 
     
    public void init() {
    	Kayttaja kayttaja = Kayttaja.getInstance();
    	l = new Locale("FI");
    }
    /**
     * getteri primary stagelle
     * @return primaryStage
     */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

   /**
    * getteri Scenelle
    * @return scene
    */

	
	public static Scene getScene() {
		return scene;
	}
	

    @Override
    public void start(Stage stage) throws IOException {
    	
    	stage2 = stage;
    	scene = new Scene(loadFXML("home"), 700, 600);
    	stage2.setScene(scene);
    	stage2.setTitle("Ravintolavarausjärjestelmä");
        stage.show();
     
        
    }

        
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        
        
    }

    public static void asetaKieli(String uuskieli) {
    	 l = new Locale(uuskieli);
    }
    
    public static Locale getKieli() {
    	return l;
    }
    
    public static ResourceBundle getResourceBundle() {
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("view.i18n.bundle",getKieli());
    
		return bundle;
    	
    }

    
    public static Parent loadFXML(String fxml) throws IOException {
    	
    	Locale currentLocale = Locale.getDefault();
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("view.i18n.bundle",l);
    	
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), bundle);
       
        return fxmlLoader.load();

    }
    
    public static byte[] lataaKuva() {
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.getExtensionFilters().addAll(
    		     new FileChooser.ExtensionFilter("PNG Files", "*.png"),
    		     new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
    		);
    	
    	File file = fileChooser.showOpenDialog(primaryStage);
    	
    	if (file == null) {
    		return null;
    	}
    	
    	
        byte[] bFile = new byte[(int) file.length()];
      
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
		return bFile;
    	
    }

    public static void main(String[] args) {
        launch();
    }


}

