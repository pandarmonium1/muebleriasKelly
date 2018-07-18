package controller.proformas;
import java.io.IOException;
import pmf.entity.*;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.*;

@SuppressWarnings("serial")
public class ProformasControllerView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		com.google.appengine.api.users.User uGoogle =UserServiceFactory.getUserService().getCurrentUser();
		String error ;
		if(uGoogle==null){
			error = "necesita iniciar sesion";
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
				accesoControlador.close();

				PersistenceManager pm = PMF.get().getPersistenceManager();
				Key k = KeyFactory.createKey(Proforma.class.getSimpleName(), Long.parseLong(request.getParameter("proformaId")));
				Proforma proformas = pm.getObjectById(Proforma.class, k);
				request.setAttribute("proformas", proformas);
				request.getRequestDispatcher("/WEB-INF/Views/proformas/read.jsp").forward(request, response);
				pm.close();
			}
		}
	}

}