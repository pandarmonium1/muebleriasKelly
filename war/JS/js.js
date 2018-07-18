function soloNumeros(evento) {
	tecla = (document.all)?evento.keyCode:evento.which;
	if (tecla==8 || tecla==0){
		return true;
	}
	patron = /([0-9\.])/;
	var te = String.fromCharCode(tecla);
	return patron.test(te);
}
function deleteItem(cui) {
	$.get('BillControllerDelete',{
		cuiDel : cui
	}, function(responseText) {
		alert(responseText);
	}
	);
}