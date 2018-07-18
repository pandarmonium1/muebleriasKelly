package controller.resources;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.Resources;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ResourcesControllerView extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm= PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Resources.class.getSimpleName(), Long.parseLong(req.getParameter("userId")));
		Resources resource = pm.getObjectById(Resources.class,k);
		req.setAttribute("resource", resource);
		req.getRequestDispatcher("/WEB-INF/Views/Resources/view.jsp").forward(req, resp);
		pm.close();
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/Resources");
	}
}
