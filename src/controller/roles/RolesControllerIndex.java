package controller.roles;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Role;
import pmf.entity.PMF;

@SuppressWarnings("serial")
public class RolesControllerIndex extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " +Role.class.getName();
		List<Role> roles = (List<Role>) pm.newQuery(query).execute();
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/WEB-INF/Views/Roles/index.jsp").forward(req, resp);
		
		pm.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Views/Roles/index.jsp").forward(req, resp);
	}
}
