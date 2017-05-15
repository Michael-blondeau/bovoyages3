package voyage.services;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import voyage.dao.CatalogueDAO;
import voyage.dao.DAO;
import voyage.entities.Destination;


public class CatalogueService implements ICatalogueService {
	private DAO dao;
	private CatalogueDAO catalogueDAO;
	private DataSource ds;

	public CatalogueService(DataSource ds) {
		this.ds = ds;
		dao = new DAO(ds);
		catalogueDAO = new CatalogueDAO(dao);
	}

	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#getAllDestinations()
	 */
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
	
	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#getDestinationsByPays(java.lang.String)
	 */
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
	
	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#addDestination(voyage.entities.Destination)
	 */
	@Override
	public void addDestination(Destination d) {
		try {
			catalogueDAO.saveOrUpdate(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#updateDestination(voyage.entities.Destination)
	 */
	@Override
	public void updateDestination(Destination d){
		try {
			catalogueDAO.update(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#deleteDestination(voyage.entities.Destination)
	 */
	@Override
	public void deleteDestination(Destination d){
		try {
			catalogueDAO.delete(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see voyage.services.ICatalogueService#getDestinationById(voyage.entities.Destination)
	 */
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


