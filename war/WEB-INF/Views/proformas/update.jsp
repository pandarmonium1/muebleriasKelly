<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.proformas.*" %>

<%@ page import="model.entity.*" %>
<%@ page import="java.util.List" %>
<% Proforma r = (Proforma)request.getAttribute("proformas");%>
<%//Aca mandariamos junto con los datos al servlet %>
<!DOCTYPE html>
<html>
<head>
<title>Editar proforma</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.in.css">
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
			<div class="col-md-3">
				<a class="btn btn-success" href="/proformas">Lists of Proforms</a>
			</div>
			<div class="col-md-6">
				<h2>Update</h2>
				<form method="post" action="/proformas/update?proformaId=<%=r.getId() %>">
	
					<input type="hidden" name="action" value="editarProforma"  />
				
					<p>No se aceptan caracteres que no sean letras o numeros</p>
						
							<h3>Nombre de la empresa o persona: <h3>
							<input class="form-control" type="input" value="<%= r.getName() %>" name="name" pattern="[A-Za-z0-9 ]*"required>
						
						
							<h3>Direccion:</h3>
							<input class="form-control" type="input" name="direccion" value="<%= r.getDireccion() %>" pattern="[A-Za-z0-9. ]*" required>
						
							<h3>Teléfono:</h3>
							<input type="number" name="telefono" value="<%=r.getTelefono() %>" min="100000" max="1000000000" >
							<h3>Cantidad de productos:</h3>
							<td><input type="number" name="cantidad" value="<%=r.getCant() %>" min="1" max="15" required></td>
						<input class="btn btn-success"	type="submit" value="Submit" id="btsubmit">
				</form>
			</div>
		</div>
	</div>

</body>
</html>