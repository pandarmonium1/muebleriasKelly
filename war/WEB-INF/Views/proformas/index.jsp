<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.proformas.*" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.*" %>
<%
List<Proforma> listas = (List<Proforma>) request.getAttribute("proformas");
%>

<!DOCTYPE html>
<html>
<head>
<title>Proformas</title>
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
				<a class="btn btn-success" href="/proformas/add">Add Proform</a>
			</div>
			<div class="col-md-9">
				<h1>Lista de Proformas</h1>
				<%
					if (listas!=null && listas.size()>0){
				%>
				<table class="table">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Dirección</th>
							<th>Teléfono</th>
							<th>Precio Total</th>
							<th>Acciones Disponibles</th>
						</tr>
					</thead>
					<%
						for ( Proforma c: listas) {
					%>
					<tbody>
					<tr>
						<td><%=c.getName()%></td>
						<td> <%= c.getDireccion() %></td>
						<td><%=c.getTelefono()%></td>
						<td><%=c.gettPrecio()%></td>
						<td>
								<span><a href="/proformas/view?proformaId=<%= c.getId() %>">View</a></span>
								<span><a href="/proformas/delete?proformaId=<%= c.getId() %>">Delete</a></span>
								<span><a href="/proformas/update?proformaId=<%= c.getId() %>">Update</a></span>
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
				
					<h3>No hay proforma</h3>
			
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>