package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Luokassa luodaan työntekijä
 * @author R27
 * @since 2021/12/17
 *
 */
@Entity
@Table(name="tyontekija")
public class Tyontekija extends Kayttaja {
	
	/**
	 * työntekijän ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
	@Column(name="TyontekijaID")
	public int tyontekijaID;
	
	/**
	 * työntekijän rooli
	 */
	@Column(name="Rooli")
	public String rooli;
	
	/**
	 * työntekijän salasana
	 */
	@Column(name="Salasana")
	public String salasana;
	
	/**
	 * työntekijän nimimerkki
	 */
	@Column(name="Tyontekija_nimimerkki")
	public String tyontekijaNimimerkki;
	

	/**
	 * 
	 * @param tyontekijaID työntekijän ID
	 * @param rooli työntekijän rooli
	 * @param salasana työntekijän salasana
	 * @param tyontekijaNimimerkki tyntekijän nimimerkki
	 */
	public Tyontekija(int tyontekijaID, String rooli, String salasana, String tyontekijaNimimerkki) {
		this.tyontekijaID = tyontekijaID;
		this.tyontekijaNimimerkki = tyontekijaNimimerkki;
		this.salasana = salasana;
		this.rooli = rooli;
	}
	/**
	 * getteri työntekijän ID:lle
	 * @return tyontekijaID
	 */
	public int getTyontekijaID() {
		return tyontekijaID;
	}
	/**
	 * asettaa työntekijän ID:n
	 * @param tyontekijaID
	 */
	public void setTyontekijaID(int tyontekijaID) {
		this.tyontekijaID = tyontekijaID;
	}
	
	/**
	 * getteri työntekijän nimimerkille
	 * @return tyontekijanNimimerkki
	 */
	public String getTyontekijaNimiMerkki() {
		return tyontekijaNimimerkki;
	}
	/**
	 * asettaa työntekijän nimimerkin
	 * @param tyontekijaNimimerkki
	 */
	public void setTyontekijaNimiMerkki(String tyontekijaNimimerkki) {
		this.tyontekijaNimimerkki = tyontekijaNimimerkki;
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
	 * getteri työtekijän roolille
	 * @return
	 */
	public String getRooli() {
		return rooli;
	}
	/**
	 * asettaa työntekijän roolin
	 * @param rooli
	 */
	public void setRooli(String rooli) {
		this.rooli = rooli;
	}
	
	public Tyontekija() {
		
	}

	/**
	 * Tuodaan tietokannasta kaikki palautteet työntekijääle
	 */
	public List<Palaute> getPalautteet() {
		
		List<Palaute> lista = tietokanta.haePalautteetTyontekija();
	
		return lista;
		
	}
	
	/**
	 * Luodaan vastaus asiakkaan palautteeseen
	 * @param viesti
	 * @param asiakasPalaute
	 */
	public void luoVastaus(String viesti, Palaute asiakasPalaute) {
		
		Asiakas asiakas = asiakasPalaute.getAsiakas();
		
		// Luodaan palaute olio
		Palaute tyontekijaPalaute = new Palaute(0,null,viesti,asiakas, asiakasPalaute, this);
	
		/**
		 * Viedään palaute- tiedot DAO- luokkaan
		 */
		tietokanta.luoPalaute(tyontekijaPalaute);
		
	}

	/**
	 * luodaan tietokantaan poyta
	 */
	public void luoPoyta(byte[] kuva, String poydantyyppi, int maksimipaikat) {

		Poyta poyta = new Poyta(0, maksimipaikat, poydantyyppi, kuva);
		
		tietokanta.luoPoyta(poyta);
		
	}
	
	
}
