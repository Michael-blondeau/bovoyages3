package voyage.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import voyage.entities.Destination;
import voyage.exceptions.DAOException;

@Ignore("Impossible de tester le DAO sans l'injection depuis le serveur Java EE")
public class CatalogueDAOTest {

	@Test
	public void testCreate() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		Destination destination = new Destination("Continent", "Pays", "Region", "Description longue");
		catalogueDAO.saveOrUpdate(destination);
		assertNotEquals(0, destination.getId());
		catalogueDAO.delete(destination);
	}

	@Test
	public void testUpdate() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		Destination destination = new Destination("Continent", "Pays", "Region", "Description longue");
		catalogueDAO.saveOrUpdate(destination);
		destination.setContinent("Europe");
		catalogueDAO.update(destination);
		Destination dest = catalogueDAO.getDestinationById(destination.getId());
		assertEquals("Europe", dest.getContinent());
		catalogueDAO.delete(destination);
	}

	@Test
	public void testDelete() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		Destination destination = new Destination("Continent", "Pays", "Region", "Description longue");
		catalogueDAO.saveOrUpdate(destination);
		catalogueDAO.delete(destination);
		assertEquals(0, destination.getId());
		assertNull(catalogueDAO.getDestinationById(destination.getId()));
	}

	@Test
	public void testGetAllDestinations() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		List<Destination> dests = catalogueDAO.getAllDestinations();
		for (Destination d : dests) {
			catalogueDAO.delete(d);
		}
		List<Destination> destinations = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			destinations.add(new Destination("Continent", "Pays", "Region", "Description longue"));
			catalogueDAO.saveOrUpdate(destinations.get(i));
		}
		assertEquals(10, catalogueDAO.getAllDestinations().size());
		for (Destination d : destinations) {
			catalogueDAO.delete(d);
		}
	}

	@Test
	public void testGetDestinationByPays() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();

		List<Destination> destinations = catalogueDAO.getAllDestinations();
		for (Destination d : destinations) {
			catalogueDAO.delete(d);
		}
		Destination d1 = new Destination("Continent", "Pays1", "Region", "Description longue");
		Destination d2 = new Destination("Continent", "Pays2", "Region", "Description longue");
		Destination d3 = new Destination("Continent", "Pays3", "Region", "Description longue");
		catalogueDAO.saveOrUpdate(d1);
		catalogueDAO.saveOrUpdate(d2);
		catalogueDAO.saveOrUpdate(d3);

		destinations = catalogueDAO.getDestinationByPays("Pays1");
		assertEquals(1, destinations.size());

		catalogueDAO.delete(d1);
		catalogueDAO.delete(d2);
		catalogueDAO.delete(d3);
	}

	@Test
	public void testGetDestinationById() throws DAOException {
		Destination destination = new Destination("Continent", "Pays", "Region", "Description longue");
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		catalogueDAO.saveOrUpdate(destination);
		int id = destination.getId();
		Destination dest = catalogueDAO.getDestinationById(id);
		assertEquals(dest, destination);
		catalogueDAO.delete(destination);
	}

	@Test
	public void getAllUniquePays() throws DAOException {
		CatalogueDAO catalogueDAO = new CatalogueDAO();
		List<Destination> destinations = catalogueDAO.getAllDestinations();
		for (Destination d : destinations) {
			catalogueDAO.delete(d);
		}
		Destination d1 = new Destination("Continent", "Pays1", "Region", "Description longue");
		Destination d2 = new Destination("Continent", "Pays2", "Region", "Description longue");
		Destination d3 = new Destination("Continent", "Pays3", "Region", "Description longue");
		catalogueDAO.saveOrUpdate(d1);
		catalogueDAO.saveOrUpdate(d2);
		catalogueDAO.saveOrUpdate(d3);

		List<String> liste = catalogueDAO.getAllUniquePays();
		assertEquals(3, liste.size());

		catalogueDAO.delete(d1);
		catalogueDAO.delete(d2);
		catalogueDAO.delete(d3);

	}

}
