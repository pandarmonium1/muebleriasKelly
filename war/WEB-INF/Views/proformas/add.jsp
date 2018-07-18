<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.proformas.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.List"%>

<% List<Producto> productos = (List<Producto>)request.getAttribute("productos"); %>
<!DOCTYPE html>
<html>
<head>
<title>Añadir Proformas</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>
function calcular(c){
	var pre = document.getElementById("precio"+c).value;
	var cant = document.getElementById("cant"+c).value;
	document.getElementById("pUTotal"+c).value = (pre*cant);
	}
} 

function seleccionar(c){
	var box = document.getElementById("comprar"+c).checked;
    if (box == false){
       document.getElementById("cant"+c).value = 0;
       document.getElementById("cant"+c).disabled = true;
       document.getElementById("pUTotal"+c).value = 0;
    }else{
       document.getElementById("cant"+c).disabled = false;
    }
}

function sumar1(c){
	var suma = 0.0;
	var numEle=c;
	for (var i=0; i<c; i++){
		if (document.getElementById("comprar"+i).disabled == false){
			var monto = document.getElementById("pUTotal"+i).value;
			suma= parseFloat(suma)+parseFloat(monto);
		}
	}
	document.getElementById("pTotal").value = suma;
}


</script>
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
			<div class="acciones col-md-4 p-4">
				<div class="mx-auto">
					<a class="btn btn-success" href="/proformas">List of Sales</a>
				</div>
			</div>
			<div class="entradas col-md-5 p-4">
				
				<h3>Añadir proforma</h3>
				<form action="/proformas/add" method="post">
					<input type="hidden" name="action" value="crearProforma" />
					<p>No se aceptan caracteres que no sean letras o numeros</p>
					
					<h4>Nombre de la empresa o Persona</h4>
					<input class="form-control" id="name" type="input" name="name" pattern="[A-Za-z0-9 ]*" minlength="3" maxlength="80" required>
					
					<h4>Dirección</h4>
					<input class="form-control" type="input" name="direccion" pattern="[A-Za-z0-9. ]*" minlength="3" maxlength="80" required>
					
					<h4>Teléfono</h4>
					<input class="form-control" type="number" name="telefono" min="100000" max="1000000000">
					
					<h4>Productos (Para seleccionar, haga click en la casilla comprar)</h4>
					<table style="width:80%" border="1">
      	<tr>
    		<th><h2>Producto</h2></th>		
    		<th colspan="3"><h2>Cantidad</h2></th>
    		<th><h2>Monto</h2></th>
  		</tr>
  		<%for(int i=0; i<productos.size();i++){%>
  		<tr>
    		<td><%=productos.get(i).getName() %></td> 	
    		<th colspan="1"></th>
    		<td> S/.<input type="text" id="precio<%=i%>" name="precio<%=i%>" value="<%=productos.get(i).getpPrecio()%>" disabled size="4">
    		 	<input type="number" id="cant<%=i%>" name="cant<%=i%>" min="1" >  
    		    <input type="button" value="total" onclick="calcular(<%=i%>)">
    		 </td>
    		 <td><input type="checkbox" id="comprar<%=i%>" name="<%=i%>" value="compra" onclick="seleccionar(<%=i%>)">Comprar</td>
    		 <td>S/.<input type="text" id="pUTotal<%=i%>" name="pUTotal<%=i%>" value="0" disabled size="4"></td>
    	</tr>
    	<%} %>
    	<tr>
    		<th colspan="3"><h2>Total:</h2></th>
    		<td><input type="button" value="ver total" onclick="sumar1()"></td>
    		<td>S/.<input type="text" id="pTotal" name="pTotal" value="0" size="5"></td>
  		</tr>
      </table><br><br>
				<input class="btn btn-success"	type="submit" value="Submit" id="btsubmit">
				</form>
			</div>
		</div>
	</div>
</body>
</html>