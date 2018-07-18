package controller.producto;
import java.util.*;
import pmf.entity.*;
import java.io.IOException;  
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.jws.WebService;
import javax.servlet.*;  
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Access;
import model.entity.Producto;
import model.entity.Proforma;
import model.entity.Resources;
import model.entity.User;  
@SuppressWarnings("serial")
public class ProductoControllerUpdate extends HttpServlet {  
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)   
			throws ServletException, IOException {  
	
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error;
		if(uGoogle==null){
			error = "necesita iniciar sesión ";
			request.setAttribute("error", error);
			RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
			dp.forward(request, response);
		}
		else{
			PersistenceManager accesoControlador=PMF.get().getPersistenceManager();
			String qUsers="select from "+ User.class.getName()+" where email=='"+uGoogle.getEmail()+"' && status==true";
			List<User> uSearch=(List<User>) accesoControlador.newQuery(qUsers).execute();
			if(uSearch.isEmpty()){
				error = "usuario no registrado";
				request.setAttribute("error", error);
				RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
				dp.forward(request, response);
			}else{
				String query2="select from "+ Resources.class.getName()
						+" where url == '"+request.getServletPath()+"' && status==true";
				List <Resources> rSearch=(List<Resources>) accesoControlador.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					error = "Recurso no añadido";
					request.setAttribute("error", error);
					RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
					dp.forward(request, response);
				}
				else{
					String query3 = "select from "+Access.class.getName()
							+" where idRole == "+uSearch.get(0).getIdRole()+" && id== "+rSearch.get(0).getId()+" && status==true";	
					List <Access> aSearch=(List<Access>) accesoControlador.newQuery(query3).execute();		
					if(aSearch.isEmpty()){
						error = "no se registró el acceso";
						request.setAttribute("error", error);
						RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
						dp.forward(request, response);
					}else{
						accesoControlador.close();	

						PersistenceManager pm = PMF.get().getPersistenceManager();
						Key k = KeyFactory.createKey(Producto.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
						Producto r = pm.getObjectById(Producto.class, k);

						request.setAttribute("producto", r);

						request.getRequestDispatcher("/WEB-INF/Views/Productos/update.jsp").forward(request, response);
						pm.close();
					}
				}
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)   
			throws ServletException, IOException {  

		PersistenceManager pm=PMF.get().getPersistenceManager();
		String name=request.getParameter("name");  
		String uPrecio=request.getParameter("uPrecio");
		String qProducto="select from "+ Producto.class.getName()+ "where name=='"+name+"'";
		List<Producto> productos=(List<Producto>) pm.newQuery(qProducto).execute();
		
		double precio=Double.parseDouble(uPrecio);
		
		
		if(productos.isEmpty()){

			Key k = KeyFactory.createKey(Producto.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
			Producto r = pm.getObjectById(Producto.class, k);
			r.setName(name);
			r.setpPrecio(precio);
			response.sendRedirect("/productos");
			pm.close();
			
		}

		}
	}