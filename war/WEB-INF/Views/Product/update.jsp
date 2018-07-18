<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  page import="model.entity.*"%>
<%
	Integer ti = (Integer) request.getAttribute("ti");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.in.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

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
					<li class="nav-item"><a class="nav-link" href="">Nosotros</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="">Catalogo</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/roles">Roles</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/users">Usuarios</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/resources">Recursos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/access">Accesos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/product">Productos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/proformas">Proformas</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/facturas">Factura</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/sesion/login">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/sesion/logout">Logout</a>
					</li>
				</ul>
				<div class="form-inline my-2 my-lg-0">
					<a class="btn btn-dark mr-sm-2" href="/login"><i
						class="far fa-user"></i> Mi Cuenta</a>
				</div>
			</div>
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a class="btn btn-success" href="/product">Lista de Productos</a>
			</div>
			<div class="col-md-9">
				<%
					if (ti == 1) {
						SalaComedor a = (SalaComedor) request.getAttribute("obj");
				%>
				<form action="/product/update" method="post">
					<h3>Update Product</h3>
					<p>Alto:</p>
					<input class="form-control form-group" type="text" name="height"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras" id="height"
							value="<%a.getHeigh();%>">
					<p>Ancho:</p>
					<input class="form-control form-group" type="text" name="width"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras"
							value="<%a.getWidth();%>">
					<p>Precio:</p>
					<input class="form-control form-group" type="text" name="price"
							required="" pattern="[0-9]{4,6}"
							title="Ingrese un precio valido de 4 a6 cifras"
							value="<%a.getPrice();%>">
					<p>Vidrio:</p>
					<select class="form-control form-group" name="glass">
								<%
											if (a.isHasGlass()) {
										%>
								<option value="true" selected="selected">Si</option>
								<option value="false">No</option>
								<%
											} else {
										%>
								<option value="true">Si</option>
								<option value="false" selected="selected">No</option>
								<%
											}
										%>
					</select>
					<p>Numero de piezas:</p>
					<input class="form-control form-group" type="text" name="num"
							required="" pattern="[0-9]{1,2}"
							title="Ingrese numero valido, maximo 2 cifras"
							value="<%a.getNumOfChairs();%>">
						<input type="hidden" name="tipo"
							value="<%= ti%>">
					<input class="form-control form-group" type="hidden" name="id"
							value="<%a.getId();%>">
					<input class="btn btn-success" type="submit" value="Enviar" name="enviar">
				</form>		
					
				<%
					} else if (ti == 2) {
						JuegoDeSala a = (JuegoDeSala) request.getAttribute("obj");
				%>
				<form action="/product/update" method="post">
					<h3>Update Product</h3>
					<p>Alto:</p>
					<input class="form-control form-group" type="text" name="height"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras" id="height"
							value="<%a.getHeigh();%>">
					<p>Ancho:</p>
					<input class="form-control form-group" type="text" name="width"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras"
							value="<%a.getWidth();%>">
					<p>Precio:</p>
					<input class="form-control form-group" type="text" name="price"
							required="" pattern="[0-9]{4,6}"
							title="Ingrese un precio valido de 4 a6 cifras"
							value="<%a.getPrice();%>">
					<p>Vidrio:</p>
					<select class="form-control form-group" name="glass">
								<%
											if (a.isHasGlass()) {
										%>
								<option value="true" selected="selected">Si</option>
								<option value="false">No</option>
								<%
											} else {
										%>
								<option value="true">Si</option>
								<option value="false" selected="selected">No</option>
								<%
											}
										%>
					</select>
					<p>Numero de piezas:</p>
					<input class="form-control form-group" type="text" name="num"
							required="" pattern="[0-9]{1,2}"
							title="Ingrese numero valido, maximo 2 cifras"
							value="<%a.getNumOfChairs();%>">
					<input type="hidden" name="tipo"
							value="<%= ti%>">
					<input type="hidden" name="id"
							value="<%a.getId();%>">
					<input class="btn btn-success" type="submit" value="Enviar" name="enviar">
				</form>		
					
				<%
					} else {
						Vitrina a = (Vitrina) request.getAttribute("obj");
				%>
				<form action="/product/update" method="post">
					<h3>Update Product</h3>
					<p>Alto:</p>
					<input class="form-control form-group" type="text" name="height"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras" id="height"
							value="<%a.getHeigh();%>">
					<p>Ancho:</p>
					<input class="form-control form-group" type="text" name="width"
							required="" pattern="[0-9]{1,3}"
							title="Ingrese numero valido en cm, maximo 3 cifras"
							value="<%a.getWidth();%>">
					<p>Precio:</p>
					<input class="form-control form-group" type="text" name="price"
							required="" pattern="[0-9]{4,6}"
							title="Ingrese un precio valido de 4 a6 cifras"
							value="<%a.getPrice();%>">
					<p>Vidrio:</p>
					<select class="form-control form-group" name="glass">
								<%
											if (a.isHasGlass()) {
										%>
								<option value="true" selected="selected">Si</option>
								<option value="false">No</option>
								<%
											} else {
										%>
								<option value="true">Si</option>
								<option value="false" selected="selected">No</option>
								<%
											}
										%>
					</select>
					<p>Numero de piezas:</p>
					<input class="form-control form-group" type="text" name="num"
							required="" pattern="[0-9]{1,2}"
							title="Ingrese numero valido, maximo 2 cifras"
							value="<%=a.getNumOfPieces()%>">
					<input type="hidden" name="tipo"
							value="<%= ti%>">
					<inpu type="hidden" name="id"
							value="<%a.getId();%>">
					<input class="btn btn-success" type="submit" value="Enviar" name="enviar">
				</form>		
					
				<% }%>

			</div>
		</div>
	</div>
</body>
</html>