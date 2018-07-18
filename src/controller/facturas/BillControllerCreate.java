package controller.facturas;
import model.entity.Access;
import model.entity.Facturar;
import model.entity.Resources;
import model.entity.Role;
import model.entity.User;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import pmf.entity.PMF;


@SuppressWarnings("serial")
public class BillControllerCreate extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error;
		if(uGoogle ==null){
			error = "necesita iniciar sesion.";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp").forward(req, resp);
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
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
								req.getRequestDispatcher("/WEB-INF/Views/Factura/create.jsp").forward(req, resp);
							}
							else{
								if(idRoleAdmin.equals(searchAccess.get(0).getIdRol()) && searchAccess.get(0).getResource().equals("/facturas/add")){
									req.getRequestDispatcher("/WEB-INF/Views/Factura/create.jsp").forward(req, resp);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String codigoProducto = req.getParameter("codigoProd");
		int cantidad = Integer.parseInt(req.getParameter("cantidad"));
		String descripcion = req.getParameter("descripcion");
		float precioProducto = Float.parseFloat(req.getParameter("precioProducto"));
		float total;
		total = cantidad*precioProducto;
		total = (float) (Math.round(total*10)*0.1);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Facturar a = new Facturar(
				codigoProducto,
				cantidad,
				descripcion,
				precioProducto,
				total);
		try{
			resp.sendRedirect("/facturas");
			pm.makePersistent(a);
		}
		finally {
			pm.close();
		}
	}
}