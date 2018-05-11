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
	
	var myEventHandler = function(event) {
		console.log("target:" + event.target.id);
	}
	
	var displayDivs = document.getElementsByClassName("displayDiv");
	
	for (var i = 0; i < displayDivs.length; i++) {
		displayDivs[i].addEventListener("click", myEventHandler);
	}
}