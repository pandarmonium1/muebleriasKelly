package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Access;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class AccessControllerIndex extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " +Access.class.getName();
		List<Access> access = (List<Access>) pm.newQuery(query).execute();
		req.setAttribute("access", access);
		req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp").forward(req, resp);
		pm.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp").forward(req, resp);
	}
}
