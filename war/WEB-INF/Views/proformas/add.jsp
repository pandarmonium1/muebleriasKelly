<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.proformas.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.List"%>
<%@ page import="pmf.entity.*"%>

<%@ page import= "javax.jdo.PersistenceManager" %>
<%@ page import= "javax.servlet.RequestDispatcher"%>
<%@ page import= "javax.servlet.ServletException"%>
<%@ page import= "javax.servlet.http.HttpServlet"%>
<%@ page import= "javax.servlet.http.HttpServletRequest"%>
<%@ page import= "javax.servlet.http.HttpServletResponse"%>
<%@ page import= "com.google.appengine.api.datastore.Key"%>
<%@ page import= "com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import= "com.google.appengine.api.users.UserServiceFactory"%>

<% 
String id= request.getAttribute("clasificacionId");
if(clasificacion!=null){
PersistenceManager pm = PMF.get().getPersistenceManager();
Key k = KeyFactory.createKey(Producto.class.getSimpleName(),proformas.getProductos().get(i));
Producto producto = pm.getObjectById(Producto.class, k);
pm.close(); 
}
List<Producto> productos = (List<Producto>)request.getAttribute("productos"); 
List<Clasificacion> clasificaciones = (List<Clasificacion>)request.getAttribute("clasificaciones"); 
%>
<!DOCTYPE html>
<html>
<head>
<title>Añadir Proformas</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script>	
$("#id_tipo_contacto").on('change', function(){
    $('.formulario').hide();
    $('#' + this.value).show();
});
</script>
</head>
<body>
	<div class="cabeza">
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<div class="container">
				<!-- Brand -->
				<a class="navbar-brand" href="index.html">Muebleria Kelly</a>

				<!-- Links -->
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">

					<!-- Dropdown -->
					<li class="nav-item">
						<a class="nav-link" href="">Nosotros</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="">Catalogo</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/roles">Roles</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/users">Usuarios</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/resources">Recursos</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/access">Accesos</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/productos">Productos</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/proformas">Proformas</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/facturas">Factura</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/sesion/login">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/sesion/logout">Logout</a>
					</li> 
				</ul>
				<div class="form-inline my-2 my-lg-0">
					<a class="btn btn-dark mr-sm-2" href="/login"><i class="far fa-user"></i> Mi Cuenta</a>
				</div>
			</div>
		</nav>
	</div>	
	<div class="container">
		<div class="row">
			<div class="acciones col-md-4 p-4">
				<div class="mx-auto">
					<a class="btn btn-success" href="/proformas">List of Sales</a>
				</div>
			</div>
			<div class="entradas col-md-5 p-4">
				
				<h3>Añadir proforma</h3>
				<form action="/proformas/add" method="post">
					<input type="hidden" name="action" value="crearProforma" />
					<p>No se aceptan caracteres que no sean letras o numeros</p>
					
					<h4>Nombre de la empresa o Persona</h4>
					<input class="form-control" id="name" type="input" name="name" pattern="[A-Za-z0-9 ]*" minlength="3" maxlength="80" required>
					
					<h4>Dirección</h4>
					<input class="form-control" type="input" name="direccion" pattern="[A-Za-z0-9. ]*" minlength="3" maxlength="80" required>
					
					<h4>Teléfono</h4>
					<input class="form-control" type="number" name="telefono" min="100000" max="1000000000">
					
					<h4>Productos</h4>
					<div id="clasificaciones">					
    				<%for(int i=0; i<clasificaciones.size();i++){%>		
    					<div id=clasificacion<%=i%>> 
    					<h4 onclick="muestra_oculta('contenido_a_mostrar')"><%=clasificaciones.get(i).getName() %></h4>
    		 	 	<%
    		 	 	int j=0;
    		 	 	while(j<productos.size()){ 
    		 	 		if(productos.get(j).getClasificacion()==clasificaciones.get(i).getId()){
    		 	 		%> 
    		    		<div id="clasiFinal"<%=i%>>
    		    		<h4>Producto</h4>
    					<h4><%=productos.get(i).getName() %></h4>
    					<h4>Cantidad</h4> 	
    					<input type="number" id="cant<%=i%>" name="cant<%=i%>" min="1" >		
    					<h4>Precio por unidad (S/.) </h4>
    					<input type="text" id="precio<%=i%>" name="precio<%=i%>" value="<%=productos.get(i).getpPrecio()%>" disabled size="4">
    		 	  		</div>
    			<%} j++;
    			} %>
    		 	 		</div>
    		 	 <% 	}%>
    				</div>
    			
				<input class="btn btn-success"	type="submit" value="Submit" id="btsubmit">
				</form>
				</div>
			</div>
					
    				<%for(int i=0; i<productos.size();i++){%>	
    					
    		    <input type="button" value="total" onclick="calcular(<%=i%>)">
    		    </div>
    	<%} %>
			</div>
	</div>
</body>
</html>