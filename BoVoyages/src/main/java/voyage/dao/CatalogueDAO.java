package voyage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import voyage.entities.DatesVoyages;
import voyage.entities.Destination;
import voyage.exceptions.DAOException;

/**
 * Implémentation de l'interface {@link ICatalogueDAO}.
 * 
 * @author Adminl
 * @version 2.0
 */
@Named
@Dependent
public class CatalogueDAO implements ICatalogueDAO, Serializable {
	
	private static final long serialVersionUID = 4750916274788982440L;

	private static final Logger LOG = Logger.getLogger(CatalogueDAO.class.getCanonicalName());

	@PersistenceContext(unitName = "BoVoyages")
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;

	public CatalogueDAO() {	}

	@Override
	public void saveOrUpdate(Destination destination) throws DAOException {
		try {
			ut.begin();
			if (destination.getId() == 0) {
				em.persist(destination);
			} else {
				em.merge(destination);
				LOG.info("La destination existe déjà ! Mise à jour de la destination correspondate.");
			}
			ut.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new DAOException("Erreur lors de l'enregistrement de la destination", e);
		}
	}
	
	@Override
	public void saveOrUpdate(DatesVoyages date) throws DAOException {
		try {
			ut.begin();
			if(date.getId() == 0){
				em.persist(date);
			} else {
				em.merge(date);
				LOG.info("La date pour cette destination existe déjà ! Mise à jour de la date correspondate.");
			}
			ut.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new DAOException("Erreur lors de l'enregistrement de la date", e);
		}
		
		
	}

	@Override
	public void delete(Destination destination) throws DAOException {
		if (destination.getId() != 0) {
			delete(destination.getId());
		} else {
			LOG.info("Impossible de supprimer la destination (n'existe pas).");
		}
	}

	public void delete(int id) throws DAOException {
		try {
			ut.begin();
			Destination d = em.find(Destination.class, id);
			em.remove(d);
			ut.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new DAOException("Erreur lors de la suppression de la destination", e);
		}
	}
	
	public void delete(DatesVoyages date) throws DAOException {
		try {
			ut.begin();
			DatesVoyages dv = em.find(DatesVoyages.class, date.getId());
			em.remove(dv);
			ut.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new DAOException("Erreur lors de la suppression de la date", e);
		}
	}

	public List<Destination> getAllDestinations() {
		Query query = em.createNamedQuery("allDestinations");
		List<Destination> destinations = query.getResultList();
		return destinations;
	}

	public List<Destination> getDestinationByPays(String pays) {
		Query query = em.createNamedQuery("destinationByPays");
		query.setParameter("p", pays);
		List<Destination> destinations = query.getResultList();
		return destinations;
	}

	@Override
	public Destination getDestinationById(int id) {
		Destination d = em.find(Destination.class, id);
		return d;
	}

	@Override
	public List<String> getAllUniquePays() {
		Query query = em.createNamedQuery("allUniquePays");
		List<String> payss = query.getResultList();
		return payss;
	}

	@Override
	public void update(Destination destination) throws DAOException {
		saveOrUpdate(destination);
	}



}
