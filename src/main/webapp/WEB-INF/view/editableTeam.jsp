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

<script>

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
			if (AlldeptEmployees[emp].id != teamEmployees[empteam].id){
				bakiEmployees.push(AlldeptEmployees[emp]);
				break;
			}
	}
	}	

console.log(teamEmployees);
console.log(AlldeptEmployees);

console.log(bakiEmployees); 
function onSubmitFunc(){
	var idValues="";
	for(let x in teamEmployees){
		idValues+=teamEmployees[x].id+";"
	}
	console.log(idValues);

	$('#hiddenFieldOfTeam').val(idValues);
	
}

function addEmployee(){
	var searchTerm = $('#search-input').val();
	const parts = searchTerm.split('.');
	if(parts.length>=2){
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
			$('#search-input').val("");
			renderTable();
			
	}
			
			
			
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
	});/* 
	console.log(bakiEmployees);
	console.log(teamEmployees); */
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
										 addEmployee();
 

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


<body onload="renderTable()">
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
				<li><a href="dashboard?search=null&pg=1" class="dashboard"><i
						class="material-icons">dashboard</i> <span>Dashboard</span></a></li>


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
					<h4 class="page-title">${team.teamName }</h4>
				</div>
				<div class="row">
					<div class="col-10"></div>
					<div class="col-2">
						<a href="#addTM" data-toggle="modal" aria-expanded="false">
							<button class="btn btn-success" type="submit" id="button-addon2"
								href="#addTM">Assign TM</button>
						</a>
						<!-- 
						<button class="btn btn-success" type="submit" id="button-addon2"
							href="#addTM">Assign TM</button> -->
					</div>
				</div>

			</div>



			<!--------main-content------------->
			<%-- 
			<div class="input-container ic2">
											<label for="department" class="placeholder">Team Manager</label>
											<div class="cut cut-short"></div>
											<select id="teamManager" name="tm"
												class="input required" placeholder="Team Manager">
												<c:forEach var="teammanager" items="${potentialTM }">
												
												<option value="${teammanager.empId}">${teammanager.empId}. ${teammanager.empName }</option>
												</c:forEach>
										</select>
										</div>
										 --%>

			<div class="main-content">
				<div class="row">

					<div class="col-md-12">
						<div class="table-wrapper">
							<div class="table-title">
								<div class="row">
									<div
										class="col-sm-2 p-0 d-flex justify-content-lg-start justify-content-center">
										<h2 class="ml-lg-2">Manage Team</h2>
									</div>
									<div class="col-7"></div>
									<div
										class="col-sm-3 col-md-2  justify-content-lg-end justify-content-center">
										<div class="xp-searchbar">
												<div class="input-group">
													<input type="search" class="form-control"
														placeholder="Add employees here" id="search-input" />
													<button class="btn btn-success" type="submit"
														id="button-addon2" onclick="addEmployee()">Add</button>
												</div>
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
							<div>
									<form
										action="addTeamMember?teamId=${team.teamId }&pg=${pageNo}"
										method="post">
								<center>
										<input type="hidden" name="hiddenFieldOfTeams"
											id="hiddenFieldOfTeam"/>
										<button type="submit" class="btn btn-primary"
											onclick="onSubmitFunc()">Submit</button>
								</center>
								</form>
							</div>
						</div>
					</div>




					<div id="addTM" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="addTeamManager?tid=${team.teamId }&pg=${pageNo }"
									method="post">
									<div class="modal-header">
										<h4 class="modal-title">Add Team Manager</h4>
									</div>
									<div class="modal-body">
										<div class="input-container ic2">
											<label for="department" class="placeholder">Team
												Manager</label>
											<div class="cut cut-short"></div>
											<select id="teamManager" name="teamManagerValues"
												class="input required" placeholder="Team Manager">
												<c:if test="${potentialTM !=null}">
												<c:forEach var="teammanager" items="${potentialTM }">

													<option value="${teammanager.empId}">${teammanager.empId}.
														${teammanager.empName }</option>
												</c:forEach>
												</c:if>
												<c:if test="${potentialTM ==null}">
												
													<option value="0">No employee eligible</option>
												</c:if>
											</select>
										</div>
										<br> <br>
									</div>

									<div class="modal-footer">
										<input type="button" class="btn btn-default"
											data-dismiss="modal" value="Cancel"> <input
											type="submit" class="btn btn-success" value="Assign">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- Edit Modal HTML -->
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