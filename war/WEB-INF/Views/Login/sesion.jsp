<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<title>Login</title>
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
			<!-- Brand -->
			<a class="navbar-brand" href="index.html">Muebleria Kelly</a>

			<!-- Links -->
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item">
					<a class="nav-link" href="index.html">Inicio</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Nosotros</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Tienda</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contactenos</a>
				</li>
				<!-- Dropdown -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Catalogo</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Muebles</a>
						<a class="dropdown-item" href="#">Sillas</a>
						<a class="dropdown-item" href="#">Mesas</a>
						<a class="dropdown-item" href="#">Comedor</a>
					</div>
				</li>
			</ul>
			<div class="form-inline my-2 my-lg-0">
				<a class="btn btn-dark mr-sm-2" href="/sesion">Iniciar Sesión</a>
			</div>
			<!--  <form class="form-inline my-2 my-lg-0" action="/#">
    			<input class="form-control mr-sm-2" type="text" placeholder="Search">
    			<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
  			</form> -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6 mx-auto p-5 ">
				<div class="formulario">
					<form class="form-control m-1" method="post" action="/sesion/login">
						<h3>Iniciar Sesión</h3>
						<span class="font-italic">con una cuenta existente</span>
						<div class="form-group p-3">
							<p>Usuario:</p>
							<input class="form-control form-group w-75" type="text" name="usuario" pattern="[A-Za-z]{4,16}" placeholder="Ingrese Usuario" required>
							<p >Contraseña:</p>
							<input class="form-control form-group w-75 " type="password" pattern="[A-Za-z]{4,16}" name="contra" placeholder="Ingrese Contraseña" required>	
							<input class="btn btn-success form-control form-group w-50 " type="submit" name="enviar" value="Iniciar Sesión">
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-6 mx-auto border-left p-5">
				<div class="	form-control text-center">
					<h3 class="form-group">Crear cuenta</h3>
					<p class="font-italic">Unete a nuestra franquicia y recibe las mejores promociones</p>
					<a class="btn btn-success m-5" href="registro.jsp">Registrarse</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>