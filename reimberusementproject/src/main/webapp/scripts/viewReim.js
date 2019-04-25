window.onload = function() {
	populateEmployee();
}
	
function populateEmployee() {
		fetch("http://localhost:8084/reimberusementproject/view").then(function(response) {
			return response.json();
		}).then(function(data) {
			if (data.session === null) {
				window.location = "http://localhost:8084/reimberusementproject/profile";
			} else {
				console.log(data);
				//console.log(data.empId);
				
				//document.getElementById("welcome").innerText = "Welcome " + data.firstname + " " + data.lastname;
				//document.getElementById("DOB").innerText = "DOB: " + data.birthdate.monthValue + "/" + data.birthdate.dayOfMonth + "/"+ data.birthdate.year;
				//document.getElementById("DOH").innerText = "DOH: " + data.hiredate.monthValue + "/" + data.hiredate.dayOfMonth + "/"+ data.hiredate.year;
				//document.getElementById("dept").innerText = "Department: " + data.department.deptName;
				//document.getElementById("manager").innerText = "Manager: " + data.department.manager.firstname + " " + data.department.manager.lastname;
				//document.getElementById("email").innerText = "Email: " + data.firstname + "." + data.lastname + "@alchemy";
				//document.getElementById("managerEmail").innerText = "Email: "+ data.department.manager.firstname + "." + data.department.manager.lastname +"@alchemy";
			}
		});
	 }