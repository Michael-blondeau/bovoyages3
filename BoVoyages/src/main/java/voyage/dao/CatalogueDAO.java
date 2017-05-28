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

	/**
	 * Constructeur par défaut.
	 */
	public CatalogueDAO() {
	}

	/**
	 * Sauvegarde une {@link Destination}, ou la met à jour si elle existe déjà.
	 * 
	 * @param destination
	 *            La destination a enregistrer
	 */
	@Override
	public void saveOrUpdate(Destination destination) throws DAOException {
		try {
			ut.begin();
			if (destination.getId() == 0) {
				em.persist(destination);
				LOG.info("Enregistrement de la nouvelle destination.");
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

	/**
	 * Sauvegarde une {@link DatesVoyages}, ou la met à jour si elle existe
	 * déjà.
	 * 
	 * @param date
	 *            La date à enregistrer
	 */
	@Override
	public void saveOrUpdate(DatesVoyages date) throws DAOException {
		try {
			ut.begin();
			if (date.getId() == 0) {
				em.persist(date);
				LOG.info("Enregistrement de la nouvelle date de voyage.");
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

	/**
	 * Supprime une destination.
	 * 
	 * @param destination
	 *            La destination à supprimer
	 */
	@Override
	public void delete(Destination destination) throws DAOException {
		if (destination.getId() != 0) {
			delete(destination.getId());
			LOG.info("La destination a été supprimée.");
		} else {
			LOG.info("Impossible de supprimer la destination (n'existe pas).");
		}
	}

	/**
	 * Supprime une destination grâce à son identifiant.
	 * 
	 * @param id
	 *            L'identifiant de la destination à supprimer
	 * @throws DAOException
	 *             En cas d'erreur de suppression
	 */
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

	/**
	 * Supprime une date de voyage.
	 * 
	 * @param date
	 *            La date de voyage à supprimer
	 */
	@Override
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

	/**
	 * Récupère la liste de toutes les destinations en base de données.
	 * 
	 * @return Une liste de destinations
	 */
	public List<Destination> getAllDestinations() {
		Query query = em.createNamedQuery("allDestinations");
		List<Destination> destinations = query.getResultList();
		return destinations;
	}

	/**
	 * Récupère la liste de toutes les destinations en base de données pour un
	 * pays donné.
	 * 
	 * @param pays
	 *            Le pays recherché
	 * @return Une liste de destinations
	 */
	public List<Destination> getDestinationByPays(String pays) {
		Query query = em.createNamedQuery("destinationByPays");
		query.setParameter("p", pays);
		List<Destination> destinations = query.getResultList();
		return destinations;
	}

	/**
	 * Récupère une destination en base de données.
	 * 
	 * @param id
	 *            L'identifiant de la destination recherchée
	 * @return Une destination
	 */
	@Override
	public Destination getDestinationById(int id) {
		Destination d = em.find(Destination.class, id);
		return d;
	}

	/**
	 * Récupère la liste de tous les pays uniques en base de données.
	 * 
	 * @return Une liste de pays
	 */
	@Override
	public List<String> getAllUniquePays() {
		Query query = em.createNamedQuery("allUniquePays");
		List<String> payss = query.getResultList();
		return payss;
	}

	/**
	 * Met à jour une destination
	 * 
	 * @param destination
	 *            La destination que l'on souhaite mettre à jour
	 * 
	 */
	@Override
	public void update(Destination destination) throws DAOException {
		saveOrUpdate(destination);
	}

	/**
	 * Récupère la liste de dates de voyage pour une destination donnée.
	 * 
	 * @param destinationId
	 *            L'identifiant de la destination
	 * @return Une liste de dates de voyage
	 */
	@Override
	public List<DatesVoyages> getDates(int destinationId) {
		List<DatesVoyages> dates = null;
		Query query = em.createQuery("SELECT d.dates FROM Destination d WHERE d.id = :id");
		query.setParameter("id", destinationId);
		dates = query.getResultList();
		return dates;
	}

}
