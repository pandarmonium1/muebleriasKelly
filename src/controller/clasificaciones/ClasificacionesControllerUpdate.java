package controller.clasificaciones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOFatalUserException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Clasificacion;
import model.entity.Producto;
import model.entity.Role;
import model.entity.User;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ClasificacionesControllerUpdate extends HttpServlet {
	private boolean status = false;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(req.getParameter("userId")).longValue());
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String adminMaestro = "bryan96.sc@gmail.com";
		String error;
		if (uGoogle == null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else {
			if (!uGoogle.getEmail().equals(adminMaestro)){
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
							try{
								PersistenceManager pm1 = PMF.get().getPersistenceManager();
								String query = "select  from " + Clasificacion.class.getName();
								List<Clasificacion> clasificaciones = (List<Clasificacion>) pm.newQuery(query).execute();
								req.setAttribute("clasificaciones", clasificaciones);
								pm1.close();
								
								PersistenceManager pm2 = PMF.get().getPersistenceManager();
								Key cl = KeyFactory.createKey(Clasificacion.class.getSimpleName(), new Long(req.getParameter("clasificacionId")).longValue());
								Clasificacion r = pm.getObjectById(Clasificacion.class, cl);
								req.setAttribute("clasificacion", r);
								pm2.close();
								
								req.getRequestDispatcher("/WEB-INF/Views/Clasificacion/update.jsp").forward(req, resp);
								}
								catch (JDOFatalUserException e) {
									resp.sendRedirect("/clasificaciones");	
								}
						}
						
						else{
							error = "no tiene accesos de administrador";
							req.setAttribute("error", error);
							req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
						}
					}
				}
			} else {
				try{
					PersistenceManager pm1 = PMF.get().getPersistenceManager();
					String query = "select  from " + Clasificacion.class.getName();
					List<Clasificacion> clasificaciones = (List<Clasificacion>) pm.newQuery(query).execute();
					req.setAttribute("clasificaciones", clasificaciones);
					pm1.close();
					
					PersistenceManager pm2 = PMF.get().getPersistenceManager();
					Key cl = KeyFactory.createKey(Clasificacion.class.getSimpleName(), new Long(req.getParameter("clasificacionId")).longValue());
					Clasificacion r = pm.getObjectById(Clasificacion.class, cl);
					req.setAttribute("clasificacion", r);
					pm2.close();
					
					req.getRequestDispatcher("/WEB-INF/Views/Clasificacion/update.jsp").forward(req, resp);
					}
					catch (JDOFatalUserException e) {
						resp.sendRedirect("/clasificaciones");	
					}
					}
		}

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Clasificacion.class.getSimpleName(), new Long(req.getParameter("clasificacionId")).longValue());
		Clasificacion a = pm.getObjectById(Clasificacion.class, k);

		String clasi= req.getParameter("padre");
		String name = req.getParameter("name");	
		status= true;
		if(clasi!=null){
			Long padre = Long.parseLong(clasi);
			a.setName(name);
			a.setStatus(status);
			a.setIdPadre(padre);
		}
		else {
			a.setName(name);
			a.setStatus(status);	
			}
		resp.sendRedirect("/clasificaciones");
		
		resp.sendRedirect("/access");
		pm.close();

		}
	}

