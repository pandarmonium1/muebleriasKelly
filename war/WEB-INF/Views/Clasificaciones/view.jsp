<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "model.entity.Clasificacion" %>

<%
Clasificacion clasificacion = (Clasificacion) request.getAttribute("clasificaciones");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Access</title>
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
					<li class="nav-item">
						<a class="nav-link" href="/product">Productos</a>
					</li>
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
			<div class=" col-md-3 p-4"> 
				<div class="">
					<h1 class="text-center text-success font-weight-bold">Actions</h1>
					<ul >
						<li class="btn form-control bg-success mx-auto"><a class="btn btn-success" href="/access/add">Add Access</a></li>
						<li class="btn form-control bg-success"><a class="btn btn-success" href="/access/update?userId=<%= clasificacion.getId() %>">Update</a></li>
						<li class="btn form-control bg-success"><a class="btn btn-success" href="/access/delete?userId=<%= clasificacion.getId() %>">Delete</a></li>
						<li class="btn form-control bg-success"><a class="btn btn-success" href="/access">List of Access</a></li>
					</ul>
				</div>
			</div>
			<div class="table col-md-9 large-9 medium-8 colums p-4"> 
				<h2 class="text-success border-bottom border-success">Clasificacion with Id:  <%= clasificacion.getId() %></h2>
					<table class="vertical-table">
						<tbody>
							<tr>
								<th scope="row">Nombre:</th>
								<td><%=clasificacion.getName()%></td>
							</tr>
							<tr>
								<th scope="row">ID del Padre:</th>
								<td><%=clasificacion.getIdPadre()%></td>
							</tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>
	</body>
</html>