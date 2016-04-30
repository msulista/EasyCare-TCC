function mascara(o, f) {
	v_obj = o
	v_fun = f
	setTimeout("execmascara()", 1)
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value)
}

function mensagem(){
	alert("oi");
}

function teste(obj){
	alert('obj.value');
}

function limpaDoubleVazio(obj){
	if(obj.value == '0,00') obj.value = '';
	
}

function retornaPadrao(obj){
	if(obj.value == '') obj.value = '0,00';
}

function moeda(v) {
	v = v.replace(/\D/g, "") // permite digitar apenas numero
	v = v.replace(/(\d{1})(\d{14})$/, "$1.$2") // coloca ponto antes dos
												// ultimos digitos
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 13 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 10 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2") // coloca ponto antes dos
												// ultimos 7 digitos
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2") // coloca virgula antes dos
												// ultimos 4 digitos
	return v;
}