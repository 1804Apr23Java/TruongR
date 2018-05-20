/**
 * 
 */

function sendAjaxGet(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
}

function populateEmployees(xhr) {
	if (xhr.responseText) {
		var res = JSON.parse(xhr.responseText);
		var table = document.getElementById("tableBody");
		table.innerHTML = "";
		var tRow = table.insertRow(-1);
		var cell = tRow.appendChild(document.createElement("th"));
		cell.setAttribute("colspan", 3);
		cell.innerHTML = "Employee Data";
		tRow = table.insertRow(-1);
		cell0 = tRow.insertCell(0);
		cell1 = tRow.insertCell(1);
		cell2 = tRow.insertCell(2);
		cell0.innerHTML = "ID";
		cell1.innerHTML = "Username";
		cell2.innerHTML = "Password";

		for (var i = 0; i < res.length; i++) {
			tRow = table.insertRow(-1);
			cell0 = tRow.insertCell(0);
			cell1 = tRow.insertCell(1);
			cell2 = tRow.insertCell(2);
			cell0.innerHTML = res[i].id;
			cell1.innerHTML = res[i].username;
			cell2.innerHTML = res[i].password;
		}

	} else {
		console.log("No employees found. ???");
	}
}

var button = document.getElementById("allEmployeesSubmit");
button.addEventListener("click", function() {sendAjaxGet(
		"http://localhost:8082/ReimbursementSystem/getAllEmployees",
		populateEmployees)});
