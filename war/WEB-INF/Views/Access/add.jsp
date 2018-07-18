<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Role" %>
<%@ page import="model.entity.Resources" %>

<%
	List<Role> roles = (List<Role>) request.getAttribute("roles");
	List<Resources> resource = (List<Resources>)  request.getAttribute("resource");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Usuarios</title>
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
						<a class="nav-link" href="/product">Productos</a>
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
	<div class="container"">
		<div class="row">
			<div class="col-md-3 p-4">
				<div class="mx-auto">
					<a class="btn btn-success" href="/access">List of Access</a>
				</div>
			</div>
			<div class="col-md-9 p-3">
				<form class="form-control" action="/access/add" method="post">
					<div class="form-group p-3">
						<p>Rol<span class="text-danger">*</span></p>
						<select  class="form-control" name="rol">
						<%
							for(int i = 0 ; i<roles.size(); i++){
								Role o = (Role) roles.get(i);
						%>
								<option value="<%= o.getId()%>"><%=o.getRoles() %></option>
						<%} %>
						</select>
					</div>	
					<div class="form-group p-3">
						<p>Resource<span class="text-danger">*</span></p>
						<select class="form-control" name="resource">
						<%
							for(int i= 0 ; i<resource.size();i++){
								Resources o = (Resources) resource.get(i);
						%>
								<option><%= o.getName()%></option>
						<%
							}
						%>
						</select>
					</div>
					<input class="btn btn-success" type="submit" name="enviar" value="Enviar">
				</form>
			</div>
		</div>
	</div>
	</body>
</html>