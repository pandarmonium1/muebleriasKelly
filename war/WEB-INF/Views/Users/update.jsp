<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "model.entity.User" %>
<%@ page import = "model.entity.Role" %>
<%@ page import = "java.util.List" %>

<%
	User user = (User) request.getAttribute("user");
	List<Role> roles = (List<Role>) request.getAttribute("roles");
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
	<div class="container">
		<div class="row">
			<div class="col-md-3 p-4">
				<div class="mx-auto">
					<a class="btn btn-success" href="/users">List of Users</a>
				</div>
			</div>
			<div class="col-md-9 p-4">
				<h3>Update <%= user.getId() %></h3>
				<form action="/users/update?userId=<%= user.getId()%>" method="post">
					<p>Role <span class="text-danger">*</span></p>
					<select class="form-control form-group w-25" name="rol">
					<%  
						if(roles.size()>0){
							for(int i=0 ; i<roles.size();i++){
								Role o = (Role) roles.get(i);
					%>
								<option value="<%= o.getId()%>"><%= o.getRoles() %></option>
					<%
							}
						}else{
							
					%>
					<option>Sin roles</option>
					<%
						}
					%>
					</select>
					<p>Email<span class="text-danger">*</span></p>
					<input class="form-control form-group w-50" name="correo" type="email" value="<%= user.getEmail()%>" maxlength="30" placeholder="Example: example@gmail.com" required>
					<p>Género <span class="text-danger">*</span> </p>
					<select class="form-control form-group w-25" name="gender" value="true">
						<option>masculino</option>
						<option>femenino</option>
					</select>
					<input class="btn btn-success form-control w-25" type="submit" name="enviar" value="Actualizar" >
				</form>
			</div>
		</div>
	</div>
	</body>
</html>