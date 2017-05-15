package voyage.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import voyage.dao.CatalogueDAO;
import voyage.entities.Destination;

@ManagedBean(name="service")
@SessionScoped
public class CatalogueService implements ICatalogueService, Serializable {

	private static final long serialVersionUID = -465153231869986884L;

	@Inject
	private CatalogueDAO catalogueDAO;

	@Override
	public List<Destination> getAllDestinations() {
		List<Destination> destinations = null;
		try {
			destinations = catalogueDAO.getAllDestinations();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinations;
	}
	
	@Override
	public List<Destination> getDestinationsByPays(String pays) {
		List<Destination> destinations = null;
		try {
			destinations = catalogueDAO.getDestinationByPays(pays);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinations;
	}
	
	@Override
	public void addDestination(Destination d) {
		try {
			catalogueDAO.saveOrUpdate(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateDestination(Destination d){
		try {
			catalogueDAO.update(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDestination(Destination d){
		try {
			catalogueDAO.delete(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Destination getDestinationById(int id) {
		Destination d = null;
		try {
			d = catalogueDAO.getDestinationById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public List<String> getAllUniquePays() {
		List<String> liste = null;
		try {
			liste = catalogueDAO.getAllUniquePays();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}


