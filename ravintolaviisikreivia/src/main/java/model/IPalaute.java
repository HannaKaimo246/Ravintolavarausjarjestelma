package model;

import model.Palaute;

/**
 * palautteiden rajapinta
 * @author R27
 * @since 2021/12/17
 */
public interface IPalaute {
	
	/**
	 * palauteVastaus- controllerille lähetetään pyyntö, että palaute- nappulaa on painettu palautesivu-controllerissa
	 * @param palaute palaute
	 */
    public void onClickListener(Palaute palaute);
}
