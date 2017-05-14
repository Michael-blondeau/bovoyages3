package voyage.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.entities.Destination;
import voyage.services.CatalogueService;

@WebServlet({ "/DeleteDestinationServlet", "/deleteDestination" })
public class DeleteDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("id");
		int id = Integer.parseInt(s);
		
		// On récupère le service dans le contexte applicatif
		CatalogueService service = (CatalogueService) getServletContext().getAttribute("catalogueService");
		service.deleteDestination(service.getDestinationById(id));
		
		// On demande au service de nous renvoyer la liste de destinations
		List<Destination> destinations = service.getAllDestinations();
		// On envoit la liste de destinations dans le contexte de la requête
		request.setAttribute("destinations", destinations);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/listeDestinations.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
