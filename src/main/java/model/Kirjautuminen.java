package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Luokassa luodaan kirjautuminen
 * @author R27
 * @since 2021/12/17
 */
@Entity
@Table(name="Kirjautuminen")
public class Kirjautuminen {
	
	/**
	 * asiakkaan yksilöivä ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="hannkaim")
	@Column(name="AsiakasID")
	public int asiakasID;
	
	/**
	 * asiakkaan käyttäjätunnus
	 */
	@Column(name="Tunnus")
	public String kayttajatunnus;
	
	/**
	 * asiakkaan salasana
	 */
	@Column(name="Salasana")
	public String salasana;
	
	/**
	 * 
	 * @param kayttajatunnus asiakkaan käyttäjätunnus
	 * @param salasana asiakkaan salasana
	 */
	public Kirjautuminen(String kayttajatunnus, String salasana) {
		super();
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
	}

	public Kirjautuminen() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * getteri kayttajatunnukselle
	 * @return käyttäjätunnus
	 */
	public String getKayttajatunnus() {
		return kayttajatunnus;
	}
	/**
	 * asettaa käyttäjätunnuksen
	 * @param kayttajatunnus
	 */
	public void setKayttajatunnus(String kayttajatunnus) {
		this.kayttajatunnus = kayttajatunnus;
	}
	/**
	 * getteri salasanalle
	 * @return salasana
	 */
	public String getSalasana() {
		return salasana;
	}
	/**
	 * asettaa salasanan
	 * @param salasana
	 */
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	/**
	 * palauttaa String- muotoisen esityksen kirjautumisesta
	 */
	@Override
	public String toString() {
		return "kayttajatunnus=" + kayttajatunnus + ", salasana=" + salasana;
	}
	
	

}
