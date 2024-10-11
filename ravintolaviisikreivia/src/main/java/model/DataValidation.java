package model;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

/**
 * Luokassa luodaan datan validaatio
 * @author R27
 * @since 2021/12/17
 *
 */
public class DataValidation {

	
	/**
	 * Katsoo, ettei kentän arvot ole null
	 * @param inputTextField tekstikenttä
	 * @param inputLabel syöttökenttä
	 * @param validationText validaation teksti
	 * @return false, jos textfield on null
	 */
	public static boolean textFieldIsNull(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if(inputTextField.getText().isEmpty()) {
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	
	/**
	 * Tarkastaa, onko tekstikentän arvo null
	 * @param inputTextField
	 * @param inputLabel
	 * @param validationText
	 * @return false, jos tekxtarean arvo on null
	 */
	public static boolean textAreaIsNull(TextArea inputTextField, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if(inputTextField.getText().isEmpty()) {
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	
	/**
	 * Tarkastaa syötteen pituuden
	 * @param inputTextField
	 * @param inputLabel
	 * @param validationText
	 * @param requiredLength
	 * @return true, jos datan pituus on oikea
	 */
	public static boolean dataLength(TextField inputTextField, Label inputLabel, String validationText, int requiredLength) {
		boolean isDataLength = true;
		String validationString = null; 
		
		if(inputTextField.getText().length() < requiredLength) {
			isDataLength = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isDataLength;
	}
	
	/**
	 * salasanan validointi(regular expression)
	 * @param pwsField 
	 * @param inputLabel syöttökenttä
	 * @param validationText validaation teksti
	 * @return true, jos data on validia
	 */
	public static boolean isValidPassword(PasswordField pwsField, Label inputLabel, String validationText) {
		 
		 boolean isPasswordValid = true;
		 String validationString = null;
		
		 if(!pwsField.getText().matches("^(?=.*[0-9])"
                 + "(?=.*[a-z])(?=.*[A-Z])"
                 + "(?=\\S+$).{8,20}$")) {
				isPasswordValid = false;
				validationString = validationText;
			}
		 	inputLabel.setText(validationString);
			return isPasswordValid;
	 }
	
	/**
	 * Tarkastaa sähköpostin muodon
	 * @param inputTextFieled
	 * @param inputLabel
	 * @param validationText
	 * @return true, jos sähköposti on oikeassa muodossa
	 */
	public static boolean emailFormat(TextField inputTextFieled, Label inputLabel, String validationText) {
		boolean isEmail = true;
		String validationString = null;
		
		if(!inputTextFieled.getText().matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			isEmail = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isEmail;
	}
	
	/**
	 * Tarkastaa, että syöte sisältää vain numeroita
	 * @param inputTextFieled
	 * @param inputLabel
	 * @param validationText
	 * @return true, jos syöttökentän teksti on numeraalinen
	 */
	public static boolean textNumeric(TextField inputTextFieled, Label inputLabel, String validationText) {
		boolean isNumeric = true;
		String validationString = null;
		
		if(!inputTextFieled.getText().matches("[0-9]+")) {
			isNumeric = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNumeric;
	}
	
	/**
	 * Tarkastaa, että syöte sisältää vain kirjaimia
	 * @param inputTextFieled
	 * @param inputLabel
	 * @param validationText
	 * @return true, jos syöttökentän teksti on kirjaimia
	 */
	public static boolean textAlphabet(TextField inputTextFieled, Label inputLabel, String validationText) {
		boolean isAlphabet = true;
		String validationString = null;
		
		if(!inputTextFieled.getText().matches("[a-z A-Z ä ö å]+")) {
			isAlphabet = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isAlphabet;
	}
	
	/**
	 * Tarkastaa, onko päivämäärän valitsin null
	 * @param inputDatePicker
	 * @param inputLabel
	 * @param validationText
	 * @return false, jos datePickerin arvo on null
	 */
		public static boolean datePickerIsNull(DatePicker inputDatePicker, Label inputLabel, String validationText) {
			boolean isNull = false;
			String validationString = null;
			if(inputDatePicker.getValue() == null) {
				isNull = true;
				validationString = validationText;
			}
			inputLabel.setText(validationString);
			return isNull;
		}
		
		/**
		 * Tarkastaa, onko valinta- valikon valinta null
		 * @param inputChoiceBox
		 * @param inputLabel
		 * @param validationText
		 * @return false, jos choiceboxin arvo on null
		 */
		public static boolean choiceBoxIsNull(ChoiceBox inputChoiceBox, Label inputLabel, String validationText) {
			boolean isNull = false;
			String validationString = null;
			if(inputChoiceBox.getValue() == null) {
				isNull = true;
				validationString = validationText;
			}
			inputLabel.setText(validationString);
			return isNull;
		}

}
