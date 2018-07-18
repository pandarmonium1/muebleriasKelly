package controller.clasificaciones;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.Access;
import model.entity.Clasificacion;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ClasificacionesControllerView extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm= PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Clasificacion.class.getSimpleName(), Long.parseLong(req.getParameter("clasificacionId")));
		Clasificacion us = pm.getObjectById(Clasificacion.class,k);
		req.setAttribute("clasificaciones", us);
		req.getRequestDispatcher("/WEB-INF/Views/Clasificaciones/view.jsp").forward(req, resp);
		pm.close();
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/clasificaciones");
	}
}
