package controller.clasificaciones;

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
import model.entity.Clasificacion;
import model.entity.Role;
import model.entity.User;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ClasificacionesControllerAdd extends HttpServlet {
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
				
				String query = "select  from " + Clasificacion.class.getName();
				List<Clasificacion> clasificaciones = (List<Clasificacion>) pm.newQuery(query).execute();
				req.setAttribute("clasificaciones", clasificaciones);
				req.getRequestDispatcher("/WEB-INF/Views/Clasificaciones/add.jsp").forward(req, resp);
				
				
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String clasi= req.getParameter("padre");
		String name = req.getParameter("name");	
		status= true;
		Clasificacion a;
		if(!clasi.equals("null")){
			Long padre = Long.parseLong(clasi);
			a = new Clasificacion(name,padre,status);
		}
		else {
			a = new Clasificacion(name,status);
			}
		
		try {
			pm.makePersistent(a);
			resp.sendRedirect("/clasificaciones");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
