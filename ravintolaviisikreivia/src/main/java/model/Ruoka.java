package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.collections.ObservableList;


/**
 * 
 * Luokassa luodaan ruoka
 * @author R27
 * @since 2021/12/17
 */
@Entity
@Table(name="ruoka")
public class Ruoka {

	/**
	 * RuokaID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
	@Column(name="RuokaID")
	public int ruokaID;
	
	/**
	 * Ruoan nimi
	 */
	@Column(name="Nimi")
	public String nimi;
	
	@Column(name="Kuvaus")
	public String kuvaus;
	
	/**
	 * Ruoan kategoria
	 */
	@Column(name="Kategoria")
	public String kategoria;
	
	/**
	 * Ruoan hinta
	 */
	@Column(name="Hinta")
	public double hinta;
	

	
	public Ruoka() {
		
	}
	/**
	 * 
	 * @param ruokaID
	 * @param nimi ruoan nimi
	 * @param kuvaus ruoan kuvaus
	 * @param kategoria ruoan kategoria
	 * @param hinta ruoan hinta
	 */
	public Ruoka(int ruokaID, String nimi, String kuvaus, String kategoria, double hinta) {
		this.ruokaID = ruokaID;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.kategoria = kategoria;
		this.hinta = hinta;
	}
	
	
	/**
	 * getteri ruokaID:lle
	 * @return ruokaID
	 */
	public int getRuokaID() {
		return ruokaID;
	}

	/**
	 * asettaa ruokaID:n
	 * @param ruokaID
	 */
	public void setRuokaID(int ruokaID) {
		this.ruokaID = ruokaID;
	}

	/**
	 * getteri ruoan nimelle
	 * @return nimi
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * asettaa ruoan nimen
	 * @param nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	/**
	 * getteri ruoan kuvaukselle
	 * @return kuvaus
	 */
	public String getKuvaus() {
		return kuvaus;
	}

	/**
	 * asettaa ruoan kuvauksen
	 * @param kuvaus
	 */
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	/**
	 * getteri ruoan hinnalle
	 * @return hinta
	 */
	public double getHinta() {
		return hinta;
	}
	/**
	 * getteri kategorialle
	 * @return kategoria
	 */
	public String getKategoria() {
		return kategoria;
	}
	/**
	 * asettaa kategorian
	 * @param kategoria
	 */
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	/**
	 * asettaa ruoan hinnan
	 * @param hinta
	 */
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	
}
