package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import pmf.entity.PMF;
@SuppressWarnings("serial")

public class UserControllerIndex extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " +User.class.getName();
		List<User> users = (List<User>) pm.newQuery(query).execute();
		req.setAttribute("users", users);
		req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp").forward(req, resp);
		pm.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp").forward(req, resp);
	}
}
