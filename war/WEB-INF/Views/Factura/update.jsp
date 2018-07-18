<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.Facturar" %>
<% Facturar r = (Facturar)request.getAttribute("facturas");%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="/CSS/estilos.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>UPDATE (CRUD DEMO)</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/CSS/estilo.css">
	<script src="/JS/js.js"></script>
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
			<div class="opcion col-md-3">
				<a class="btn btn-success" href="/facturas">Lists of sales</a>
			</div>
			<div class="derecha col-md-6">
				<h1>Update Bill</h1><br>
				<form class="form-control" action="/facturas/update?facturasId=<%=r.getId() %>" method="post">
		
					<h3>Id <%= r.getId() %></h3><br>

					<h3>Product Code</h3>
					<input class="form-control" type="text" name="codigoProd" placeholder="Codigo Producto" value="<%= r.getCodigoPro() %>"  maxlength="8"  required ><br>
		
					<h3>Quantity</h3>
					<input class="form-control" type="number" pattern="[1-9]{1,5}" min="1" max="5000" name="cantidad" value="<%= r.getCantidad() %>" required><br>
		
					<h3>Description</h3>
					<input class="form-control" type="text" name="descripcion" placeholder="Descripcion" value="<%= r.getDescripcion() %>" required ><br>
		
					<h3>Price/Unity</h3>
					<input class="form-control" type="number" pattern="[0-9]{4,6}" step="0.0001" min="0.0001" name="precioProducto" placeholder="Precio Por Unidad" value="<%= r.getPrecioUnidad() %>" required><br>
					<input  class="btn btn-success" type="submit" value="Update"/>
				</form>
	
			</div>
		</div>
	</div>
</body>
</html>