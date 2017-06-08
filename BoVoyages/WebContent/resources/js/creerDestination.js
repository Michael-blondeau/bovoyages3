/*
 * Ajout des tags JS
 */
document.addEventListener("DOMContentLoaded", function(e) {
	document.getElementById("cancel").onclick = retour;
	document.getElementById("cancel").type = button;
})

/*
 * Retour sur la page précédente
 */
function retour(){
	window.location='index.jsp';
}