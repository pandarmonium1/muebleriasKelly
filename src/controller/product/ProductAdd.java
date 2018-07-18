package controller.product;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pmf.entity.*;
import model.entity.*;
import javax.servlet.*;
import javax.jdo.PersistenceManager;
public class ProductAdd extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PersistenceManager pm = PMF.get().getPersistenceManager();			
		int w,h,p,n,ti;
		boolean hasGlass;
		w=Integer.parseInt(request.getParameter("width"));
		h=Integer.parseInt(request.getParameter("height"));
		p=Integer.parseInt(request.getParameter("price"));
		n=Integer.parseInt(request.getParameter("num"));
		ti=Integer.parseInt(request.getParameter("tipo"));
		hasGlass = Boolean.parseBoolean(request.getParameter("glass"));
		if(ti==1){
			SalaComedor temp = new SalaComedor(h, w, p, hasGlass, n);
			pm.makePersistent(temp);
		}else if(ti==2){
			JuegoDeSala temp = new JuegoDeSala(h, w, p, hasGlass, n);
			pm.makePersistent(temp);
		}else{
			Vitrina temp = new Vitrina(h, w, p, hasGlass, n);
			pm.makePersistent(temp);
		}
		response.sendRedirect("/createP");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

