package voyage.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import voyage.entities.DatesVoyages;
import voyage.entities.Destination;
import voyage.services.CatalogueService;

@Named("fullDestination")
@ConversationScoped
public class DestinationFullBean implements Serializable {
	private static final long serialVersionUID = -8090576106232814027L;

	@Inject
	private CatalogueService service;
	
	@Inject
	Conversation conversation;

	private int id;
	private String continent;
	private String pays;
	private String region;
	private String description;
	
	private List<DatesVoyages> dates;

	public DestinationFullBean() {
	}

	public DestinationFullBean(int id, String continent, String pays, String region, String description) {
		this.id = id;
		this.continent = continent;
		this.pays = pays;
		this.region = region;
		this.description = description;
	}
	
	public String viewDates(int id) {
		startConversation();
		
		Destination d = service.getDestinationById(id);
		this.id = d.getId();
		this.continent = d.getContinent();
		this.pays = d.getPays();
		this.region = d.getRegion();
		this.description = d.getDescription();
		this.dates = d.getDates();
		
		return "viewDates?faces-redirect=true";
    }
	
	public void startConversation(){
		if (conversation.isTransient()) {
			conversation.begin();
		}	
	}
	
	public void stopConversation(){
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DatesVoyages> getDates() {
		return dates;
	}

	public void setDates(List<DatesVoyages> dates) {
		this.dates = dates;
	}

}
