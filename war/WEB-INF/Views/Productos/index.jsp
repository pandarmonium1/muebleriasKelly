<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.proformas.*" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Producto" %>
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html>
<head>
<title>Productos</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
		
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
			<div class="col-md-3 p-4">
				<a class="btn btn-success" href="/productos/add">Añadir Producto</a>
			</div>
			<div class="col-md-9">
				<h1>Lista de Productos</h1>
				<%
					if (productos!=null && productos.size()>0){
				%>
				<table class="table">
					<thead>
						<tr>
							<th>ID:</th>
							<th>Nombre</th>
							<th>Precio Unitario</th>
						
						</tr>
					</thead>
					<%
						for ( Producto c: productos) {
					%>
					<tbody>
					<tr>
						<td><%=c.getId()%></td>
						<td><%=c.getName()%></td>
						<td> <%= c.getpPrecio() %>
						<td>
								<span><a href="/productos/view?productoId=<%= c.getId() %>">View</a></span>
								<span><a href="/productos/delete?productoId=<%= c.getId() %>">Delete</a></span>
								<span><a href="/productos/update?productoId=<%= c.getId() %>">Update</a></span>
							</td>
							</tr>
					</tbody>
					<%
						}
					%>
				</table>
				<%
					} else {
				%>
				
					<h3>No hay productos</h3>
			
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>