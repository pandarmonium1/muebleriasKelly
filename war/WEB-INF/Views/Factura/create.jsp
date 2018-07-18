<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head lang="en">
		<title>Add Product (DEMO CRUD)</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/CSS/estilo.css">
		<link rel="stylesheet" href="/CSS/estiloCreate.css">
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
		<div class="create">
			<div class="row">
				<div class="acciones col-md-4">
					<div class="mx-auto">
						<a class="btn btn-success" href="/facturas">List of Sales</a>
					</div>
				</div>
				<div class="entradas col-md-5 p-4">
					<h3>Add Product</h3>
					<form class="form-control" action="/facturas/add" method="post">
						<h4>Product Code</h4>
						<input class="form-control" type="text"  pattern="[A-Za-z]{8}" name="codigoProd" placeholder="Product Code" maxlength="8" required><br>
						
						<h4>Quantity</h4>
						<input class="cantidad form-control" type="number" pattern="[1-9]{1,5}" min="1" max="5000" name="cantidad" placeholder="Quantity" required><br>	
						
						<h4>Description</h4>
						<input class="form-control"  type="text" pattern="[A-Za-z]{3,}" name="descripcion" maxlength="50" placeholder="Description" required><br>
						
						<h4>Product/Unity</h4><br>
						<input class="form-control" type="number" pattern="[0-9]{4,6}" step="0.0001" min="0.0001" name="precioProducto" placeholder="Product/Unity" required><br>
						
						<input class="btn btn-success" type="submit" value="Añadir">
					</form>
				</div>
			</div>
		</div>
	</body>
</html>