package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import view.App;

public class MainViewController implements Initializable {
		
			
			private ObservableList<String> languages = FXCollections.observableArrayList("FI", "EN");
	
	   		@FXML
	   		private Label textLabel;
	   		
	   		@FXML
            private Button signin;

            @FXML
            private Button signup;

            @FXML
            private ChoiceBox<String> lang;
            
            /**
             * kayttoliittyman kielen asettaminen
             */
        	@Override
			public void initialize(URL arg0, ResourceBundle arg1) {
				// TODO Auto-generated method stub
        		lang.setItems(languages);
        		lang.setValue(App.getKieli().toString().toUpperCase());
        		lang.setOnAction(event -> {
					try {
						getLanguage(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				
			}
        	
        	/**
        	 * kayttoliittyman kielen valinta
        	 * @param e
        	 * @throws IOException, jos kielen valinta ei onnistu
        	 */
        	public void getLanguage(ActionEvent e) throws IOException {
        				
        		String kieli1 = "FI";
        		
        		String kieli2 = "EN";
        		
        		if(lang.getSelectionModel().getSelectedItem() == kieli1) {
        			App.asetaKieli(kieli1);
        			App.setRoot("home");
        		} else {
        			App.asetaKieli(kieli2);
        			App.setRoot("home");
        		}
        		
        	}
			
      
			@FXML
			private void signinBtn() throws IOException {
				
        			App.setRoot("signin");
			}
			
			@FXML
			private void signupBtn() throws IOException {
				App.setRoot("signUp");
			}
			
			
			
			@FXML
			private void menuBtn() throws IOException {
				App.setRoot("menu");
			}

	}