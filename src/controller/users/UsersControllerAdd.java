package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import pmf.entity.PMF;
import model.entity.Access;
import model.entity.Facturar;
import model.entity.Resources;
import model.entity.Role;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet{
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		String adminMaestro = "bryan96.sc@gmail.com";
		String error;
		if(uGoogle == null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else{
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
							String query = "select  from " + Facturar.class.getName();
							List<Facturar> listas = (List<Facturar>) pm.newQuery(query).execute();
							req.setAttribute("listas", listas);
							req.getRequestDispatcher("/WEB-INF/Views/Users/add.jsp").forward(req, resp);
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
				List<Role> listas = (List<Role>) pm.newQuery(query).execute();
				req.setAttribute("roles", listas);
				req.getRequestDispatcher("../WEB-INF/Views/Users/add.jsp").forward(req, resp);	
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String correo = req.getParameter("correo");
		Long rol = Long.parseLong(req.getParameter("rol"));
		String sexo = req.getParameter("gender");
		boolean gender=false ;
		boolean status = true;
		if(sexo.equals("masculino"))
			gender = true;
		else
			gender = false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		User us = new User(
				correo, 
				rol , 
				gender , 
				status);
		
		try{
			resp.sendRedirect("/users");
			pm.makePersistent(us);
		}finally {
			pm.close();
		}
	}

}
