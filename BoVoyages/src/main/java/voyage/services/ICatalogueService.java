package voyage.services;

import java.util.List;

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

}