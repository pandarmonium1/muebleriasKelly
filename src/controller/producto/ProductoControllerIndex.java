package controller.producto;

import java.io.IOException;
import java.util.List;


import javax.jdo.PersistenceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.*;

import com.google.appengine.api.users.UserServiceFactory;

import pmf.entity.PMF;

@SuppressWarnings("serial")
public class ProductoControllerIndex extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error;
		PersistenceManager pm=PMF.get().getPersistenceManager();
		String adminMaestro= "bryan96.sc@gmail.com";
		if(uGoogle==null){
			error = "No estas Logueado";
			request.setAttribute("error", error);
			RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
			dp.forward(request, response);
		}
		else{
			if(!uGoogle.getEmail().equals(adminMaestro)){
				String query="select from "+ User.class.getName()+" where email=='"+uGoogle.getEmail()+"' && status==true";
				List<User> uSearch=(List<User>) pm.newQuery(query).execute();
				if(uSearch.isEmpty()){
					error = "Usuario no registrado";
					request.setAttribute("error", error);
					RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
					dp.forward(request, response);
				}else{

					String query2="select from "+ Resources.class.getName()
							+" where url == '"+request.getServletPath()+"' && status==true";
					List <Resources> rSearch=(List<Resources>) pm.newQuery(query2).execute();
					if(rSearch.isEmpty()){
						error = "Recuros no añadido";
						request.setAttribute("error", error);
						RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
						dp.forward(request, response);
					}
					else{
						String query3 = "select from "+Access.class.getName()
								+" where idRol == "+uSearch.get(0).getIdRole()+" && resource =="+rSearch.get(0).getId()+" && status==true";	
						List <Access> aSearch=(List<Access>) pm.newQuery(query3).execute();		
						if(aSearch.isEmpty()){
							error = "No se registro el acceso";
							request.setAttribute("error", error);
							RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
							dp.forward(request, response);
						}else{
							String queryProductos = "select from "+Producto.class.getName();
							List<Producto> productos = (List<Producto>) pm.newQuery(queryProductos).execute();
							request.setAttribute("productos", productos);
							request.getRequestDispatcher("/WEB-INF/Views/Productos/index.jsp").forward(request, response);
						}
					}

				}
			}
			else{
				String queryProductos = "select from "+Producto.class.getName();
				List<Producto> productos = (List<Producto>) pm.newQuery(queryProductos).execute();
				request.setAttribute("productos", productos);
				request.getRequestDispatcher("/WEB-INF/Views/Productos/index.jsp").forward(request, response);
			}
		}


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
}