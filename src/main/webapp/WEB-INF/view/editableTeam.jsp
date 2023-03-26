<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>ADMIN DASHBOARD</title>
<link rel="stylesheet"
	href="../resources/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../resources/custom/css/admin-dashboard/custom.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Material+Icons"
	rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<c:set var="pageNo" value="${pageNo}" />
<c:set var="pageCount" value="${pageCount}" />

<script >


var teamEmployees = [
	<c:forEach var="employee" items="${team.employees}">
		{
			id : ${employee.empId},
			name : "${employee.empName}"
		},
	</c:forEach>
];
var AlldeptEmployees = [
	<c:forEach var="employee" items="${employees}">
		{
			id : ${employee.empId},
			name : "${employee.empName}"
		},
	</c:forEach>
];
var bakiEmployees = [];
	for(let emp in AlldeptEmployees){/* 
		console.log(AlldeptEmployees[emp]); */
		if (teamEmployees.length == 0){
			bakiEmployees.push(AlldeptEmployees[emp]);
		}
	for(let empteam in teamEmployees){
			if (emp.id != empteam.id){
				bakiEmployees.push(AlldeptEmployees[emp]);
			}
	}
	}	
/* 
console.log(teamEmployees);
console.log(AlldeptEmployees);

console.log(bakiEmployees); */


function addEmployee(){
	var searchTerm = $('#search-input').val();
	const parts = searchTerm.split('.');
	var employeeIdValue=parseInt(parts[0]);

	var employeeNameValue=parts[1];/* 
	console.log(parts); */
	teamEmployees.push({
		id : employeeIdValue,
		name : employeeNameValue});
	
	bakiEmployees = bakiEmployees.filter(employee => employee.id != parts[0]);


		/* var AlldeptEmployees = [
			<c:forEach var="employee" items="${employees}">
				{
					id : ${employee.empId},
					name : "${employee.empName}"
				},
			</c:forEach>
		];
		var bakiEmployees = [];
			for(let emp in AlldeptEmployees){/* 
				console.log(AlldeptEmployees[emp]); */
				/* if (teamEmployees.length == 0){
					bakiEmployees.push(AlldeptEmployees[emp]);
				}
			for(let empteam in teamEmployees){
					if (emp.id != empteam.id){
						bakiEmployees.push(AlldeptEmployees[emp]);
					}
			}
			}	 */ 
			console.log(bakiEmployees); 
			$('#search-input').val("");
			renderTable();
			
			
			
			
			
}

function renderTable(){
	let temp = "";
	for(let emp in teamEmployees){
		temp += "<tr><td>"+teamEmployees[emp].id+"</td><td>"+teamEmployees[emp].name+"</td><td><a class='delete' onclick='removeTableRow("+teamEmployees[emp].id+")'><i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a></td></tr>";
	}
	document.getElementById("teamTable").innerHTML = temp;
}

function removeTableRow(eid){
	var employeename;
	for(let emp in teamEmployees){
		if(teamEmployees[emp].id == eid){
			employeename = teamEmployees[emp].name;
		}
	}
	teamEmployees = teamEmployees.filter(employee => employee.id != eid);
	bakiEmployees.push({
		id : eid,
		name : employeename
	});
	renderTable();
}

function addRow(id,name) {
	// Get the table body element
	const tableBody = document.getElementById("teamTable");

	// Create a new row element
	const newRow = document.createElement("tr");

	// Create cell elements for the new row
	const idCell = document.createElement("td");
	const nameCell = document.createElement("td");
	const actionCell = document.createElement("td");

	// Set the text content of the cell elements
	idCell.textContent = id;
	nameCell.textContent = name;
	actionCell.innerHTML = '<a href="" onclick="deleteRow('+id+')"><i class="material-icons" data-toggle="tooltip" title="Delete">delete</i></a>';

	// Append the cell elements to the new row
	newRow.appendChild(idCell);
	newRow.appendChild(nameCell);
	newRow.appendChild(actionCell);

	// Append the new row to the table body
	tableBody.appendChild(newRow);

	}


						$(document).ready(function () {
							/* console.log(AlldeptEmployees); */
							// Get the list of items from the model attribute
							 /* var items = [
								<c:forEach var="item" items="${employees}">
									"${item.empName}",
								</c:forEach>
							]; */

							// Set the initial form action
							/*  var initialUrl = window.location.href.split('?')[0];
						   $('#search-form').attr('action', initialUrl + '?search=null&pg=1');
							*/

							// Listen for the keyup event on the search input field
							$('#search-input').on('keyup', function () {
								// Get the value of the search input field
								var searchTerm = $(this).val().toLowerCase();

								// Filter the list based on the search term
								var filteredItems = bakiEmployees.filter(function (item) {
									return item.name.toLowerCase().indexOf(searchTerm) > -1;
								});

								// Update the search results list
								var $searchResults = $('#search-results');
								$searchResults.empty();
								filteredItems.forEach(function (item) {
									var $li = $('<li>').text(item.id +". " + item.name);
									$li.on('click', function () {
										$('#search-input').val($(this).text());
										 $searchResults.hide();
 

									});
									$searchResults.append($li);
								});

								// Show/hide the search results list
								if (searchTerm.length > 0) {
									$searchResults.show();
								} else {
									$searchResults.hide();
								}
							});

							// Hide the search results list on document click
							$(document).on('click', function (event) {
								var $searchResults = $('#search-results');
								if (!$(event.target).closest('.xp-searchbar').length) {
									$searchResults.hide();
								}
							});

							// Update form action when GO button is clicked
							$('#button-addon2').on('click', function (event) {
								event.preventDefault();
								/* updateFormAction(); */
							});

							function updateFormAction() {
								var searchTerm = $('#search-input').val();
								if (searchTerm.trim() === '') {
									searchTerm = 'null';
								}
								var url = 'http://localhost:8081/admin/dashboard?search=null'; // replace with your URL
								var newUrl = url.replace('search=null', 'search=' + encodeURIComponent(searchTerm) + '&pg=1');
								window.location.href = newUrl;
							}



						});
					</script>


</head>


<body>
	<!-- <div>${result.getBody().getStatus()}</div> -->

	<div class="wrapper">
		<div class="body-overlay" />
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>
					<img src="../resources/assets/logo.png" class="img-fluid" /><span>NRI
						Fintech</span>
				</h3>
			</div>
			<ul class="list-unstyled components">
				<li ><a href="dashboard?search=null&pg=1"
					class="dashboard"><i class="material-icons">dashboard</i> <span>Dashboard</span></a></li>


				<li><a href="projectallocation?pg=1"> <i
						class="material-icons">laptop</i>Project
				</a></li>
				<li class="active"><a href="teamallocation?pg=1"> <i
						class="material-icons">groups</i>Team
				</a></li>
				<!-- <li><a href="departmentallocation?pg=1"> <i
						class="material-icons">work</i>Department
				</a></li> -->
				<li><a href="leaveApproval?pg=1"> <i class="material-icons">playlist_add_check</i>Leave
						Approval
				</a></li>
				<li><a href="resignationApproval?pg=1"> <i
						class="material-icons">directions_walk</i>Resignation approval
				</a></li>
				<li><a href="analytics"> <i class="material-icons">analytics</i>Analytics
				</a></li>
				<li><a href="#adminprofile" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">account_circle</i>Profile
				</a></li>


			</ul>


		</nav>
		<%-- <div id="project" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<form action="proAlloc" method="post">
										<div class="modal-header">
											<h4 class="modal-title">PROJECT ALLOCATION</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">
											<div class="form-group">
												<label>Team Name</label> <input type="text" class="form-control"
													pattern="" required>
											</div>
											<div class="form-group">
												<label>Project Name</label> <input type="text" class="form-control"
													required>
											</div>

										</div>
										<div class="modal-footer">
											<input type="button" class="btn btn-default" data-dismiss="modal"
												value="Cancel"> <input type="submit" class="btn btn-success"
												value="Add">
										</div>
									</form>
								</div>
							</div>
					</div>

					<div id="team" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="teamAlloc" method="post">
									<div class="modal-header">
										<h4 class="modal-title">TEAM ALLOCATION</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>Employee Name</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Team Name</label> <input type="text" class="form-control" required>
										</div>

									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal"
											value="Cancel">
										<input type="submit" class="btn btn-success" value="Add">
									</div>
								</form>
							</div>
						</div>
					</div>

					<div id="department" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="deptAlloc" method="post">
									<div class="modal-header">
										<h4 class="modal-title">DEPARTMENT ALLOCATION</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>Employee Name</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Department Name</label> <input type="text" class="form-control"
												required>
										</div>

									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal"
											value="Cancel">
										<input type="submit" class="btn btn-success" value="Add">
									</div>
								</form>
							</div>
						</div>
					</div>

					<div id="hike" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="hike" method="post">
									<div class="modal-header">
										<h4 class="modal-title">APPRAISAL</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>Employee Name</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Hike</label> <input type="text" class="form-control" required>
										</div>
										<div class="form-group">
											<label>Hike Date</label> <input type="date" class="form-control" required>
										</div>

									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal"
											value="Cancel">
										<input type="submit" class="btn btn-success" value="Add">
									</div>
								</form>
							</div>
						</div>
					</div> --%>

		<div id="adminprofile" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">ADMIN PROFILE</h4>

					</div>
					<div class="modal-body">
						<form>
							<ul>
								<li>
									<p>
										<b>Admin id:</b> ${admin.adminId}
									</p>
								</li>
								<li>
									<p>
										<b>Admin name:</b> ${admin.adminName}
									</p>
								</li>
								<li>
									<p>
										<b>Admin email:</b> ${admin.adminEmail}
									</p>
								</li>
							</ul>
					</div>

					<div class="modal-footer">
						<input type="button" class="btn btn-secondary"
							data-dismiss="modal" value="Close">

					</div>
					</form>
				</div>
			</div>
		</div>






		<!--------page-content---------------->

		<div id="content">

			<!--top--navbar----design--------->

			<div class="top-navbar">
				<div class="xp-topbar">

					<!-- Start XP Row -->
					<div class="row justify-content-end">
						<!-- Start XP Col -->
						<!-- <div
							class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
							<div class="xp-menubar">
								<span class="material-icons text-white">signal_cellular_alt
								</span>
							</div>
						</div> -->
						<!-- End XP Col -->

						<!-- Start XP Col -->
						


						<!-- End XP Col -->

						<!-- Start XP Col -->
						<div class="col-md-3 col-lg-2 order-1 order-md-3 ">
							<div class="xp-profilebar text-right" align="right">
								<nav class="navbar p-0">
									<ul class="nav navbar-nav flex-row ml-auto">
										<li class="align-right"><a href="../logout"
											class="nav-link"><span class="material-icons">logout</span>
												Logout</a></li>

									</ul>


								</nav>

							</div>
						</div>
						<!-- End XP Col -->

					</div>
					<!-- End XP Row -->

				</div>
				<div class="xp-breadcrumbbar text-center">
					<h4 class="page-title">Manage Team</h4>
					<!--  <ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Booster</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol>-->
				</div>

			</div>



			<!--------main-content------------->

			<div class="main-content">
				<div class="row">

					<div class="col-md-12">
						<div class="table-wrapper">
							<div class="table-title">
								<div class="row">
									<div
										class="col-sm-2 p-0 d-flex justify-content-lg-start justify-content-center">
										<h2 class="ml-lg-2">Manage Employees</h2>
									</div>
									<div class="col-7"></div>
									<div class="col-sm-3 col-md-2  justify-content-lg-end justify-content-center">
							<div class="xp-searchbar">
								<form id="search-form" action="" method="get">
									<input type="hidden" name="search" value="">
									<div class="input-group">
										<input type="search" class="form-control" placeholder="Add employees here"
											id="search-input">
										<div class="input-group-append ">
											<button class="btn" type="submit" id="button-addon2" onclick="addEmployee()">GO</button>
										</div>
									</div>
								</form>
								<ul class="dropdown-menu" id="search-results"
									style="display: none;">
								</ul>
							</div>
						</div>
									<!-- <div
										class="col-sm-2 p-0 d-flex justify-content-lg-end justify-content-center">
										<a href="#addEmployeeModal" class="btn btn-success"
											data-toggle="modal" data-target="#addEmployeeModal"> <i
											class="material-icons">&#xE147;</i> <span>Add New
												Employee</span>
										</a>
										 <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
		  <i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
									</div> -->
								</div>
							</div>
							<table class="table table-striped table-hover">
								<thead>
									<tr>

										<th>ID</th>
										<th>NAME</th>
										<th style="width: 140px;">ACTIONS</th>
									</tr>
								</thead>
								<tbody id="teamTable">
									<%-- <tr>

										<c:forEach items="${teamEmployees}" var="emp">
											<tr>
												<td><c:out value="${emp.empId}" /></td>
												<td><c:out value="${emp.empName}" /></td>
												
																		<td><a href="#deleteEmployeeModal"
																				class="delete" data-toggle="modal"
																				onClick="removeTableRow(this)">
																				<i class="material-icons"
																					data-toggle="tooltip"
																					title="Delete">&#xE872;</i>
																			</a></td>

											</tr>
											
										</c:forEach>

</tr> --%>

							
								</tbody>
							</table>

							<div class="clearfix">
								<div class="hint-text">
									Total number of entries <b>${empCount}</b><br> Showing
									page <b>${pageNo}</b> of <b>${pageCount eq 0 ? 1 : pageCount}</b>

								</div>
								<ul class="pagination">

									<c:if test="${ pageNo > 1}">
										<li class="page-item"><a
											href="?search=${search}&pg=${pageNo-1}" class="page-link">Previous</a>
										</li>
									</c:if>
									<c:if test="${ pageNo < pageCount}">
										<li class="page-item"><a
											href="?search=${search}&pg=${pageNo+1}" class="page-link">Next</a>
										</li>
									</c:if>


								</ul>
							</div>
						</div>
					</div>
					<!-- Edit Modal HTML -->
					<div class="modal fade" id="addEmployeeModal" tabindex="-1"
						role="dialog" aria-labelledby="addEmployeeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<h5 class="modal-title" id="addEmployeeModalLabel">Add New
										Employee</h5>
									<!-- <button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button> -->
								</div>
								<div class="modal-body">
									<form action="addUser" method="post" modelAttribute="newuser"
										onsubmit="return validateForm()">
										<div class="input-container ic1">
											<label for="name" class="placeholder">Name</label>
											<div class="cut"></div>
											<input id="name" name="name" class="input required"
												type="text" placeholder=" " required
												pattern="^[a-zA-Z_][[\\s]a-zA-Z_]*"
												$"
												oninvalid="setCustomValidity('Name can only contain alphabetic characters, underscores, and one whitespace between each word')"
												onchange="setCustomValidity('')" />
										</div>



										<div class="input-container ic2">
											<label for="email" class="placeholder">Personal Email</label>
											<div class="cut cut-short"></div>
											<input id="email" name="personalEmail" class="input required"
												type="email" placeholder=" " required /> <span
												id="email-error" class="error-text"></span>
										</div>
										<div class="input-container ic3">
											<label for="gender" class="placeholder">Gender</label>
											<div class="cut cut-short"></div>
											<div class="wrapper-class">
												<input id="gender-male" name="gender" class="input required"
													type="radio" value="male" required /><label
													for="gender-male">Male</label> <input id="gender-female"
													name="gender" class="input" type="radio" value="female"
													required /><label for="gender-female">Female</label> <input
													id="gender-other" name="gender" class="input" type="radio"
													value="other" required /><label for="gender-other">Other</label>
											</div>
										</div>
										<div class="input-container ic2">
											<label for="designation" class="placeholder">Designation</label>
											<div class="cut cut-short"></div>
											<input id="designation" name="designation" class="input"
												type="text" placeholder=" " />
										</div>
										<div class="input-container ic2">
											<label for="department" class="placeholder">Department</label>
											<div class="cut cut-short"></div>
											<select id="department" name="department"
												class="input required" placeholder="Deparment">
												<option value="trainee">Trainee</option>
												<option value="hr">Hr</option>
												<option value="administration">Administration</option>
												<option value="accounts">Accounts</option>
												<option value="sports">Sports</option>
												<option value="security">Security</option>
												<option value="others">Others</option>
												
											</select>
										</div>
										<div class="input-container ic2">
											<label for="experience" class="placeholder">Experience (in years)</label>
											<div class="cut cut-short"></div>
											<input id="experience" name="yearOfExperience" class="input"
												type="number" placeholder=" " required/>
										</div>
										<div class="input-container ic2">
											<label for="gradelevel" class="placeholder">Grade
												Level</label>
											<div class="cut cut-short"></div>
											<select id="gradelevel" name="gradeLevel"
												class="input required" required>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
											</select>
										</div>
										<div class="input-container ic2">
											<label for="dateofjoining" class="placeholder">Date
												of Joining</label>
											<div class="cut cut-short"></div>
											<input id="dateofjoining" name="doj" class="input required"
												type="date" placeholder=" " required />
										</div>
										<div class="input-container ic2">
											<label for="employeetype" class="placeholder">Employee
												Type</label>
											<div class="cut cut-short"></div>
											<select id="employeetype" name="emptype"
												class="input required" required>
												<option value="full time">Full Time</option>
												<option value="part time">Part Time</option>
												<option value="contract">Contract</option>
											</select>
										</div>

										<div class="input-container ic2">
											<label for="username" class="placeholder">Username</label>
											<div class="cut"></div>
											<input id="username" name="username" class="input required"
												type="text" placeholder=" " required /> <span
												id="username-error" class="error-text"></span>
										</div>
										<div class="input-container ic2">
											<label for="password" class="placeholder">Password</label>
											<div class="cut"></div>
											<input id="password" name="password" class="input required"
												type="password" placeholder=" "
												pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{8,}$"
												oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Password must be of 8 characters and contain at least one capital character, one number, and a special character.')"
												required />
										</div>
										<br>

										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary">Submit</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>



					<%-- <!-- Edit Modal HTML -->
									<div id="editEmployeeModal" class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<form>
													<div class="modal-header">
														<h4 class="modal-title">Edit Employee</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
													</div>
													<div class="modal-body">
														<div class="form-group">
															<label>Name</label> <input type="text" class="form-control"
																required>
														</div>
														<div class="form-group">
															<label>Email</label> <input type="email"
																class="form-control" required>
														</div>
														<div class="form-group">
															<label>Address</label>
															<textarea class="form-control" required></textarea>
														</div>
														<div class="form-group">
															<label>Phone</label> <input type="text" class="form-control"
																required>
														</div>
													</div>
													<div class="modal-footer">
														<input type="button" class="btn btn-default"
															data-dismiss="modal" value="Cancel"> <input type="submit"
															class="btn btn-info" value="Save">
													</div>
												</form>
											</div>
										</div>
									</div> --%>


					<!-- <!-- Delete Modal HTML -->
					<div id="deleteEmployeeModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">

								<form:form action="editStatus" method="post"
									id="status-modal-form" modelAttribute="employee">

									<div class="modal-header">
										<h4 class="modal-title">Edit Status</h4>
										<!-- <button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button> -->
									</div>
									<div class="modal-body">

										<!-- <p>Edit status for this employee?</p> -->

										<p>
											Edit status of <span id="empstatusname"></span> ?
										</p>


									</div>
									<input type="hidden" name="empId" id="empIdStatus" />
									<div class="modal-footer">

										<!-- <input type="submit" class="btn btn-danger" value="Inactive">
										<input type="submit" class="btn btn-primary" value="Active">
 -->
										<!-- <input type="submit" class="btn btn-primary" value="Active" id = "activate"> 
										<input type="submit" class="btn btn-danger" value="Inactive" id = "deactivate"> -->
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<input type="submit" id="changestatusbtn" />


									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>



				<!---footer---->


			</div>

			<footer class="footer">
				<div class="container-fluid">
					<div class="footer-in">
						<p class="mb-0">NRI FinTech - All Rights Reserved.</p>
					</div>
				</div>
			</footer>
		</div>
	</div>


	<!----------html code complete----------->








	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

</body>
<script src="../resources/lib/jquery/jquery-3.3.1.min.js"
	type="text/javascript" />
<script src="../resources/lib/jquery/jquery-3.3.1.slim.min.js"
	type="text/javascript" />
<script src="../resources/custom/js/admin-dashboard/navtoggle.js"
	type="text/javascript" />
<script src="../resources/lib/popper/popper.min.js"
	type="text/javascript" />
<script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js"
	type="text/javascript" />


<script type="text/javascript">

					$(document).ready(function () {
						$(".xp-menubar").on('click', function () {
							$('#sidebar').toggleClass('active');
							$('#content').toggleClass('active');
						});

						$(".xp-menubar,.body-overlay").on('click', function () {
							$('#sidebar,.body-overlay').toggleClass('show-nav');
						});

					});

				</script>

</html>