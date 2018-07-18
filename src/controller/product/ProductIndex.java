package controller.product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import pmf.entity.*;
import model.entity.*;
import javax.servlet.*;
import javax.jdo.PersistenceManager;

public class ProductIndex extends HttpServlet {

	private static final long serialVersionUID = 1L;	
	public ProductIndex() {
        super();
    }	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();		

		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String admin = "bryan96.sc@gmail.com";
		String error;
		if(uGoogle ==null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else {
			String queryUsers = "select from "+User.class.getName()+ " where email== '"+uGoogle.getEmail()+"' && status==true";
			List<User> searchUsers = (List<User>) pm.newQuery(queryUsers).execute();

			if(searchUsers.isEmpty()){
				error = "Usuario no registrado/Sin permisos.";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
			} else {

				String queryResources = "select from "+Resources.class.getName()+ " where url == '"+req.getServletPath()+"' && status==true";
				List<Resources> searchResources = (List<Resources>) pm.newQuery(queryResources).execute();

				if(searchResources.isEmpty()){
					error = "recurso no registrado.";
					req.setAttribute("error", error);
					req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
				} else {

					String queryAccess = "select from " +Access.class.getName()+ " where idRol =="+searchUsers.get(0).getIdRole()+" && resource== '"+searchResources.get(0).getName()+"' && status==true";
					List<Access> searchAccess = (List<Access>) pm.newQuery(queryAccess).execute();

					if(searchAccess.isEmpty()){
						error = "no se registro el acceso.";
						req.setAttribute("error", error);
						req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
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
								List<SalaComedor> salas = (List<SalaComedor>)pm.newQuery(SalaComedor.class).execute();
								List<JuegoDeSala> juegos = (List<JuegoDeSala>)pm.newQuery(JuegoDeSala.class).execute();
								List<Vitrina> vitrinas = (List<Vitrina>)pm.newQuery(Vitrina.class).execute();
								req.setAttribute("salas", salas);
								req.setAttribute("juegos", juegos);
								req.setAttribute("vitrinas", vitrinas);
								
								
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/index.jsp"); 
								dispatcher.forward(req, resp); 
							}
							else{
								if(idRoleAdmin.equals(searchAccess.get(0).getIdRol()) && searchAccess.get(0).getResource().equals("/product")){
									List<SalaComedor> salas = (List<SalaComedor>)pm.newQuery(SalaComedor.class).execute();
									List<JuegoDeSala> juegos = (List<JuegoDeSala>)pm.newQuery(JuegoDeSala.class).execute();
									List<Vitrina> vitrinas = (List<Vitrina>)pm.newQuery(Vitrina.class).execute();
									req.setAttribute("salas", salas);
									req.setAttribute("juegos", juegos);
									req.setAttribute("vitrinas", vitrinas);
									
									
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/index.jsp"); 
									dispatcher.forward(req, resp); 
								} else {
									error = "solo es administrador puede editar.";
									req.setAttribute("error", error);
									req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
								}
							}
						}
					}
				}
			}
		}
		
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
