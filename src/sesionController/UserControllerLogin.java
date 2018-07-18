package sesionController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class UserControllerLogin extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		
		//HttpSession nuevaSesion = req.getSession(true);
		
		/*if(user == null && nuevaSesion!=null){
			resp.sendRedirect("/sesion/registro");
		}else{
			if (user != null && nuevaSesion==null){
				resp.sendRedirect(us.createLoginURL("/sesion/login"));
			}
		}*/
		
		if(user == null){
			resp.sendRedirect(us.createLoginURL("/sesion/login"));
		}
		else {
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/Views/Login/login.jsp").forward(req, resp);
		}
	}

}
