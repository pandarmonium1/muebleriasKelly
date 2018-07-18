package controller.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Resources;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ResourcesControllerIndex extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " +Resources.class.getName();
		List<Resources> resource = (List<Resources>) pm.newQuery(query).execute();
		req.setAttribute("resource", resource);
		req.getRequestDispatcher("/WEB-INF/Views/Resources/index.jsp").forward(req, resp);
		pm.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Views/Resources/index.jsp").forward(req, resp);
	}
}
