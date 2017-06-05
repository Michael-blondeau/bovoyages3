package voyage.services;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import voyage.entities.DatesVoyages;
import voyage.entities.Destination;

public interface ICatalogueService {

	List<Destination> getAllDestinations();

	List<Destination> getDestinationsByPays(String pays);
	
	Destination getDestinationById(int id);
	
	List<String> getAllUniquePays();

	void addDestination(Destination d);

	void saveOrUpdate(Destination d);
	
	void updateDestination(Destination d);

	void deleteDestination(Destination d);
	
	void deleteDestination(int id);

	void saveOrUpdate(DatesVoyages date);

	void delete(DatesVoyages date);

	List<DatesVoyages> getDates(int destinationId);

	List<DatesVoyages> getAllDates(int id);

	List<Destination> getDestinations(int first, int end, String sortField, SortOrder sortOrder, String pays);
	
	List<Destination> getDestinations(int first, int end, String sortField, SortOrder sortOrder,
			Map<String, String> filters);

	long count(Map<String, String> filters);

	long getAllDestinationCount();

	long getDestinationByPaysCount(String pays);

	List<String> getAllUniqueRegions();

}