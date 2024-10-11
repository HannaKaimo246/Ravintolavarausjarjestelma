package model;

import java.util.List;

/**
 * RavintolaVarusDAO:n rajapinta
 * @author R27
 * @since 2021/12/17
 */
public interface IRavintolavarausDAO {
 
	/**
	 * luodaan palaute
	 * @param palaute palaute
	 * @return true, jos palutteen luonti onnistui, palauttaa false, jos epäonnistui
	 */
	public abstract boolean luoPalaute(Palaute palaute);

	/**
	 * luodaan asiakas
	 * @param asiakas asiakas
	 * @return true, jos asiakkaan luonti onnistui, ja false, jos se epäonnistui
	 */
	public abstract boolean luoAsiakas(Asiakas asiakas);

	/**
	 * haetaan kaikki palautteet työntekijälle
	 * @return lista palautteista
	 */
	public abstract List<Palaute> haePalautteetTyontekija();

	/**
	 * luodaan varaus
	 * @param varaus varaus
	 * @return true, jos varauksen luonti onnistui ja false, jos se epäonnistui
	 */
	public abstract boolean luoVaraus(Varaus varaus);
	
	/**
	 * poistetaan varaus
	 * @param varaus varaus
	 * @return true, jos varauksen poisto onnistui ja false, jos se epäonnistui
	 */
	public abstract boolean deleteVaraus(Varaus varaus);

	/**
	 * haetaan kaikki työntekijät
	 * @param tyontekija tyontekija
	 * @return lista työntekijät
	 */
	public abstract List<Tyontekija> haeTyontekijat(Tyontekija tyontekija);

	/**
	 * luodaan tyontekija
	 * @param tyontekija tyontekija
	 * @return true, jos työntekijän luonti onnistui ja false, jos se epäonnistui
	 */
	public abstract boolean luoTyontekija(Tyontekija tyontekija);

	/**
	 * tarkistetaan, onko asiakas olio olemassa nimimerkin ja salasanan avulla
	 * @param nimimerkki nimimerkki
	 * @param salasana salasana
	 * @return lista asiakkaista
	 */
	public abstract Asiakas onkoAsiakas(String nimimerkki, String salasana);
	
	/**
	 * tarkistetaan, onko tyontekijaolio olemassa nimimerkin ja salasanan avulla
	 * @param nimimerkki nimimerkki
	 * @param salasana salasana
	 * @return lista työntekijöistä
	 */
	public abstract Tyontekija onkoTyontekija(String nimimerkki, String salasana);

	/**
	 * haetaan asiakkaiden palautteet
	 * @param asiakasID asiakasID
	 * @return lista palautteista
	 */
	public abstract List<Palaute> haePalautteetAsiakas(int asiakasID);

	/**
	 * luetaan varauksia
	 * @param varaus varaus
	 * @return lista varauksista
	 */
	
	 
	public List<Varaus> readAsiakkaanVaraus(Asiakas asiakas, Varaus varaus);
	
	/**
	 * 
	 * haetaan asiakas ID:n perusteella
	 * @param asiakasID asiakasID
	 * @return asiakas
	 */
	public abstract Asiakas haeAsiakas(int asiakasID);

	/**
	 * luodaan poyta
	 * @param poyta poyta
	 * @return true, jos pöydän luonti onnistui ja false, jos se epäonnistui
	 */
	public abstract boolean luoPoyta(Poyta poyta);

	/**
	 * luetaan pöutyiä
	 * @return lista pöydistä
	 */
	public abstract List<Poyta> getPoytaTyypit();

	/**
	 * haetaan lista koordinaateista
	 * @return pöytälista
	 */
	public abstract List<Koordinaatit> getPoytaLista();
	/**
	 * luodaan pöytälista
	 * @param poytalista poytalista
	 * @return luonti
	 */
	public abstract List<Varaus> julkaise(List<Koordinaatit> poytalista);
	
	/**
	 * haetaan ruoat
	 * @param ruoka ruoka
	 * @param kategoria kategoria
	 * @return lista ruoista
	 */
	public abstract List<Ruoka> readRuokaKategoria(Ruoka ruoka, String kategoria);
	
	/**
	 * luodaan ruoka
	 * @param ruoka ruoka
	 * @return true, jos ruoka luonti onnistui ja false, jos se epäonnistui
	 */
	public abstract boolean luoRuoka(Ruoka ruoka);
	
	
	/**
	 * poistetaan ateria
	 * @param ruoka ruoka
	 * return poistettu ateria
	 */
	public abstract boolean deleteAteria(Ruoka ruoka);
	
	
	/**
	 * Muokataan ateria
	 * @param ruoka ruoka
	 * return muokkattu ateria
	 */
	public abstract boolean updateMeal(Ruoka ruoka);

	/**
	 * Muokkataan työntekijän tiedot
	 */
	public abstract boolean updateEmployee(Tyontekija tyontekija);
	
	/**
	 * poistetaan työntekijän tiedot
	 */
	public abstract boolean deleteEmployee(Tyontekija tyontekija);

	/**
	 * Luetaan varauksia
	 */
	public abstract List<Varaus> readVaraukset(Varaus varaus);

	/**
	 * poistetaan varattu varaus
	 */
	public abstract boolean poistaVarattuVaraus(List<Varaus> poytalista);


	
}
