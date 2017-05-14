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

@WebServlet({ "/GetDestinationDetailsServlet", "/getDestinationDetails" })
public class GetDestinationDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'identifiant envoyé par le formulaire
		String destinationId = request.getParameter("id");
		int id = Integer.parseInt(destinationId);
		
		// On récupère le service dans le contexte applicatif
		CatalogueService service = (CatalogueService) getServletContext().getAttribute("catalogueService");
		Destination d = service.getDestinationById(id);
		
		// On enregistre la destination dans le contexte de requête
		request.setAttribute("destination", d);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/modifierDestination.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
