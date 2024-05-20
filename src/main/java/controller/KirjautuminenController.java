package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.DataValidation;
import model.Kayttaja;
import view.App;

public class KirjautuminenController {

	
		
	  	@FXML
	    private GridPane KirjautumisTable;

	    @FXML
	    private TextField kayttajatunnus;
	    
	 
	    @FXML
	    private Label textFieldLabel;
	    
	   
	    @FXML
	    private CheckBox rememberTap;

	   
	    @FXML
	    private PasswordField salasana;

	   
	    @FXML
	    private Label pwsLabel;

	   
	    @FXML
	    private Button loginbtn;

	    
	    @FXML
	    private Button takaisin;
	    
	    ResourceBundle bundle = App.getResourceBundle();
	    
	   
        @FXML
        private void takaisin() throws IOException {
            System.out.println("takaisin.");
            App.setRoot("home");
        }
       
        /**
         * jarjestelmaan kirjautuminen ja kirjautumistietojen tarkistus tietokannasta
         * @throws IOException Kirjautuminen epaonnistui
         */
        @FXML
        void signinButtonClicked() throws IOException {
            	//App.showFeedbackStage();
        	
        	boolean tunnus1 = DataValidation.textFieldIsNull(kayttajatunnus, textFieldLabel, bundle.getString("username1"));
        	
        	Kayttaja kayttaja = Kayttaja.getInstance();
        	
        	kayttaja.alustaKirjautuminenKontrolleri(this);
        	
        	String tunnus = kayttajatunnus.getText();
        	String ss = salasana.getText();
        	
        	boolean toimii= kayttaja.tarkistaKayttaja(tunnus, ss);
        
        	if(!toimii && ss.isEmpty()) {
        	
	    	pwsLabel.setText(bundle.getString("password"));
	    	 }else if(!toimii){
        	  pwsLabel.setText("Kirjautuminen epäonnistui");
	    	 	}else {
	    	 		System.out.println("Kirjautuminen onnistui");
          }
        } 	
        
        public void työnTekijänakyma() throws IOException {
        	App.setRoot("tyontekijaNakyma");
        }
        
        public void asiakasNakyma() throws IOException {
        	App.setRoot("asiakasNakyma");
        }

    }   


	
/*
	@FXML
	private void takaisin() throws IOException {
		System.out.println("takaisin.");
		App.setRoot("home");
	}


	@FXML
	public void signinButtonClicked() throws IOException {

		String emailId = kayttajatunnus.getText();
		String password = salasana.getText();

		IRavintolavarausDAO loginDao = new RavintolavarausDAO();

		boolean flag = loginDao.validate(emailId, password);


		if (!flag) {
			System.out.println("Error in kirjautuminen");

		}else {
			App.setRoot("kayttajanNakyma");
		}
	}
}*/

