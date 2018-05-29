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
		cell.setAttribute("colspan", 10);
		cell.innerHTML = "Employee Data";
		tRow = table.insertRow(-1);
		cell0 = tRow.insertCell(0);
		cell1 = tRow.insertCell(1);
		cell2 = tRow.insertCell(2);
		cell3 = tRow.insertCell(3);
		cell4 = tRow.insertCell(4);
		cell5 = tRow.insertCell(5);
		cell6 = tRow.insertCell(6);
		cell7 = tRow.insertCell(7);
		cell8 = tRow.insertCell(8);
		cell9 = tRow.insertCell(9);
		cell0.innerHTML = "ID";
		cell1.innerHTML = "Username";
		cell2.innerHTML = "First Name";
		cell3.innerHTML = "Last Name";
		cell4.innerHTML = "Address";
		cell5.innerHTML = "City";
		cell6.innerHTML = "State";
		cell7.innerHTML = "ZIP";
		cell8.innerHTML = "Phone"
		cell9.innerHTML = "E-mail"

		for (var i = 0; i < res.length; i++) {
			tRow = table.insertRow(-1);
			cell0 = tRow.insertCell(0);
			cell1 = tRow.insertCell(1);
			cell2 = tRow.insertCell(2);
			cell3 = tRow.insertCell(3);
			cell4 = tRow.insertCell(4);
			cell5 = tRow.insertCell(5);
			cell6 = tRow.insertCell(6);
			cell7 = tRow.insertCell(7);
			cell8 = tRow.insertCell(8);
			cell9 = tRow.insertCell(9);
			cell0.innerHTML = res[i].employeeId;
			cell1.innerHTML = res[i].username;
			cell2.innerHTML = res[i].firstName;
			cell3.innerHTML = res[i].lastName;
			cell4.innerHTML = res[i].address;
			cell5.innerHTML = res[i].city;
			cell6.innerHTML = res[i].state;
			cell7.innerHTML = res[i].zip;
			cell8.innerHTML = res[i].phone;
			cell9.innerHTML = res[i].email;
		}


		var tRow = table.insertRow(-1);
		var cell = tRow.appendChild(document.createElement("td"));
		cell.setAttribute("colspan", 10);
		
		var returnForm = document.createElement("form");
		returnForm.method = "get";
		returnForm.action = "./employeeTable.html"
		var returnButton = document.createElement("button");
		//document.body.appendChild(returnForm);
		returnForm.appendChild(returnButton);
		returnButton.appendChild(document.createTextNode("Return"));
		
		cell.appendChild(returnForm);
		
	} else {
		console.log("No employees found. ???");
	}
}

var button = document.getElementById("allEmployeesSubmit");
button.addEventListener("click", function() {
	sendAjaxGet("http://localhost:8082/ReimbursementSystem/getAllEmployees",
			populateEmployees)
});
