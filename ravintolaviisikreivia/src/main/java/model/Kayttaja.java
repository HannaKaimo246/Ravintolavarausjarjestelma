package model;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import controller.KirjautuminenController;
import controller.PalauteController;
import controller.PalauteSivuController;
import controller.PoytalistaController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * Luokassa luodaan käyttäjä
 * @author R27
 * @since 2021/12/17
 *
 */
public class Kayttaja {
	
	/**
	 * Käyttäjän ID
	 */
	private int kayttajaID;
	
	/**
	 * Käyttäjän tunnus
	 */
	protected String tunnus;
	
	/**
	 * Käyttäjän salasana
	 */
	protected String salasana;
	
	/**
	 * Käyttäjä- luokan muuttuja kayttaja
	 */
	private static Kayttaja kayttaja;
	/**
	 * Luonnos varauksesta
	 */
	private Varaus varausLuonnos;
	
	/**
	 * Asiakas- luokan muuttuja asiakas
	 */
	protected Asiakas asiakas;
	
	
	/**
	 * Työntekijä- luokan muuttuja tyontekija
	 */
	protected Tyontekija tyontekija;
	

	/**
	 * Ruoka- luokan muuttuja ruoka
	 */
	protected Ruoka ruoka;
	
	/**
	 * palauteController
	 */
	protected PalauteController palauteController;

	/**
	 * kirjautumisController
	 */
	protected KirjautuminenController kirjautuminencontroller;
	
	/**
	 * palauteSivuController
	 */
	protected PalauteSivuController palauteSivuController;

	/**
	 * rajapinnan IRavintolavarausDAO tietokanta
	 */
	public static IRavintolavarausDAO tietokanta;

	/**
	 * Käyttäjä, jolla tunnus ja salasana
	 */
	protected Kayttaja(){
		tunnus = "";
		salasana = "";
		
	}
	
	/**
	 * Lista, pöytien koordinaateista
	 */
	private List<Koordinaatit> poytalista;
	/**
	 * PoytalistaController viittaus
	 */
	private PoytalistaController poytalistakontrolleri;
	/**
	 * Indeksi pöydille
	 */
	private int poytaListaIndeksi = 0;
	/**
	 * Painikkeet pöytäeditorille
	 */
	private List<Poyta> PoytaEditoriPainikkeet;
	
	/**
	 * Luodaan vain kerran käyttäjä- olio
	 * @return kayttaja
	 */
	
	public static Kayttaja getInstance(){
		if (kayttaja == null){

			kayttaja = new Kayttaja();
			tietokanta = new RavintolavarausDAO();

		}
		return kayttaja;
	}
	
	/**
	 * Viedään palautteen tiedot asiakas-luokkaan
	 * @param aihe
	 * @param viesti
	 */
	
	public void viePalauteAsiakas(String aihe, String viesti) {
		
			boolean luotu = asiakas.luoPalaute(kayttajaID, aihe, viesti);
			
			// Viedään palautekontrolleriin tieto, että onnistuiko palautteen luonti.
			if (luotu) {
				palauteController.onnistui();
			} else {
				palauteController.epaonnistui();
			}
	
	}
	
	/**
	 * Tuodaan joko asiakkaan tai työntekijän palautteet
	 * @return tulos
	 */

	public List<Palaute> getPalautteet() {
		
		List<Palaute> tulos = null;
	
		if (this.asiakas != null) {
			tulos = asiakas.getPalautteet();
		} else if (this.tyontekija != null) {
			tulos = tyontekija.getPalautteet();
		} else {
			System.out.println("Käyttäjää ei ole olemassa!");
		}
	
		return tulos;
		
	}
	
	/**
	 * Alustetaan palaute- kontrolleri
	 * @param palauteController
	 */
	public void alustaPalauteKontrolleri(PalauteController palauteController) {
		this.palauteController = palauteController;
	}
	
	/**
	 * Alustetaan palautesivu- kontrolleri
	 * @param palauteSivuController
	 */
	public void alustaPalauteSivuKontrolleri(PalauteSivuController palauteSivuController) {
		
		this.palauteSivuController = palauteSivuController;
		
	}
	
	/**
	 * Alustetaan kirjautumis- kontrolleri
	 * @param kirjautuminencontroller
	 */
	public void alustaKirjautuminenKontrolleri(KirjautuminenController kirjautuminencontroller) {
		this.kirjautuminencontroller = kirjautuminencontroller;
	}
	
	/**
	 * Viedään varauksen tiedot asiakas- luokkaan
	 * @param paiva varauksen päivämäärä
	 * @param alku varauksen aloitusajankohta
	 * @param loppu varauksen lopetusajankohta
	 * @param maara varauksen henkilömäärä
	 * @param tiedot varauksen lisätiedot
	 * @param valittuPoyta 
	 */
	public void teeVaraus(LocalDate paiva, LocalTime alku, LocalTime loppu, int maara, String tiedot, Poyta valittuPoyta, Koordinaatit koordinaatti) {

	
		asiakas.luoVaraus(paiva, alku, loppu, maara, tiedot, valittuPoyta, koordinaatti);
		
	}
	
	/**
	 * Muokataan varausta tietokannassa
	 * @param id varauksen ID
	 * @param paiva varauksen päivämäärä
	 * @param alku varauksen aloitusajankohta
	 * @param loppu varauksen päättymisajankohta
	 * @param maara varauksen henkilömäärä
	 * @param tiedot varauksen lisätiedot
	 * @param varausAsiakas varaajan tiedot
	 */
	public void editVaraus(int id, LocalDate paiva, LocalTime alku, LocalTime loppu, int maara, String tiedot, Asiakas varausAsiakas, Poyta poyta, Koordinaatit koordinaatti) {
		Varaus varaus = new Varaus(id , paiva, alku, loppu, maara, tiedot);	
		tietokanta.luoVaraus(varaus);
	}
	/**
	 * Poistetaan varaus tietokannasta
	 * @param varaus
	 */
	public void poistaVaraus(Varaus varaus) {
		tietokanta.deleteVaraus(varaus);
	}
	/**
	 * Viedään työntekijä tietokantaan
	 * @param rooli työntekijän rooli
	 * @param salasana työntekijän salasana
	 * @param nimimerkki työntekijän nimimerkki
	 */
	public boolean luoTyontekija(String rooli, String salasana, String nimimerkki) {
		
		tyontekija = new Tyontekija(0, rooli, hashPassword(salasana), nimimerkki);

		if(tietokanta.luoTyontekija(tyontekija)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Viedään ruoka tietokantaan
	 * @param nimi ruoan nimi
	 * @param kuvaus ruoan sisältö
	 * @param kategoria ruoan kategoria
	 * @param hinta ruoan hinta
	 */
	public boolean luoRuoka(String nimi, String kuvaus, String kategoria, double hinta) {
		
		ruoka = new Ruoka(0, nimi, kuvaus, kategoria, hinta);

		if(tietokanta.luoRuoka(ruoka)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Viedään asiakas tietokantaan
	 * @param etunimi
	 * @param sukunimi
	 * @param puhelinnumero
	 * @param sahkoposti
	 * @param salasana
	 * @param salasanaVahvistus
	 * @param nimimerkki
	 */
	public boolean luoAsiakas(String etunimi, String sukunimi, String puhelinnumero, String sahkoposti, String salasana, String salasanaVahvistus, String nimimerkki) {
		
		Asiakas asiakas = new Asiakas(0, etunimi, sukunimi, sahkoposti, puhelinnumero, hashPassword(salasana), nimimerkki);

		if(tietokanta.luoAsiakas(asiakas)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Kirjauduttuaan tarkistetaan, onko kirjautunut asiakas vai työntekijä
	 * @param nimimerkki
	 * @param salasana
	 * @return
	 */
	public boolean tarkistaKayttaja(String nimimerkki, String salasana) {
	
		Asiakas asiakas = null;
		Tyontekija tyontekija = null;
		
		asiakas = tietokanta.onkoAsiakas(nimimerkki, hashPassword(salasana));
	
			 
		if (asiakas != null) {
		
		System.out.println("Asiakas kirjautuminen onnistui!");
		this.asiakas = asiakas;
	
		try {
			kirjautuminencontroller.asiakasNakyma();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		} else {
			
			tyontekija = tietokanta.onkoTyontekija(nimimerkki, hashPassword(salasana));
			
			if (tyontekija != null) {
				
				System.out.println("Työntekijä kirjatuminen onnistui!");
				this.tyontekija = tyontekija;
				try {
					kirjautuminencontroller.työnTekijänakyma();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Kirjautuminen epäonnistui. Ei löydetty asiakasta tai työntekijää.");
				
			}
		}
		return false;
	}
	
	/**
	 * Palautetaan true, jos ei ole työntekijä
	 * @return false, jos on työntekijä
	 */
	public boolean onkoAsiakasSessio() {
		
		if (this.tyontekija == null) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Viedään palautteen vastaus työntekijä- luokkaan
	 * @param vastaus työntekijän vastaus
	 * @param palaute palautteen sisältö
	 */
	public void vieVastaus(String vastaus, Palaute palaute) {
		
		this.tyontekija.luoVastaus(vastaus, palaute);
		
	}
	
	/**
	 * Palauttaa työntekijän
	 * @return tyontekija
	 */
	public Tyontekija tuoTyontekija() {
		return tyontekija;
	}
	
	/**
	 * Palauttaa asiakkaan
	 * @return asiakas
	 */
	public Asiakas tuoAsiakas() {
		return asiakas;
		
	}
	
	/**
	 * Asettaa työntekijän
	 * @param t
	 */
	public void setTyontekija(Tyontekija t) {
		this.tyontekija = t;
	}
	
	/**
	 * 
	 * Asettaa asiakkaan
	 * @param a
	 */
	public void setAsiakas(Asiakas a) {
		this.asiakas = a;
	}

	/**
	 * Hashataan salasana
	 * @param password
	 * @return generatedPassword
	 */

	
	// tyontekijan luoma poyta tallennetaan tietokantaan
	public void luoPoyta(byte[] kuva, String tyyppi, int maksimipaikat) {
		
		if (tyontekija != null) {
			this.tyontekija.luoPoyta(kuva, tyyppi, maksimipaikat);
		}
		
		
	}
	
	// Viedaan poytalistan tiedot kantaan
	public boolean julkaise() {
		
		if (tyontekija != null) {
		
			List<Varaus> julkaise = tietokanta.julkaise(poytalista);
			
			if (julkaise.size() > 0) {
				
				if (poytalistakontrolleri.ilmoitus()) {
				
				tietokanta.poistaVarattuVaraus(julkaise);
				
				}
				
			}
	
			poytalistakontrolleri.nollaaEditori();
			lisaaKaikkiPoytaListaan();
			naytaPoytalista();
			
			return true;
			
		}
		return false;
		
		
	}
	
	// Haetaan poydat tietokannasta
	public List<Poyta> getPoytaTyypit() {
		
		List<Poyta> lista = null;
	
		lista = tietokanta.getPoytaTyypit();
	
		return lista;
		
	}
	
	// Haetaan poytalista tietokannasta
	public List<Koordinaatit> getPoytaLista() {
		
		List<Koordinaatit> lista = null;
		
		lista = tietokanta.getPoytaLista();
		
		return lista;

	}	

	/**
	 * hashataan ja suolataan salasana
	 * @param password password
	 * @return generated password
	 */
	public String hashPassword(String password) {
		String salt = "fT)68n3f9H-mYQtdm18*RTgd";
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            
            // Add password bytes to digest
            md.update(salt.getBytes());
            
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;

	}

	 
	
	/**
	 * Poyta listan ja editorin toiminaallisuus
	 */
	public void alustaPoytalista() {
		
		poytalista = null;
		poytalista = new ArrayList<>();
		
	}
	
	public void alustaPoytaEditoriPainikkeet() {
		
		PoytaEditoriPainikkeet = null;
		PoytaEditoriPainikkeet = new ArrayList<>();
		
	}
	
	public void alustaPoytalistaKontrolleri(PoytalistaController poytalistakontrolleri)  {
		
		this.poytalistakontrolleri = poytalistakontrolleri;
		poytaListaIndeksi = 0;
		
	}
	
	public List<Koordinaatit> tuoLista() {
		return poytalista;
		
	}
	

	public void lisaaKaikkiPoytaListaan() {
		
		List<Koordinaatit> poydat = kayttaja.getPoytaLista();
		
		poytalista.addAll(poydat);
	}
	
    public void naytaPoytalista() {
    	
            for (int i = 0; i < poytalista.size(); i++) {
          
            	poytalistakontrolleri.naytaPoyta();
                
            }
    	
    }
	
    public Koordinaatit getPoytalistaIndeksi() {
   
		return poytalista.get(poytaListaIndeksi++);
    	
    }
    
    public void nollaaPoytalista() {
    	
    	poytaListaIndeksi = 0;
    	poytalista.clear();
    	
    }
    
    public void lisaaYksiPoytaListaan(Koordinaatit koordinaatti) {
    	
    	poytalista.add(koordinaatti);
    	
    }
    
    public void poistaYksiPoytaListaan(Koordinaatit koordinaatti) {
   
    	koordinaatti.setKoko(-1);
    	
    }
    
    public void nollaaPainikkeet() {
    	
    	PoytaEditoriPainikkeet.clear();
    	
    }
    
    public List<Poyta> tuoPainikkeet() {
		return PoytaEditoriPainikkeet;
    	
    }
    
    public void lisaaKaikkiPainikkeetPoytaEditoriin() {
    	
    	List<Poyta> napit = getPoytaTyypit();
    	
    	PoytaEditoriPainikkeet.addAll(napit);
    	
    }
    
    public int PoytaEditoriPainikkeetListaKoko() {
		return PoytaEditoriPainikkeet.size();
    	
    }
    
    public int poytaListaKoko() {
		return poytalista.size();
    	
    }
    
    public Poyta valitsePoyta(int arvo) {
		
    	return PoytaEditoriPainikkeet.get(arvo);
    	
    }
    
    
    
    public void tallennaVarausLuonnos(LocalDate paiva, LocalTime alku, LocalTime loppu, int maara, String tiedot) {
    	
    	varausLuonnos = new Varaus();
    	
    	varausLuonnos.setPaiva(paiva);
    	
    	varausLuonnos.setAlkamisAjankohta(alku);
    	
    	varausLuonnos.setPaattymisAjankohta(loppu);
    	
    	varausLuonnos.setHenkiloMaara(maara);
    	
    	varausLuonnos.setLisaTiedot(tiedot);
    	
    }
    
    public Varaus getVarausLuonnos() {
		return varausLuonnos;
    	
    }
    
    
}
