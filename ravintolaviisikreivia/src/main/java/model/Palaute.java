package model;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Luokassa luodaan palaute
 * @author R27
 * @since 2021/12/17
 */
@Entity
@Table(name="palaute")
public class Palaute {
	/**
	 * palautteen ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
	@Column(name = "palauteID", nullable=false)
	public int palauteID;
	/**
	 * palautteen aihe
	 */
	@Column(name = "Aihe")
	public String aihe;
	/**
	 * palautteen sisältö
	 */
	@Column(name = "Viestipalaute")
	public String viesti;
	/**
	 * palautteen lähetysajankohta
	 */
    @Column(name = "Aika")
    public LocalDateTime dateTime;
    
    /**
     * palautteen lähettäjä
     */
    @ManyToOne
    @JoinColumn(name="AsiakasID")
    public Asiakas asiakas;
    /**
     * palautteen vastauksen ID
     */
    @ManyToOne
    @JoinColumn(name="Vastaus_PalauteID")
    public Palaute vastausid;
    
    /**
     * palautteen vastaaja
     */
    @ManyToOne
    @JoinColumn(name="TyontekijaID")
    public Tyontekija tyontekija;
    
    /**
     * Alustetaan palaute
     * @param tunnus palutteen yksilöivä tunnus
     * @param aihe palautteen aihe
     * @param viesti palautteen sisältö
     * @param asiakas palautteen tekijä
     * @param vastauspalaute työntekijän vastaus
     * @param tyontekija vastaaja
     */
	public Palaute(int tunnus, String aihe, String viesti, Asiakas asiakas, Palaute vastauspalaute, Tyontekija tyontekija) {
		
		this.aihe = aihe;
		this.viesti = viesti;
		this.dateTime = LocalDateTime.now();  
		this.asiakas = asiakas;
		this.vastausid = vastauspalaute;
		this.tyontekija = tyontekija;

	}

	public Palaute() {
	
	}
	/**
	 * asettaa palauteID:n
	 * @param palauteID
	 */
	public void setTunnus(int palauteID) {
		
		this.palauteID = palauteID;
		
	}
	/**
	 * getteri tunnukselle
	 * @return palauteID
	 */
	public int getTunnus() {
		return palauteID;
		
	}
	/**
	 * asettaa aiheen
	 * @param aihe
	 */
	public void setAihe(String aihe) {
		this.aihe = aihe;
	}
	/**
	 * getteri aiheelle
	 * @return aihe
	 */
	public String getAihe() {
		return aihe;
		
	}
	/**
	 * asettaa viestin
	 * @param viesti
	 */
	public void setViesti(String viesti) {
		
		this.viesti = viesti;
		
	}
	/**
	 * getteri viestille
	 * @return
	 */
	public String getViesti() {
		return viesti;
		
	}
	/**
	 * getteri nimelle
	 * @return asiakas.getEtunimi()
	 */
	public String getNimi() {
		return asiakas.getEtuNimi();
		
	}
	/**
	 * getteri nimimerkille
	 * @return asiakas.asiakasNimimerkki
	 */
	public String getNimiMerkki() {
		return asiakas.asiakasNimimerkki;
		
	}
	/**
	 * asettaa asiakkaan
	 * @param asiakas
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
	/**
	 * getteri asiakkaalle
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}
	/**
	 * asettaa työntekijän
	 * @param tyontekija
	 */
	public void setTyontekija(Tyontekija tyontekija) {
		this.tyontekija = tyontekija;
	}
	/**
	 * getteri työntekijälle
	 * @return tyontekija
	 */
	public Tyontekija getTyontekija() {
		return tyontekija;
		
	}
	/**
	 * asettaa vastauksen
	 * @param vastaus
	 */
	public void setVastaus(Palaute vastaus) {
		this.vastausid = vastaus;
	}
	/**
	 * getteri vastaukselle
	 * @return vastausID
	 */
	public Palaute getVastaus() {
		return vastausid;
		
	}
	/**
	 * asettaa ajan
	 * @param aika
	 */
	public void setAika(LocalDateTime aika) {
		this.dateTime = aika;
		
	}
	/**
	 * getteri ajalle
	 * @return dateTime
	 */
	public LocalDateTime getAika() {
		return dateTime;
		
	}

}
