package controller.clasificaciones;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Clasificacion;
import pmf.entity.PMF;
@SuppressWarnings("serial")
public class ClasificacionesControllerIndex extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " +Clasificacion.class.getName();
		List<Clasificacion> clasificaciones = (List<Clasificacion>) pm.newQuery(query).execute();
		req.setAttribute("clasificaciones", clasificaciones);
		req.getRequestDispatcher("/WEB-INF/Views/Clasificaciones/index.jsp").forward(req, resp);
		pm.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Views/Clasificaciones/index.jsp").forward(req, resp);
	}
}
