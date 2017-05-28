package voyage.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

	// Date du jour, utilisée pour fixer la date minimum du
	// calendrier
	private Date minDate = new Date();

	private Destination destination;

	@Inject
	private Conversation conversation;

	@Inject
	private CatalogueService service;

	public DatesVoyagesBean() {
	}

	public DatesVoyagesBean(Date dateDepart, Date dateRetour, double prix, int nbVoyageurs) {
		this.dateDepart = dateDepart;
		this.dateRetour = dateRetour;
		this.prix = prix;
		this.nbVoyageurs = nbVoyageurs;
	}

	/**
	 * Initie le bean avec les informations sur la destination en cours.
	 * 
	 * La variable privée {@link #destination} est initialisée avec le contenu
	 * de la destination correspondante en base de données.
	 * 
	 * @param id
	 *            L'identifiant de la destination sélectionnée
	 * @return Redirige vers la page affichant la liste des dates de voyage pour
	 *         cette destination.
	 */
	public String viewDates(int id) {
		startConversation();
		destination = service.getDestinationById(id);
		return "viewDates?faces-redirect=true";
	}

	/**
	 * Ajoute une nouvelle date de voyage à une destination.
	 * 
	 * @param id
	 *            L'identifiant de la destination pour laquelle une nouvelle
	 *            date de voyage doit être créée.
	 * @return
	 */
	public String addDate(int id) {
		startConversation();
		this.destination = service.getDestinationById(id);
		return "creationDateVoyage?faces-redirect=true";
	}

	/**
	 * Ajout ou modification d'une date de voyage (validation du formulaire)
	 * 
	 * @return Redirige vers la liste des destinations lors de la création d'une
	 *         nouvelle date de voyage. En cas de modification, la redirection
	 *         s'effectue vers la liste des dates de voyage.
	 */
	public String add() {
		DatesVoyages dv = new DatesVoyages(dateDepart, dateRetour, prix, nbVoyageurs);
		if (id != 0) {
			dv.setId(id);
			service.saveOrUpdate(dv);
			// stopConversation();
		} else {
			destination.addDate(dv);
			service.saveOrUpdate(destination);
		}
		return "viewDates?faces-redirect=true";
	}

	/**
	 * Méthode pour modifier une date de voyage.
	 * 
	 * @param idDate
	 *            L'identifiant de la date à modifier
	 * @param idDestination
	 *            L'identification de la date à modifier
	 * @return Redirige vers le formulaire de création/modification d'une date
	 *         de voyage
	 */
	public String modifier(int idDate, int idDestination) {
		startConversation();
		// Pas nécessaire ?
		if (destination == null) {
			destination = service.getDestinationById(idDestination);
		}
		// List<DatesVoyages> dates = destination.getDates();
		List<DatesVoyages> dates = service.getDates(idDestination);
		System.err.println(">>> idDestination = " + idDestination);
		System.err.println(">>> dates = " + dates);
		if (dates != null) {
			for (DatesVoyages date : dates) {
				if (date.getId() == idDate) {
					this.id = idDate;
					this.dateDepart = date.getDateDepart();
					this.dateRetour = date.getDateRetour();
					this.prix = date.getPrix();
					this.nbVoyageurs = date.getNbVoyageurs();
				}
			}
			return "creationDateVoyage?faces-redirect=true";
		}
		return null;
	}

	/**
	 * Surcharge de la méthode.
	 * 
	 * Permet de ne fournir que l'identifiant de la date, à supposer que
	 * l'identifiant de la destination soit déjà présent dans le bean (e.g., la
	 * méthode {@link #viewDates(int)} a déjà été appelée.
	 * 
	 * @param idDate
	 *            L'identifiant de la date à modifier
	 * @return Redirige vers le formulaire de création/modification d'une date
	 *         de voyage
	 */
	public String modifier(int idDate) {
		return modifier(idDate, this.destination.getId());
	}

	/**
	 * Méthode pour supprimer une date de voyage.
	 * 
	 * @param idDate
	 *            L'identifiant de la date à supprimer
	 * @param idDestination
	 *            L'identification de la date à supprimer
	 * @return Redirige vers la liste des dates de la destination
	 */
	public String supprimer(int idDate, int idDestination) {
		// Pas nécessaire ?
		if (destination == null) {
			destination = service.getDestinationById(idDestination);
		}
		// List<DatesVoyages> dates = destination.getDates();
		List<DatesVoyages> dates = service.getDates(idDestination);
		DatesVoyages date = new DatesVoyages();
		if (dates != null) {
			Iterator<DatesVoyages> it = dates.iterator();
			while (it.hasNext()) {
				DatesVoyages dv = it.next();
				if (dv.getId() == idDate) {
					date = dv;
				}
			}
		}
		// destination.setDates(dates);
		// service.saveOrUpdate(destination);
		service.delete(date);
		// stopConversation();
		return "viewDates?faces-redirect=true";
	}

	/**
	 * Surcharge de la méthode.
	 * 
	 * Permet de ne fournir que l'identifiant de la date, à supposer que
	 * l'identifiant de la destination soit déjà présent dans le bean (e.g., la
	 * méthode {@link #viewDates(int)} a déjà été appelée.
	 * 
	 * @param idDate
	 *            L'identifiant de la date à supprimer
	 * @return Redirige vers le formulaire de création/modification d'une date
	 *         de voyage
	 */
	public String supprimer(int idDate) {
		return supprimer(idDate, this.destination.getId());
	}

	/**
	 * Commence une conversation de bean.
	 */
	public void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	/**
	 * Arrête une conversation de bean.
	 */
	public void stopConversation() {
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

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
}
