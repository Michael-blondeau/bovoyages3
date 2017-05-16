package voyage.services;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import voyage.dao.CatalogueDAO;
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

	private static final long serialVersionUID = -465153231869986884L;

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

	@Override
	public void addDestination(Destination d) {
		try {
			catalogueDAO.saveOrUpdate(d);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateDestination(Destination d) {
		try {
			catalogueDAO.update(d);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDestination(Destination d) {
		try {
			catalogueDAO.delete(d);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Destination getDestinationById(int id) {
		Destination d = catalogueDAO.getDestinationById(id);
		return d;
	}

	@Override
	public List<String> getAllUniquePays() {
		List<String> liste = catalogueDAO.getAllUniquePays();
		return liste;
	}

	@Override
	public void saveOrUpdate(Destination d) {
		addDestination(d);
  }	

	public void deleteDestination(int id) {
		try {
			catalogueDAO.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
