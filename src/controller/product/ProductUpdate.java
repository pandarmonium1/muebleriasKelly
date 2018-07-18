package controller.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import pmf.entity.*;
import model.entity.*;
import javax.servlet.*;
import javax.jdo.PersistenceManager;

public class ProductUpdate extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		int num = Integer.parseInt(request.getParameter("Num"));
		int ti = Integer.parseInt(request.getParameter("ti"));
		request.setAttribute("Num", num);
		request.setAttribute("ti", ti);
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/update.jsp"); 		
		try{
			if(ti==1){
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				SalaComedor a = (SalaComedor) pm.getObjectById(Product.class, k);
				request.setAttribute("obj", a);
				dispatcher.forward(request, response); 	
			}else if(ti==2){
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				JuegoDeSala a = (JuegoDeSala) pm.getObjectById(Product.class, k);
				request.setAttribute("obj", a);
				dispatcher.forward(request, response); 	
			}else{
				Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("ProductId")));
				Vitrina a = (Vitrina) pm.getObjectById(Product.class, k);
				request.setAttribute("obj", a);
				dispatcher.forward(request, response); 	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("id")));
			SalaComedor a = (SalaComedor) pm.getObjectById(Product.class, k);
			a.setHeigh(h);
			a.setWidth(w);
			a.setNumOfChairs(n);
			a.setPrice(p);
			a.setHasGlass(hasGlass);
		}else if(ti==2){
			Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("id")));
			JuegoDeSala a = (JuegoDeSala) pm.getObjectById(Product.class, k);
			a.setHeigh(h);
			a.setWidth(w);
			a.setNumOfChairs(n);
			a.setPrice(p);
			a.setHasGlass(hasGlass);
		}else{
			Key k =	KeyFactory.createKey(Product.class.getSimpleName(), Long.parseLong(request.getParameter("id")));
			Vitrina a = (Vitrina) pm.getObjectById(Product.class, k);
			a.setHeigh(h);
			a.setWidth(w);
			a.setNumOfPieces(n);
			a.setPrice(p);
			a.setHasGlass(hasGlass);
		}
		response.sendRedirect("/index");
	}

}
