package controller.producto;
import java.io.IOException;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import javax.servlet.*;  
import javax.servlet.http.*;
import pmf.entity.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Proforma;
import model.entity.Resources;
import model.entity.User;  
import model.entity.Producto;  
@SuppressWarnings("serial")
public class ProductoControllerDelete extends HttpServlet {  
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error; 
		if(uGoogle==null){
			error = "Necesita iniciar sesion ";
			request.setAttribute("error", error);
			RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
			dp.forward(request, response);
		}
		else{
			PersistenceManager accesoControlador=PMF.get().getPersistenceManager();
			String qUsers="select from "+ User.class.getName()+" where email=='"+uGoogle.getEmail()+"' && status==true";
			List<User> uSearch=(List<User>) accesoControlador.newQuery(qUsers).execute();
			if(uSearch.isEmpty()){
				error = "Usuario no registrado";
				request.setAttribute("error", error);
				RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
				dp.forward(request, response);
			}else{
				String query2="select from "+ Resources.class.getName()
						+" where url == '"+request.getServletPath()+"' && status==true";
				List <Resources> rSearch=(List<Resources>) accesoControlador.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					error= "Recurso NO REGISTRADO";
					request.setAttribute("error", error);
					RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
					dp.forward(request, response);
				}
				else{
					String query3 = "select from "+Access.class.getName()
							+" where idRole == "+uSearch.get(0).getIdRole()+" && id== "+rSearch.get(0).getId()+" && status==true";	
					List <Access> aSearch=(List<Access>) accesoControlador.newQuery(query3).execute();		
					if(aSearch.isEmpty()){
						error = "no se registro el acceso";
						request.setAttribute("error", error);
						RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
						dp.forward(request, response);
					}else{
						accesoControlador.close();

						PersistenceManager pm = PMF.get().getPersistenceManager();
						Key k = KeyFactory.createKey(Producto.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
						try{
							Producto r = pm.getObjectById(Producto.class, k);
							if (r !=null){
								pm.deletePersistent(r);

								response.sendRedirect("/productos");
								pm.close();
							}
						}catch (JDOObjectNotFoundException e) {
							response.sendRedirect("/productos");
						}

					}
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		doGet(request,response);	
	}
}