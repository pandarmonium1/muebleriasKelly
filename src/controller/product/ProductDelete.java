package controller.product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import pmf.entity.*;
import model.entity.*;

public class ProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	public ProductDelete() {
		super();
	}	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PersistenceManager pm = PMF.get().getPersistenceManager();	
		int ti = Integer.parseInt(request.getParameter("ti"));
		try{
			if(ti==1){
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				SalaComedor a = (SalaComedor) pm.getObjectById(Product.class, k);
				pm.deletePersistent(a);
			}else if(ti==2){
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				JuegoDeSala a = (JuegoDeSala) pm.getObjectById(Product.class, k);
				pm.deletePersistent(a);
			}else{
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				Vitrina a = (Vitrina) pm.getObjectById(Product.class, k);
				pm.deletePersistent(a);
			}
			response.sendRedirect("/product");
		}catch (Exception e) {
			response.sendRedirect("/product");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
