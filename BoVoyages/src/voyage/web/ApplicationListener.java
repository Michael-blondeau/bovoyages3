package voyage.web;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import voyage.services.CatalogueService;



/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
	@Resource(name="jdbc/bovoyage")	private DataSource ds;
	private static final Logger LOG = Logger.getLogger(ApplicationListener.class.getCanonicalName());
	

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent evt)  { 
    	evt.getServletContext().removeAttribute("catalogueService");
    	LOG.info("Application arrêtée");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent evt)  { 
    	CatalogueService service = new CatalogueService(ds);
		evt.getServletContext().setAttribute("catalogueService", service);
		LOG.info("Application démarrée - ds : " + ds);
    }
	
}
