package controller.proformas;

import javax.servlet.http.HttpServlet;
import java.util.*;
import java.io.IOException;  


import javax.jdo.PersistenceManager;

import javax.servlet.*;  
import javax.servlet.http.*;

import pmf.entity.*;
import model.entity.Access;
import model.entity.Clasificacion;
import model.entity.Producto;
import model.entity.Producto2;
import model.entity.Proforma;
import model.entity.Resources;
import model.entity.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ProformasControllerAdd extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error;
		if(uGoogle==null){
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
					error = "recurso no registrado";
					request.setAttribute("error", error);
					RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
					dp.forward(request, response);
				}
				else{
					String query3 = "select from "+Access.class.getName()
							+" where idRol == "+uSearch.get(0).getIdRole()+" && resource== '"+rSearch.get(0).getName()+"' && status==true";	
					List <Access> aSearch=(List<Access>) accesoControlador.newQuery(query3).execute();		
					if(aSearch.isEmpty()){
						error = "no se registro el acceso";
						request.setAttribute("error", error);
						RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/error5.jsp");
						dp.forward(request, response);
					}else{
						PersistenceManager pm=PMF.get().getPersistenceManager();
						String queryProductos = "select from "+Producto.class.getName();
						List<Producto> productos = (List<Producto>) pm.newQuery(queryProductos).execute();
						request.setAttribute("productos", productos);
						PersistenceManager pm2=PMF.get().getPersistenceManager();
						String queryClasificaciones = "select from "+Producto.class.getName();
						List<Clasificacion> clasificaciones = (List<Clasificacion>) pm.newQuery(queryClasificaciones).execute();
						request.setAttribute("productos", productos);
						request.setAttribute("clasificaciones", clasificaciones);
						request.getRequestDispatcher("/WEB-INF/Views/proformas/add.jsp").forward(request, response);
					}

				}
			}
		}



	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)   
			throws ServletException, IOException {


		boolean existe = false;
		String res = request.getParameter("name");
		if(res!=null){ 


			String name=request.getParameter("name");  
			String direc=request.getParameter("direccion");
			String telefono=request.getParameter("telefono");
			
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();

			String query = "select  from " + Proforma.class.getName();
			List<Proforma> listas = (List<Proforma>) pm.newQuery(query).execute();
			
			for(Proforma c : listas){
				if(c.getName().equals(name))
					existe=true;
			}
			
			

			if(!existe){
				
				Proforma nuevoP= new Proforma(name, direc, telefono);
				try{
					pm.makePersistent(nuevoP);
					pm.close();
					
				}
				finally {
					PersistenceManager pm2=PMF.get().getPersistenceManager();
					String queryProductos = "select from "+Producto.class.getName();
					List<Producto> productos = (List<Producto>) pm2.newQuery(queryProductos).execute();
					pm2.close();
					double suma=0;
					int cant=0;
					int para=0;
					
					while(cant<productos.size()){
						
						para=Integer.parseInt(request.getParameter("cant"+cant));
						Producto pAux= productos.get(cant);
						suma=suma+(para*productos.get(cant).getpPrecio());	
						
						PersistenceManager pm3 = PMF.get().getPersistenceManager();
						
						Key k = KeyFactory.createKey(Producto.class.getSimpleName(), new Long(pAux.getId()));
						Producto r = pm3.getObjectById(Producto.class, k);
						r.setCant(para);
						r.setpUTotal(para*r.getpPrecio());
						pm3.close();
						
						PersistenceManager pm4 = PMF.get().getPersistenceManager();
						Key k2 = KeyFactory.createKey(Proforma.class.getSimpleName(), new Long(nuevoP.getId()));
						Proforma r2 = pm4.getObjectById(Proforma.class, k2);
						r2.getProductos().add(pAux.getId());
						pm4.close();
						//pAux.setCant(para);
						//pAux.setpUTotal(para*productos.get(cant).getpPrecio());
						//productos.add(pAux);
						cant++;
					}
					PersistenceManager pm4 = PMF.get().getPersistenceManager();
					Key k2 = KeyFactory.createKey(Proforma.class.getSimpleName(), new Long(nuevoP.getId()));
					Proforma r2 = pm4.getObjectById(Proforma.class, k2);
					r2.settPrecio(suma);
					pm4.close();
					response.sendRedirect("/proformas");

				}


				
				
				
			}

		}

	}
}

