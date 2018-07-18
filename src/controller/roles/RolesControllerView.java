package controller.roles;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.Role;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class RolesControllerView extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm= PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Role.class.getSimpleName(), Long.parseLong(req.getParameter("userId")));
		Role roles = pm.getObjectById(Role.class,k);
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/WEB-INF/Views/Roles/view.jsp").forward(req, resp);
		pm.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/roles");
	}
		
}
