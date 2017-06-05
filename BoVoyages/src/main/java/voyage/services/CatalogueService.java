package voyage.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.SortOrder;

import voyage.dao.CatalogueDAO;
import voyage.entities.DatesVoyages;
import voyage.entities.Destination;
import voyage.exceptions.DAOException;

/**
 * Classe de service qui permet d'interroger la couche de persistence depuis la
 * couche de présentation.
 * 
 * Chaque demande d'accès aux informations stockées en base de donnée doivent
 * impérativement passer par cette couche de service. Elle gère de façon
 * transparente l'interrogation de la base de données, et intercepte les
 * exceptions de la couche métier.
 * 
 * @author Adminl
 *
 */
@ManagedBean(name = "service")
@SessionScoped
public class CatalogueService implements ICatalogueService, Serializable {

	@Inject
	private CatalogueDAO catalogueDAO;

	@Override
	public List<Destination> getAllDestinations() {
		List<Destination> destinations = catalogueDAO.getAllDestinations();
		return destinations;
	}

	@Override
	public List<Destination> getDestinationsByPays(String pays) {
		List<Destination> destinations = catalogueDAO.getDestinationByPays(pays);
		return destinations;
	}

	/**
	 * Ajoute une destination
	 */
	@Override
	public void addDestination(Destination d) {
		try {
			catalogueDAO.saveOrUpdate(d);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Modifie une destination
	 */
	@Override
	public void updateDestination(Destination d) {
		try {
			catalogueDAO.update(d);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime une destination
	 */
	@Override
	public void deleteDestination(Destination d) {
		try {
			catalogueDAO.delete(d);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Affiche la destination en fonction de son identifiant
	 */
	@Override
	public Destination getDestinationById(int id) {
		Destination d = catalogueDAO.getDestinationById(id);
		return d;
	}

	/**
	 * Affiche tous les pays
	 */
	@Override
	public List<String> getAllUniquePays() {
		List<String> liste = catalogueDAO.getAllUniquePays();
		return liste;
	}
	
	/**
	 * Affiche toutes les régions
	 */
	@Override
	public List<String> getAllUniqueRegions() {
		List<String> liste = catalogueDAO.getAllUniqueRegions();
		return liste;
	}

	/**
	 * Surcharge de la methode addDestination
	 */
	@Override
	public void saveOrUpdate(Destination d) {
		addDestination(d);
	}

	@Override
	public void saveOrUpdate(DatesVoyages date) {
		try {
			catalogueDAO.saveOrUpdate(date);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDestination(int id) {
		try {
			catalogueDAO.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DatesVoyages date) {
		try {
			catalogueDAO.delete(date);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DatesVoyages> getDates(int destinationId) {
		List<DatesVoyages> dates = null;
		dates = catalogueDAO.getDates(destinationId);
		return dates;
	}

	/**
	 * Affiche une liste de dates en fonction de la destination
	 * 
	 * @param id
	 *            L'identifiant de la destination
	 * @return Une liste des destinations
	 */
	@Override
	public List<DatesVoyages> getAllDates(int id) {
		Destination d = getDestinationById(id);
		return d.getDates();
	}

	/**
	 * Récupère la liste des destination par batch. La requête est filtrée par
	 * pays, et triée par champ.
	 * 
	 * @param first
	 *            L'indice du premier résultat
	 * @param end
	 *            L'indice du dernier résultat
	 * @param sortField
	 *            Le champ qui servira au tri
	 * @param sortOrder
	 *            L'ordre de tri (ascendant, descendant ou nul)
	 * @param pays
	 *            Le Pays recherché
	 */
	@Override
	public List<Destination> getDestinations(int first, int end, String sortField, SortOrder sortOrder, String pays) {
		if (sortOrder == SortOrder.ASCENDING){
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.ASCENDING, pays);
		} else if (sortOrder == SortOrder.DESCENDING){
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.DESCENDING, pays);
		} else {
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.UNSORTED, pays);
		}
	}
	

	/**
	 * Récupère le nombre total de destinations.
	 * 
	 * @return Le nombre de résultats
	 */
	@Override
	public long getAllDestinationCount() {
		return catalogueDAO.getAllDestinationCount();
	}

	/**
	 * Récupère le nombre total de destinations pour un pays donné.
	 * 
	 * @param pays
	 *            Le pays recherché
	 * @return Le nombre de résultats
	 */
	@Override
	public long getDestinationByPaysCount(String pays) {
		return catalogueDAO.getDestinationByPaysCount(pays);
	}

	@Override
	public List<Destination> getDestinations(int first, int end, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		if (sortOrder == SortOrder.ASCENDING){
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.ASCENDING, filters);
		} else if (sortOrder == SortOrder.DESCENDING){
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.DESCENDING, filters);
		} else {
			return catalogueDAO.getDestinations(first, end, sortField, SortingOrder.UNSORTED, filters);
		}
	}

	@Override
	public long count(Map<String, String> filters) {
		return catalogueDAO.count(filters);
	}
}
