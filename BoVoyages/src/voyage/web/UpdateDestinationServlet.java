package voyage.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.entities.Destination;
import voyage.services.CatalogueService;

@WebServlet({ "/UpdateDestinationServlet", "/updateDestination" })
public class UpdateDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère les informations du formulaire
		String continent = request.getParameter("continent");
		String pays = request.getParameter("pays");
		String region = request.getParameter("region");
		String description = request.getParameter("description");
		String destinationId = request.getParameter("id");
		int id = Integer.parseInt(destinationId);
		
		// On récupère le service dans le contexte applicatif
		CatalogueService service = (CatalogueService) getServletContext().getAttribute("catalogueService");
		if(continent != null && pays != null && region != null && description != null && id != 0){
			Destination d = new Destination(continent, pays, region, description);
			d.setId(id);
			service.updateDestination(d);
		} 
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/showAll");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
