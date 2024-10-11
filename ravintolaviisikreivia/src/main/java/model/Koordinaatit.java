package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Luokassa luodaan koordinaatit
 * @author R27
 * @since 2021/12/17
 */
@Entity
@Table(name="koordinaatit")
public class Koordinaatit {
	
	/**
	 * koordinaattien ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
	@Column(name = "koordinaatitID", nullable=false)
	private int koordinaatitID;
	
	/**
	 * y- akselin suuntainen sijainti
	 */
	@Column(name = "Paikkapysty")
	private double paikka_y;
	
	/**
	 * x- akselin suuntainen sijainti
	 */
	@Column(name = "Paikkavaaka")
	private double paikka_x;
	

	/**
	 * poydan iD
	 */

	@Column(name = "Kulma")
	private int kulma;
	
	@Column(name = "Koko")
	private double koko;

	@ManyToOne
	@JoinColumn(name="PoytaID")
	public Poyta poyta;
	
	public Koordinaatit() {
		
	}
	
	
	/**
	 * 
	 * @param koordinaatitID koordinaattien ID
	 * @param paikka_y y- akselin suuntainen sijainti
	 * @param paikka_x x- akselin suuntainen sijainti
	 * @param poyta poyta
	 */
	
	public Koordinaatit(int koordinaatitID, double paikka_y, double paikka_x, int kulma, double koko, Poyta poyta) {
		
		this.koordinaatitID = koordinaatitID;
		this.paikka_y = paikka_y;
		this.paikka_x = paikka_x;
		this.poyta = poyta;
		this.kulma = kulma;
		this.koko = koko;
		
	}
	
	/**
	 * getteri koordinaattien ID:lle
	 * @return koordinaatitID
	 */
	public int getKoordinaattiTunnus() {
		return koordinaatitID;
	}
	
	/**
	 * asettaa koordnaattien ID:n
	 * @param tunnus tunnus
	 */
	public void setKoordinaattiTunnus(int tunnus) {
		this.koordinaatitID = tunnus;
	}
	
	/**
	 * asettaa y- arvon
	 * @param y
	 */
	public void setKoordinaattiY(double y) {
		this.paikka_y = y;
	}

	/**
	 * asettaa x- arvon
	 * @param x
	 */
	public void setKoordinaattiX(double x) {
		this.paikka_x = x;
	}
	
	/**
	 * getteri koordinaatille y
	 * @return paikka-y
	 */
	public double getKoordinaattiY() {
		return paikka_y;
	}
	
	/**
	 * getteri koordinaatille x
	 * @return paikka-x
	 */
	public double getKoordinaattiX() {
		return paikka_x;
	}
	
	/**
	 * asettaa poydan
	 * @param poyta
	 */
	public void setPoyta(Poyta poyta) {
		this.poyta = poyta;
	}
	
	/**
	 * getteri poydalle
	 * @return poyta
	 */
	public Poyta getPoyta() {
		return poyta;
		
	}
	
	/**
	 * asettaa kulman
	 * @param kulma kulma
	 */
	public void setKulma(int kulma) {
		
		this.kulma = kulma;
		
	}

	/**
	 * getteri kulmalle
	 * @return kulma
	 */
	public int getKulma() {
		
		return kulma;
		
	}
	
	public void setKoko(double koko) {
		
		this.koko = koko;
		
	}
	
	public double getKoko() {
		
		return koko;
	
	}
	
}
