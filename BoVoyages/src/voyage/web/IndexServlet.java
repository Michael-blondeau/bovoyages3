package voyage.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.dao.CatalogueDAO;
import voyage.services.CatalogueService;


@WebServlet({ "/IndexServlet", "/index" , "/index.jsp"})
public class IndexServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(IndexServlet.class.getCanonicalName());
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère le service dans le contexte applicatif
		CatalogueService service = (CatalogueService) getServletContext().getAttribute("catalogueService");
		List<String> allPays = service.getAllUniquePays();
		request.setAttribute("allPays", allPays);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
