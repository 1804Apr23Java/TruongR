/**
 * 
 */

window.onload = function() {
	document.getElementById("divWithText").addEventListener("mousemove", trackMouse); 
	//last param optional
	function trackMouse(event){
		console.log(event.timeStamp);
		document.getElementById("mouseX").innerHTML = event.clientX;
		document.getElementById("mouseY").innerHTML = event.clientX;
	}
}