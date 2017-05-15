package voyage.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name="dates_voyages")
public class DatesVoyages implements Serializable {

	private static final long serialVersionUID = 8983837795367625557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_date_voyage")
	private int id;
	
	@Column(name="date_depart")
	private Date dateDepart;
	
	@Column(name="date_retour")
	private Date dateRetour;
	
	@Column(name="prixHT")
	private double prix;
	
	@Column(name="nb_voyageurs")
	private int nbVoyageurs;

	public DatesVoyages() {	}

	public DatesVoyages(Date dateDepart, Date dateRetour, double prix, int nbVoyageurs) {
		super();
		this.dateDepart = dateDepart;
		this.dateRetour = dateRetour;
		this.prix = prix;
		this.nbVoyageurs = nbVoyageurs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getNbVoyageurs() {
		return nbVoyageurs;
	}

	public void setNbVoyageurs(int nbVoyageurs) {
		this.nbVoyageurs = nbVoyageurs;
	}

	
}
