package voyage.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import voyage.entities.DatesVoyages;
import voyage.entities.Destination;
import voyage.exceptions.DAOException;
import voyage.services.SortingOrder;

/**
 * Cette interface fournit les méthodes CRUD pour la sauvegarde des Destinations
 * en base de données.
 * 
 * Une implémentation est fournie avec la classe {@link CatalogueDAO}.
 * 
 * @author Pierrik et Michael
 *
 */
public interface ICatalogueDAO {
	/**
	 * Cette méthode permet de sauvegarder une nouvelle destination en base de
	 * données.
	 * 
	 * La méthode devrait vérifier si la destination possède un identifiant nul.
	 * Si ce n'est pas le cas, la destination devrait être mise à jour grâce à
	 * la méthode {@link ICatalogueDAO#update(Destination)}. Après avoir
	 * enregistré la nouvelle destination en base de données, il est nécessaire
	 * de mettre à jour l'identifiant unique (id) de la destination par appel de
	 * la méthode {@link Destination#setId(int)}.
	 * 
	 * @param destination
	 *            La destination à sauvegarder.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	void saveOrUpdate(Destination destination) throws DAOException;

	/**
	 * Cette méthode permet de sauvegarder une nouvelle date de voyage en base
	 * de données.
	 * 
	 * @param date
	 *            La date à sauvegarder
	 * @throws DAOException
	 *             En cas d'erreur de sauvegarde
	 */
	void saveOrUpdate(DatesVoyages date) throws DAOException;

	/**
	 * Cette méthode permet de mettre à jour une destination en base de données.
	 * 
	 * La méthode devrait vérifier si la destination possède un identifiant non
	 * nul. Si ce n'est pas le cas, la destination devrait être enregistrée
	 * grâce à la méthode {@link ICatalogueDAO#saveOrUpdate(Destination)}.
	 * 
	 * @param destination
	 *            La destination à mettre à jour.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	void update(Destination destination) throws DAOException;

	/**
	 * Cette méthode permet de supprimer une destination en base de données.
	 * 
	 * La méthode devrait vérifier si la destination possède un identifiant non
	 * nul. Si ce n'est pas le cas, rien ne se passe.
	 * 
	 * @param destination
	 *            La destination à mettre à jour.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	void delete(Destination destination) throws DAOException;

	/**
	 * Cette méthode permet de supprimer une date de voyage en base de données.
	 * 
	 * @param date
	 *            La date de voyage à supprimer
	 * @throws DAOException
	 *             En cas d'erreur de suppression
	 */
	void delete(DatesVoyages date) throws DAOException;

	/**
	 * Cette méthode permet de récupérer la liste de toutes les destinations en
	 * base de données.
	 * 
	 * La méhode devrait renvoyer une liste de toutes les destinations
	 * enregistrées en base de données.
	 * 
	 * @return Une liste de destinations, ou {@code null} si rien n'a été
	 *         trouvé.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	List<Destination> getAllDestinations() throws DAOException;

	/**
	 * Cette méthode permet de récupérer la liste de toutes les destinations en
	 * base de données pour un pays donné.
	 * 
	 * La méhode devrait renvoyer une liste de toutes les destinations
	 * enregistrées en base de données correspondant au pays passé en argument.
	 * 
	 * @param pays
	 *            Le pays utilisé pour filtrer les résultats.
	 * @return Une liste de destinations, ou {@code null} si rien n'a été
	 *         trouvé.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	List<Destination> getDestinationByPays(String pays) throws DAOException;

	/**
	 * Cette méthode permet de récupérer une destination en base de données à
	 * l'aide de son identifiant unique.
	 * 
	 * @param id
	 *            L'identifiant unique de la Destination
	 * @return La destination correspondante à la recherche, ou {@code null} si
	 *         rien n'a été trouvé.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	Destination getDestinationById(int id) throws DAOException;

	/**
	 * Cette méthode permet de récupérer la liste des pays (sans doublons).
	 * 
	 * @return Une liste de tous les pays enregistrés en base de données.
	 * @throws DAOException
	 *             En cas d'erreur
	 */
	List<String> getAllUniquePays() throws DAOException;

	/**
	 * Récupère la liste de dates de voyage pour une destination donnée.
	 * 
	 * @param destinationId
	 *            L'identifiant de la destination
	 * @return Une liste de dates de voyage
	 */
	List<DatesVoyages> getDates(int destinationId);

	List<Destination> getDestinations(int first, int end, String sortField, SortingOrder sortOrder, String pays);

	List<String> getAllUniqueRegions();

	long count(Map<String, String> filters);

	List<Destination> getDestinations(int first, int end, String sortField, SortingOrder sortOrder,
			Map<String, String> filters);
}
