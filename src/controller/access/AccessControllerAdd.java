package controller.access;

import java.io.IOException;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Resources;
import model.entity.Role;
import model.entity.User;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
	private boolean status = false;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String adminMaestro = "bryan96.sc@gmail.com";
		String error;
		
		if(uGoogle== null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else {
			if(!uGoogle.getEmail().equals(adminMaestro)){
				String queryUsers = "select from "+User.class.getName()+" where email == '"+uGoogle.getEmail()+"' && status ==true";
				List<User> searchUser = (List<User>) pm.newQuery(queryUsers).execute();
				
				if(searchUser.isEmpty()){
					req.getRequestDispatcher("/WEB-INF/Views/Errors/error2.jsp").forward(req, resp);
				} else {
					String  admin_1="Administrador";
					Long idRoleAdmin = searchUser.get(0).getIdRole();
					String queryAdmin = "select from "+Role.class.getName()+" where roles== '"+admin_1+"' && status==true";
					List<Role> searchAdmin = (List<Role>) pm.newQuery(queryAdmin).execute();
					boolean entradaAdmin = searchAdmin.get(0).getId().equals(idRoleAdmin);
					if(searchAdmin.isEmpty()){
						error = "No se encuentra administrador";
						req.setAttribute("error", error);
						req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
					} else {
						if(entradaAdmin){
							String query = "select  from " + Role.class.getName();
							List<Role> roles = (List<Role>) pm.newQuery(query).execute();

							String query2 = "select  from "+ Resources.class.getName();
							List<Resources> resource = (List<Resources>) pm.newQuery(query2).execute();

							req.setAttribute("resource", resource);
							req.setAttribute("roles", roles);
							req.getRequestDispatcher("/WEB-INF/Views/Access/add.jsp").forward(req, resp);
						}
						else{
							error = "no tiene accesos de administrador";
							req.setAttribute("error", error);
							req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
						}
					}
				}
			} else {
				String query = "select  from " + Role.class.getName();
				List<Role> roles = (List<Role>) pm.newQuery(query).execute();

				String query2 = "select  from "+ Resources.class.getName();
				List<Resources> resource = (List<Resources>) pm.newQuery(query2).execute();

				req.setAttribute("resource", resource);
				req.setAttribute("roles", roles);
				req.getRequestDispatcher("/WEB-INF/Views/Access/add.jsp").forward(req, resp);
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Long rol = Long.parseLong(req.getParameter("rol"));
		String src = req.getParameter("resource");	
		status= true;
		Access b = new Access(rol,src,status);
		try {
			pm.makePersistent(b);
			resp.sendRedirect("/access");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
