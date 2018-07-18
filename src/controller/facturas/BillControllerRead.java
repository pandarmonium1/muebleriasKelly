package controller.facturas;
import model.entity.Facturar;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import pmf.entity.PMF;

@SuppressWarnings("serial")
public class BillControllerRead extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("../facturas");
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Facturar.class.getSimpleName(), Long.parseLong(req.getParameter("facturasId")));
		Facturar r = pm.getObjectById(Facturar.class, k);
		req.setAttribute("facturas", r);
		req.getRequestDispatcher("/WEB-INF/Views/Factura/read.jsp").forward(req, resp);
		pm.close();
		
	}
}
