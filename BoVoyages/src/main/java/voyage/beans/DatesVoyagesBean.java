package voyage.beans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import voyage.entities.DatesVoyages;
import voyage.entities.Destination;
import voyage.services.CatalogueService;

@Named("date")
@ConversationScoped
public class DatesVoyagesBean implements Serializable {
	private static final long serialVersionUID = 357912718931065193L;

	private int id;
	private Date dateDepart;
	private Date dateRetour;
	private double prix;
	private int nbVoyageurs;
	
	private Destination destination;
	
	@Inject
	private Conversation conversation;
	
	@Inject
	private CatalogueService service; 
	
	public DatesVoyagesBean() {	}
	
	public DatesVoyagesBean(Date dateDepart, Date dateRetour, double prix, int nbVoyageurs) {
		this.dateDepart = dateDepart;
		this.dateRetour = dateRetour;
		this.prix = prix;
		this.nbVoyageurs = nbVoyageurs;
	}
	
	public String addDate(int id){
		startConversation();
		this.destination = service.getDestinationById(id);
		return "creationDateVoyage?faces-redirect=true";
	}
	
	public String add(){
		DatesVoyages dv = new DatesVoyages(dateDepart, dateRetour, prix, nbVoyageurs);
		destination.addDate(dv);
		service.saveOrUpdate(destination);
		stopConversation();
		return "allDestinations?faces-redirect=true";
	}

	
	public void startConversation(){
		if (conversation.isTransient()) {
			conversation.begin();
		}	
	}
	
	public void stopConversation(){
		if (!conversation.isTransient()) {
			conversation.end();
		}
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
