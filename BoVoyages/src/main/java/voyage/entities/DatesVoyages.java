package voyage.entities;

import java.util.Date;

/**
 * <b>DatesVoyages est une classe représentant les dates de voyages d'une destination.</b>
 * <p>
 * Une date de voyage est caractérisée par :
 * <ul>
 * <li>Une date de départ</li>
 * <li>Une date d'arrivée</li>
 * <li>Un prix</li>
 * <li>Un nombre de voyageurs</li>
 * </ul>
 * <p>
 * 
 * @author Adminl
 * @version 2.0
 *
 */
public class DatesVoyages {
	private int id;
	private Date dateDepart;
	private Date dateRetour;
	private double prix;
	private int nbVoyageurs;

}
