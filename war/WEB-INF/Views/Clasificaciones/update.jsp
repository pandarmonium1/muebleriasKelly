<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.Clasificacion" %>
<%@ page import="java.util.List" %>

<%
List<Clasificacion> clasificaciones = (List<Clasificacion>) request.getAttribute("clasificaciones");
 Clasificacion clasificacion = (Clasificacion) request.getAttribute("clasificacion");

%>
<!DOCTYPE html>
<html>
	<head>
		<title>Clasificacion Update</title>
		<meta charset="utf-8">
  		<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>		
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
			<div class="opcion col-md-3 mx-auto p-4">
				<a class="btn btn-success" href="/clasificaciones">Lista de Clasificaciones</a>
			</div>
			<div class="derecha col-md-9">
				<h1 class="text-success border-bottom border-success">Update Clasificaciones</h1><br>
				
				<form class="form-control" action="/clasificaciones/update?clasificacionId=<%=clasificacion.getId() %>" method="post">
		
					<h2>Id <%= clasificacion.getId() %></h2>
					<input class="form-control" type="input" value="<%= clasificacion.getId() %>" name="id" pattern="[A-Za-z0-9 ]*" disabled>
					
					<h3>Nombre:</h3>
					<input class="form-control" type="input" name="name"  pattern="[A-Za-z0-9. ]*" required>
			
					<h3>Padre:</h3>
					<div class="form-group p-3">
						<p><span class="text-danger">*</span></p>
						<select  class="form-control" name="padre">
						<option value="null">----</option>
						<%
							for(int i = 0 ; i<clasificaciones.size(); i++){
								Clasificacion o = (Clasificacion) clasificaciones.get(i);
						%>
								<option value="<%= o.getId()%>"><%=o.getName()%></option>
						<%} %>
						</select>
					 	 </div>
						<input class="btn btn-success" type="submit" name="enviar" value="Enviar">
				</form>
			</div>
		</div>
	</div>
	</body>
</html>