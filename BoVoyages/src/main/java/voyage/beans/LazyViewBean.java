package voyage.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import voyage.services.CatalogueService;
import voyage.services.LazyDestinationDataModel;

@ManagedBean(name="lazyView")
@ViewScoped
public class LazyViewBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(LazyViewBean.class.getName());
	
	@Inject
	private CatalogueService service;

	private LazyDestinationDataModel lazyModel;
	private String pays;

	public LazyViewBean() {
	}
	
	@PostConstruct
	public void init(){
		lazyModel = new LazyDestinationDataModel(service);
		lazyModel.setRowCount((int) service.count(new HashMap<String, String> ()));
		LOG.info("Initialisation du lazymodel");
	}

	public LazyDestinationDataModel getLazyModel() {
		lazyModel.setPays(pays);
		return lazyModel;
	}

	public void setLazyModel(LazyDestinationDataModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
