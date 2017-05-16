package voyage.beans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named("date")
@ConversationScoped
public class DatesVoyagesBean implements Serializable {
	private static final long serialVersionUID = 357912718931065193L;

	private int id;
	private Date dateDepart;
	private Date dateRetour;
	private double prix;
	private int nbVoyageurs;
	
	public DatesVoyagesBean() {	}
	
	public DatesVoyagesBean(Date dateDepart, Date dateRetour, double prix, int nbVoyageurs) {
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
