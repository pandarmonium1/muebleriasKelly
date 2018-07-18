package controller.roles;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Facturar;
import model.entity.Resources;
import model.entity.Role;
import model.entity.User;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet{

	private boolean status = true;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String adminMaestro = "bryan96.sc@gmail.com";
		String error;
		if(uGoogle==null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else {
			if(!uGoogle.getEmail().equals(adminMaestro)){
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String queryUsers = "select from "+User.class.getName()+" where email == '"+uGoogle.getEmail()+"' && status ==true";
				List<User> searchUser = (List<User>) pm.newQuery(queryUsers).execute();
				
				if (searchUser.isEmpty()){
					req.getRequestDispatcher("/WEB-INF/Views/Errors/error2.jsp").forward(req, resp);
				} else {
					String queryResources = "select from "+Resources.class.getName()+ " where url == '"+req.getServletPath()+"' && status==true";
					List<Resources> searchResources = (List<Resources>) pm.newQuery(queryResources).execute();
					
					if(searchResources.isEmpty()){
						req.getRequestDispatcher("/WEB-INF/Views/Errors/error3.jsp").forward(req, resp);
					} else {
						String queryAccess = "select from " +Access.class.getName()+ " where idRol =="+searchUser.get(0).getIdRole()+" && resource== '"+searchResources.get(0).getName()+"' && status==true";
						List<Access> searchAccess = (List<Access>) pm.newQuery(queryAccess).execute();
						
						if(searchAccess.isEmpty()){
							req.getRequestDispatcher("/WEB-INF/Views/Errors/error4.jsp").forward(req, resp);
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
									String query = "select  from " + Facturar.class.getName();
									List<Facturar> listas = (List<Facturar>) pm.newQuery(query).execute();
									req.setAttribute("listas", listas);
									req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
								}
								else{
									error = "no tiene accesos de administrador";
									req.setAttribute("error", error);
									req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
								}
							}
						}
					}
				}
			} else {
				req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String res = req.getParameter("rol");
		if(res != null){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String rol = req.getParameter("rol");
			String primera = rol.substring(0,1).toUpperCase();
			rol = primera+ rol.substring(1,rol.length());
			Role roles = new Role(rol,status);
			try{
				resp.sendRedirect("/roles");
				pm.makePersistent(roles);
			}finally {
				pm.close();
			}
		}
		else{
			req.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp").forward(req, resp);
		}
	}

}
