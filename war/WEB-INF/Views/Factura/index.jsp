<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List"  %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "model.entity.Facturar" %>

<%
	Facturar facturas = (Facturar)request.getAttribute("facturas");
	List<Facturar> listas = (List<Facturar>) request.getAttribute("listas");
	SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
%>


<!DOCTYPE html>
<html lang="en">
	<head>
		<title>CRUD DEMO</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/CSS/estilo.css">
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
					<a class="btn btn-success" href="/facturas/add">Add New Product</a>	
				</div>
			</div>
		<div class="vista col-md-9">
		
		<h1>Factura</h1>
		
		<%
		if (!listas.isEmpty() && listas.size() > 0) {
		%>
		<table class="table">
			<thead>
				<tr>
					<th>n°</th>
					<th>Codigo Producto</th>
					<th>Cantidad</th>
					<th>Descripción</th>
					<th>Precio/unidad</th>
					<th>total</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<% 	
				float sumTotal=0;
				for (int i= 0; i<listas.size() ; i++){
					Facturar o = (Facturar) listas.get(i);
			%>
			<tbody>
				<tr>
					<td><%= i+1%></td>
					<td><%= o.getCodigoPro()%></td>
					<td><%= o.getCantidad()%></td>
					<td><%= o.getDescripcion()%></td>
					<td><%= o.getPrecioUnidad()%></td>
					<td><%= o.getTotal()%></td>
					<td>
						<span><a href="/facturas/view?facturasId=<%= o.getId() %>" >View</a></span>
						<span><a href="/facturas/delete?facturasId=<%= o.getId() %>" >Delete</a></span>
						<span><a href="/facturas/update?facturasId=<%= o.getId() %>">Update</a></span>
					</td>
				</tr>
			</tbody>
			<%
				sumTotal = sumTotal+o.getTotal();
				}
			%>
		</table>
		<div class="muestraTotal">
			<h2>Importe Total: <%= sumTotal %></h2>
		</div>		
		<%
			} else{
		%>
		<h4>Sin productos para la venta</h4>		
		<% 
			}
		%>
		</div>
		</div>
	</div>
	</body>
</html>