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


@WebServlet({ "/CreateDestinationServlet", "/createDestination" })
public class CreateDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère les informations du formulaire
		String continent = request.getParameter("continent");
		String pays = request.getParameter("pays");
		String region = request.getParameter("region");
		String description = request.getParameter("description");
		Boolean success = true;
		
		// On récupère le service dans le contexte applicatif
		CatalogueService service = (CatalogueService) getServletContext().getAttribute("catalogueService");
		if(continent != null && pays != null && region != null && description != null){
			service.addDestination(new Destination(continent, pays, region, description));
		} else {
			success = false;
		}
		
		// On enregistre un booléen true si l'opération a fonctionné, sinon false
		request.setAttribute("success", success);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/resultat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
