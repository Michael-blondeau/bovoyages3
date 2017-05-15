package voyage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import voyage.entities.Destination;

/**
 * Implémentation de l'interface {@link ICatalogueDAO}.
 * 
 * @author Adminl
 * @version 2.0
 */
public class CatalogueDAO implements ICatalogueDAO {
	private static final Logger LOG = Logger.getLogger(CatalogueDAO.class.getCanonicalName());
	private DAO dao;

	public CatalogueDAO(DAO dao) {
		this.dao = dao;
	}

	public CatalogueDAO() {
		super();
	}

	@Override
	public void saveOrUpdate(Destination destination) throws SQLException {
		if(destination.getId() == 0){
			Connection connection = dao.getConnection();
			String sql = "INSERT INTO destinations (continent,pays,region,description) VALUES(?,?,?,?)";
			PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, destination.getContinent());
			st.setString(2, destination.getPays());
			st.setString(3, destination.getRegion());
			st.setString(4, destination.getDescription());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()){
				destination.setId(rs.getInt(1));
			}
			dao.close(connection);
		} else {
			LOG.info("La destination existe déjà ! Mise à jour de la destination correspondate.");
			update(destination);
		}
	}
	
	@Override
	public void update(Destination destination) throws SQLException {
		if (destination.getId() != 0){
			Connection connection = dao.getConnection();
			String sql = "UPDATE destinations SET continent=?, pays=?, region=?, description=? WHERE pk_destination=?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, destination.getContinent());
			st.setString(2, destination.getPays());
			st.setString(3, destination.getRegion());
			st.setString(4, destination.getDescription());
			st.setInt(5, destination.getId());
			st.executeUpdate();
			dao.close(connection);
		} else {
			LOG.info("La destination n'existe pas encore ! Création...");
			saveOrUpdate(destination);
		}

	}

	@Override
	public void delete(Destination destination) throws SQLException {
		if (destination.getId() != 0){
			Connection connection = dao.getConnection();
			String sql = "DELETE FROM destinations WHERE pk_destination=?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, destination.getId());
			int affectedRows = st.executeUpdate();
			if (affectedRows !=0){
				destination.setId(0);
			} else {
				LOG.info("Impossible de supprimer la destination (n'existe pas).");
			}
			dao.close(connection);
		} else {
			LOG.info("Impossible de supprimer la destination (n'existe pas).");
		}

	}

	@Override
	public List<Destination> getAllDestinations() throws SQLException {
		Connection connection = dao.getConnection();
		List<Destination> destinations = new ArrayList<>();
		String sql = "SELECT * FROM destinations";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			Destination d = createDestination(rs);
			destinations.add(d);
		}
		dao.close(connection);
		return destinations;
	}

	@Override
	public List<Destination> getDestinationByPays(String pays) throws SQLException {
		Connection connection = dao.getConnection();
		List<Destination> destinations = new ArrayList<>();
		String sql = "SELECT * FROM destinations WHERE pays =?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, pays);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			Destination d = createDestination(rs);
			destinations.add(d);
		}
		dao.close(connection);
		return destinations;
	}

	@Override
	public Destination getDestinationById(int id) throws SQLException {
		Connection connection = dao.getConnection();
		String sql ="SELECT * FROM destinations WHERE pk_destination =?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		Destination d = null;
		if(rs.next()){
			d = createDestination(rs);
		}
		dao.close(connection);
		return d;
	}

	@Override
	public List<String> getAllUniquePays() throws SQLException {
		Connection connection = dao.getConnection();
		List<String> liste = new ArrayList<>();
		String sql = "SELECT DISTINCT pays FROM destinations";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			liste.add(rs.getString("pays"));
		}
		dao.close(connection);
		return liste;
	}


	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	private Destination createDestination(ResultSet rs) throws SQLException{
		Destination d = new Destination();
		d.setContinent(rs.getString("continent"));
		d.setPays(rs.getString("pays"));
		d.setRegion(rs.getString("region"));
		d.setDescription(rs.getString("description"));
		d.setId(rs.getInt("pk_destination"));
		return d;
	}


}
