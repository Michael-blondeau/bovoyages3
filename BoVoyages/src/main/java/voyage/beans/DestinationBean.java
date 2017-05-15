package voyage.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import voyage.services.CatalogueService;

@ManagedBean(name = "destination")
@ViewScoped
public class DestinationBean implements Serializable {
	private static final long serialVersionUID = -8090576106232814027L;

	@Inject
	private CatalogueService service;

	private int id;
	private String continent;
	private String pays;
	private String region;
	private String description;

	public DestinationBean() {
	}

	public DestinationBean(int id, String continent, String pays, String region, String description) {
		super();
		this.id = id;
		this.continent = continent;
		this.pays = pays;
		this.region = region;
		this.description = description;
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

}
