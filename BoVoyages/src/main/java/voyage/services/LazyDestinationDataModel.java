package voyage.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import voyage.entities.Destination;

/**
 * Cette classe permet de récupérer la liste des destinations sous forme
 * paginée.
 * 
 * @author Pierrik
 *
 */
public class LazyDestinationDataModel extends LazyDataModel<Destination> {
	private ICatalogueService service;
	private String pays;

	public LazyDestinationDataModel(ICatalogueService service, String pays) {
		this.service = service;
		this.pays = pays;
	}

	public LazyDestinationDataModel(ICatalogueService service) {
		this.service = service;
	}

	@Override
	public List<Destination> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		Map<String, String> filtersNew = (Map) filters;
		System.out.println(">>> " + filters);
		// Récupération de la liste de villes
//		List<Destination> destinations = service.getDestinations(first, first + pageSize, sortField, sortOrder, pays);
		List<Destination> destinations = service.getDestinations(first, first + pageSize, sortField, sortOrder, filtersNew);

		// Tri
		if (sortField != null) {
			Collections.sort(destinations, new LazySorter(sortField, sortOrder));
		}

		// Nombre de lignes
//		if (pays == null || pays == "") {
//			this.setRowCount((int) service.getAllDestinationCount());
//		} else {
//			this.setRowCount((int) service.getDestinationByPaysCount(pays));
//		}
		this.setRowCount((int) service.count(filtersNew));

		// Retour
		return destinations;
	}

	public ICatalogueService getService() {
		return service;
	}

	public void setService(ICatalogueService service) {
		this.service = service;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
