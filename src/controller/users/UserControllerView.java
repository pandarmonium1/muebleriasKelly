package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.User;
import pmf.entity.PMF;

@SuppressWarnings("serial")
public class UserControllerView extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		
		if(uGoogle==null){
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error1.jsp").forward(req, resp);
		} else {
			PersistenceManager pm= PMF.get().getPersistenceManager();
			String queryUsers = "select from "+ User.class.getName()+ " where email== '"+uGoogle.getEmail()+"'"+" && status == true";
			List<User> searchUser = (List<User>) pm.newQuery(queryUsers).execute();
			
			if(searchUser.isEmpty()){
				req.getRequestDispatcher("/WEB-INF/Views/Errors/error2.jsp").forward(req, resp);
			} else{
				Key k = KeyFactory.createKey(User.class.getSimpleName(), Long.parseLong(req.getParameter("userId")));
				User us = pm.getObjectById(User.class,k);
				req.setAttribute("user", us);
				req.getRequestDispatcher("/WEB-INF/Views/Users/view.jsp").forward(req, resp);
				pm.close();
			}
		}
		
		
		/*PersistenceManager pm= PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(User.class.getSimpleName(), Long.parseLong(req.getParameter("userId")));
		User us = pm.getObjectById(User.class,k);
		req.setAttribute("user", us);
		req.getRequestDispatcher("/WEB-INF/Views/Users/view.jsp").forward(req, resp);
		pm.close();
		*/
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/users");
	}


}
