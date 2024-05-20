package model;

import javafx.scene.layout.AnchorPane;

/**
 * Koordinaattien rajapinta
 * @author R27
 * @since 2021/12/17
 */
public interface IKoordinaatit {
	
	/**
	 * 
	 * @param koordinaatit koordinaatit
	 * @param skaalaa 
	 */
	public void onClickListener(Koordinaatit koordinaatit);
	
	public void poisto(Koordinaatit koordinaatit, AnchorPane poyta);
	
	public void paivitaSkaalaus(double koko);
}
