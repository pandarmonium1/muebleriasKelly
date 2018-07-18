package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import pmf.entity.PMF;
import model.entity.Access;
import model.entity.Resources;
import model.entity.Role;
import model.entity.User;

@SuppressWarnings("serial")
public class UserControllerUpdate extends HttpServlet{

	private boolean gender;
	private boolean status = true;
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(req.getParameter("userId")).longValue());
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String adminMaestro = "bryan96.sc@gmail.com";
		String error;
		if(uGoogle==null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else{
			if(!uGoogle.getEmail().equals(adminMaestro)){
				String queryUsers = "select from "+User.class.getName()+ " where email== '"+uGoogle.getEmail()+"' && status==true";
				List<User> searchUsers = (List<User>) pm.newQuery(queryUsers).execute();

				if(searchUsers.isEmpty()){
					req.getRequestDispatcher("/WEB-INF/Views/Errors/error2.jsp").forward(req, resp);
				} else {
					String  admin_1="Administrador";
					Long idRoleAdmin = searchUsers.get(0).getIdRole();
					String queryAdmin = "select from "+Role.class.getName()+" where roles== '"+admin_1+"' && status==true";
					List<Role> searchAdmin = (List<Role>) pm.newQuery(queryAdmin).execute();
					boolean entradaAdmin = searchAdmin.get(0).getId().equals(idRoleAdmin);
					if(searchAdmin.isEmpty()){
						error = "No se encuentra administrador";
						req.setAttribute("error", error);
						req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
					} else {
						if(entradaAdmin){
							User user = pm.getObjectById(User.class, k);
							String query = "select  from " + Role.class.getName();
							List<Role> roles = (List<Role>) pm.newQuery(query).execute();
							req.setAttribute("roles", roles);
							req.setAttribute("user", user);
							req.getRequestDispatcher("/WEB-INF/Views/Users/update.jsp").forward(req, resp);
							pm.close();
						}
						else{
							error = "no tiene permisos de administrador";
							req.setAttribute("error", error);
							req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
						}
					}
				}
			} else {
				User user = pm.getObjectById(User.class, k);
				String query = "select  from " + Role.class.getName();
				List<Role> roles = (List<Role>) pm.newQuery(query).execute();
				req.setAttribute("roles", roles);
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/Views/Users/update.jsp").forward(req, resp);
				pm.close();
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();	
		Key k = KeyFactory.createKey(User.class.getSimpleName(), Long.parseLong(req.getParameter("userId")));
		User us = pm.getObjectById(User.class, k);

		Long rol = Long.parseLong(req.getParameter("rol"));
		String correo = req.getParameter("correo");
		String sexo = req.getParameter("gender");
		if(sexo.equals("masculino"))
			gender = true;
		else
			gender = false;

		us.setEmail(correo);
		us.setIdRole(rol);
		us.setGender(gender);
		us.setStatus(status);

		resp.sendRedirect("/users");
		pm.close();

	}

}
