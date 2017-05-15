package voyage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import voyage.entities.Destination;

/**
 * Implémentation de l'interface {@link ICatalogueDAO}.
 * 
 * @author Adminl
 * @version 2.0
 */
public class CatalogueDAO implements ICatalogueDAO {
	private static final Logger LOG = Logger.getLogger(CatalogueDAO.class.getCanonicalName());
	@PersistenceContext(unitName="bovoyage") private EntityManager em;
	private UserTransaction ut;

	@Override
	public void saveOrUpdate(Destination destination) throws SQLException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		ut.begin();
		if(destination.getId() == 0){
			em.persist(destination);
		}else{
			em.merge(destination);
			LOG.info("La destination existe déjà ! Mise à jour de la destination correspondate.");
		}
		ut.commit();
	}

	@Override
	public void delete(Destination destination) throws SQLException, SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		if(destination.getId() != 0){
			delete(destination.getId());
		}else{
			LOG.info("Impossible de supprimer la destination (n'existe pas).");
		}
	}
	
	private void delete(int id) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		ut.begin();
		Destination d = em.find(Destination.class, id);
		em.remove(d);
		ut.commit();
	}

	public List<Destination> getAllDestinations(){
		Query query = em.createNamedQuery("allDestinations");
		return query.getResultList();
	}

	public List<Destination> getDestinationByPays(String pays){
		Query query = em.createNamedQuery("destinationByPays");
		query.setParameter("p", pays);
		List<Destination> destinations = query.getResultList();
		return destinations;
	}

	@Override
	public Destination getDestinationById(int id) throws SQLException {
		Destination d = em.find(Destination.class, id);
		return d;
	}

	@Override
	public List<String> getAllUniquePays() throws SQLException {
		Query query = em.createNamedQuery("allUniquePays");
		List<String> payss = query.getResultList();
		return payss;
	}

	@Override
	public void update(Destination destination) throws SQLException, SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		saveOrUpdate(destination);
		
	}

}
