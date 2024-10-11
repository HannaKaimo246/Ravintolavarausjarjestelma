package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Luokassa luodaan pöytä
 * @author R27
 * @since 2021/12/17
 */
@Entity
@Table(name="poyta")
public class Poyta {
	
	/**
	 * poydan ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
	@Column(name = "poytaID", nullable=false)
	private int poytaID;
	
	/**
	 * poydan maximipaikkamaara
	 */
	@Column(name = "Maksimipaikat")
	private int maksimipaikat;
	
	/**
	 * poydan nimi
	 */
	@Column(name = "Poydantyyppi")
	private String poydantyyppi;
	
	
	@Column(name = "Kuva", columnDefinition = "LONGBLOB")
	private byte[] kuva;
	
	@Transient
	private boolean valinta = false;
	
	public Poyta() {
		
	}

	/**
	 * 
	 * @param poytaID poydan yksiloiva tunnus
	 * @param maksimipaikat poydan maksimipaikkamaara
	 * @param nimi poydan nimi
	 * @param tyyppi poydan tyyppi
	 * @param kuva kuva poydasta
	 */
	public Poyta(int poytaID, int maksimipaikat, String poydantyyppi, byte[] kuva) {
		
		this.poytaID = poytaID;
		this.maksimipaikat = maksimipaikat;
		this.poydantyyppi = poydantyyppi;
		this.kuva = kuva;
		
	}

	/**
	 * getteri poytaID:lle
	 * @return poytaID
	 */
	public int getPoytaID() {
		return poytaID;
	}

	/**
	 * asettaa poytaID:n
	 * @param poytaID
	 */
	public void setPoytaID(int poytaID) {
		this.poytaID = poytaID;
	}
	
	/**
	 * getteri maximipaikkamaaralle
	 * @return maksimipaikat
	 */
	public int getMaksimipaikat() {
		return maksimipaikat;
	}
	
	/**
	 * asettaa maksimipaikat
	 * @param maksimipaikat
	 */
	public void setMaksimipaikat(int maksimipaikat) {
		this.maksimipaikat = maksimipaikat;
	}
	
	public void setPoydanTyyppi(String poydanTyyppi) {
		this.poydantyyppi = poydanTyyppi;
	}
	
	public String getPoydanTyyppi() {
		return poydantyyppi;
		
	}
	
	/**
	 * asettaa kuvan
	 * @param kuva
	 */
	public void setkuva(byte[] kuva) {
		this.kuva = kuva;
	}
	
	/**
	 * getteri kuvalle
	 * @return kuva
	 */
	public byte[] getKuva() {
		return kuva;
		
	}

	/**
	 * palauttaa String- muotoisen esityksen poydasta
	 */
	@Override
	public String toString() {
		return "Poyta [poytaID=" + poytaID + ", MAX=" + maksimipaikat + "]";
	}
	
	public void setValinta(boolean valinta) {
		this.valinta = valinta;
	}
	
	public boolean valinta() {
		return valinta;
	}
	
}
