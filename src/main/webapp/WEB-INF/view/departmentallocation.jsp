<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="utf-8">
				<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
				<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
				<title>ADMIN DASHBOARD</title>
				<link rel="stylesheet" href="../resources/lib/bootstrap/css/bootstrap.min.css">
				<link rel="stylesheet" href="../resources/custom/css/admin-dashboard/custom.css">
				<link rel="preconnect" href="https://fonts.googleapis.com">
				<link rel="preconnect" href="https://fonts.gstatic.com">
				<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
					rel="stylesheet">
				<link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">

				<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
				<c:set var="pageNo" value="${pageNo}" />
				<c:set var="pageCount" value="${countPages}" />

				<script>
					document.addEventListener("DOMContentLoaded", function () {
						// Get employee names and ids from model attribute using JSTL
						var employees = [
							<c:forEach var="employee" items="${allemployeenames}">
								{
									id: "${employee.empId}",
								name: "${employee.empName}"
        },
							</c:forEach>
						];

						// Get department names from model attribute using JSTL
						var listdepartments = [
							<c:forEach var="department" items="${listdepartments}">
								"${department.hod.empName}",
							</c:forEach>
						];

						// Create a list to store selected employee names
						var selectedEmployees = [];

						// Get references to DOM elements
						var empList = document.getElementById("empList");
						var suggestionList = document.getElementById("suggestions");



						// Function to render the list of selected employees
						function renderSelectedEmployees() {
							var names = selectedEmployees.map(function (employee) {
								return employee.name;
							});
							empList.value = names.join(", ");
						}




						// Function to filter employee names based on input text
						function filterEmployees(text) {
							return employees.filter(function (employee) {
								// Exclude names that are already in the list of selected employees
								if (selectedEmployees.some(function (selectedEmployee) {
									return selectedEmployee.name.toLowerCase() === employee.name.toLowerCase();
								})) {
									return false;
								}
								// Exclude names that match any department name
								if (listdepartments.some(function (department) {
									return department.toLowerCase() === employee.name.toLowerCase();
								})) {
									return false;
								}
								return employee.name.toLowerCase().includes(text.toLowerCase());
							});
						}

						// Function to handle input events on empList
						function handleInput() {
							var text = empList.value.trim();
							suggestionList.innerHTML = "";

							if (text) {
								// Split the input by commas
								var names = text.split(",");
								for (var i = 0; i < names.length; i++) {
									var name = names[i].trim();
									if (name) {
										// Filter employee names based on input text
										var filteredEmployees = filterEmployees(name);

										// Create a new suggestion element for each filtered employee
										filteredEmployees.forEach(function (employee) {
											var suggestionElement = document.createElement("li");
											suggestionElement.innerText = employee.name;
											suggestionElement.setAttribute("data-employee-id", employee.id);

											suggestionElement.addEventListener("click", function () {
												// Add selected employee to list
												var employeeId = this.getAttribute("data-employee-id");
												selectedEmployees.push({
													id: employeeId,
													name: employee.name
												});



												renderSelectedEmployees();
												suggestionList.innerHTML = "";
											});

											suggestionList.appendChild(suggestionElement);
										});
									}
								}
							}
						}

						// Function to handle keydown events on empList
						function handleKeydown(event) {
							if (event.key === "Backspace") {
								if (empList.selectionStart === empList.selectionEnd && empList.selectionStart === 0 && selectedEmployees.length > 0) {
									// Remove last name from selected employees list
									selectedEmployees.pop();
									renderSelectedEmployees();

								} else if (empList.value.length < prevLength) {
									// Remove last name from selected employees list
									selectedEmployees.pop();
									renderSelectedEmployees();
								}
							}

							prevLength = empList.value.length;

							console.log(selectedEmployees);
							console.log(empList.value);
						}



						// Add keydown event listener to empList
						empList.addEventListener("keydown", handleKeydown);

						// Add input event listener to empList
						empList.addEventListener("input", handleInput);

						// Add keydown event listener to empList to handle keyboard shortcuts
						empList.addEventListener("keydown", function (event) {
							if (event.key === "Escape") {
								suggestionList.innerHTML = "";
							} else if (event.key === "Enter") {
								var firstSuggestion = suggestionList.querySelector("li");
								if (firstSuggestion) {
									firstSuggestion.click();
								}
							}
						});

						// Render the list of selected employees
						renderSelectedEmployees();
					});
				</script>

				<script>
					document.addEventListener('DOMContentLoaded', function () {
						var departmentNameInput = document.getElementById('deptName');
						var listdept = [
							<c:forEach var="department" items="${listdepartments}">
								"${department.departmentName}",
							</c:forEach> // assuming "listdepartments" is a model attribute
						];

						var defaultValue = departmentNameInput.value;

						// add an "oninput" event listener to clear custom validity message
						departmentNameInput.addEventListener('input', function () {
							this.setCustomValidity('');
						});

						document.querySelector('form#editDept').addEventListener('submit', function (e) {
							// check if department name already exists
							if (departmentNameInput.value == defaultValue) {
								departmentNameInput.setCustomValidity('');
							}
							else if (departmentNameInput.value == "") {
								departmentNameInput.setCustomValidity('Department name cannot be empty.');
								e.preventDefault(); // prevent form submission
							}
							else if (listdept.includes(departmentNameInput.value)) {
								departmentNameInput.setCustomValidity('Department name already exists. Please choose a different name.');
								e.preventDefault(); // prevent form submission
							}
						});
					});


				</script>

				<script>

					document.addEventListener('DOMContentLoaded', function () {
						var departmentNameInput = document.getElementById('departmentName');

						var listdept = [
							<c:forEach var="department" items="${listdepartments}">
								"${department.departmentName.toLowerCase()}",
							</c:forEach> // assuming "listdepartments" is a model attribute
						];

						// add an "oninput" event listener to clear custom validity message
						departmentNameInput.addEventListener('input', function () {
							this.setCustomValidity('');
						});

    document.querySelector('form[modelAttribute="newdept"]').addEventListener('submit', function(e) {
        // check if department name already exists
        var departmentName = departmentNameInput.value.toLowerCase();
        var pattern = /^[_a-zA-Z][_a-zA-Z0-9]*(\s[_a-zA-Z][_a-zA-Z0-9]*)*$/;
        if (departmentName == "") {
            departmentNameInput.setCustomValidity('Department name cannot be empty.');
            e.preventDefault(); // prevent form submission
        } else if (!pattern.test(departmentName)) {
            departmentNameInput.setCustomValidity('Department name can only contain alphabetic characters, underscores, and numbers, with at most one space between each word.');
            e.preventDefault(); // prevent form submission
        } else if (listdept.includes(departmentName)) {
            departmentNameInput.setCustomValidity('Department name already exists. Please choose a different name.');
            e.preventDefault(); // prevent form submission
        } else {
            departmentNameInput.value = departmentName; // set value to lowercase
        }
    });
});


				</script>

				<style>
					.dropdown-container {
						position: relative;
					}

					.dropdown-menu {
						position: absolute;
						top: 100%;
						left: 0;
						z-index: 1;
						margin: 0;
						padding: 0;
						list-style: none;
						background-color: #fff;
						border: 1px solid #ccc;
						border-top: none;
						overflow-y: scroll;
						max-height: 150px;
						display: block;
					}

					.dropdown-menu li {
						padding: 5px;
						cursor: pointer;
					}

					.dropdown-menu li:hover {
						background-color: #f2f2f2;
					}
				</style>
			</head>

			<body>

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
				<li><a href="teamallocation?pg=1"> <i
						class="material-icons">groups</i>Team
				</a></li>
				<li class="active"><a href="departmentallocation?pg=1"> <i
						class="material-icons">work</i>Department
				</a></li>
				<li><a href="#homeSubmenu1" data-toggle="collapse"
					aria-expanded="false"> <i class="material-icons">playlist_add_check</i>Leave
						Approval
				</a></li>
				<li><a href="#empresignation" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">directions_walk</i>Resignation
						approval
				</a></li>
				<li><a href="analytics"
					aria-expanded="false"> <i class="material-icons">analytics</i>Analytics
				</a></li>
				<!-- <li><a href="#changePasswordModal" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">vpn_key</i>Change
						Password
				</a></li> -->
				<li><a href="#adminprofile" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">account_circle</i>Profile
				</a></li>


						</ul>


					</nav>


					<div id="adminprofile" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="hike" method="post">
									<div class="modal-header">
										<h4 class="modal-title">ADMIN PROFILE</h4>
										<!-- <button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button> -->
									</div>
									<div class="modal-body">
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
										<input type="button" class="btn btn-secondary" data-dismiss="modal"
											value="Close">

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
									<!-- <div class="col-md-5 col-lg-3 order-3 order-md-2">
							<div class="xp-searchbar">
								<form>
									<div class="input-group">
										<input type="search" class="form-control" placeholder="Search">
										<div class="input-group-append">
											<button class="btn" type="submit" id="button-addon2">GO</button>
										</div>
									</div>
								</form>
							</div>
						</div> -->
									<!-- End XP Col -->

									<!-- Start XP Col -->
									<div class="col-md-3 col-lg-2 order-1 order-md-3 ">
										<div class="xp-profilebar text-right" align="right">
											<nav class="navbar p-0">
												<ul class="nav navbar-nav flex-row ml-auto">
													<li class="align-right"><a href="../logout" class="nav-link"><span
																class="material-icons">logout</span>
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
								<h4 class="page-title">Manage Departments</h4>
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
													class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center">
													<h2 class="ml-lg-2">Manage Departments</h2>
												</div>
												<div
													class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">
													<a href="#addDepartmentModal" class="btn btn-success"
														data-toggle="modal"> <i class="material-icons">&#xE147;</i>
														<span>Add New Department</span></a>

												</div>

											</div>
										</div>
										<table class="table table-striped table-hover">
											<thead>
												<tr>

													<th>ID</th>
													<th>DEPARTMENT NAME</th>
													<th>HEAD OF DEPARTMENT</th>
													<th>ACTIONS</th>
												</tr>
											</thead>
											<tbody>
												<tr>

													<c:forEach items="${listdepartments}" var="dept">
												<tr>
													<td>
														<c:out value="${dept.deptId}" />
													</td>
													<td>
														<c:out
															value="${fn:toUpperCase(fn:substring(dept.departmentName, 0, 1))}${fn:toLowerCase(fn:substring(dept.departmentName, 1,fn:length(dept.departmentName)))}" />
													</td>
													<td>
														<c:out value="${dept.hod.empName}" />
													</td>
													<td><a href="#editDepartmentModal${dept.deptId}" class="edit"
															data-toggle="modal"> <i class="material-icons"
																data-toggle="tooltip" title="Edit">&#xE254;</i></a>
														<a href="#generateDepartmentReportModal" class="report"
															data-toggle="modal"> <i class="material-icons"
																data-toggle="tooltip" title="Report">summarize</i></a>
													</td>
												</tr>



												<!-- Edit Modal HTML -->
												<div id="editDepartmentModal${dept.deptId }" class="modal fade">
													<div class="modal-dialog">
														<div class="modal-content">

															<div class="modal-header">
																<h4 class="modal-title">Edit Department</h4>
																<!-- <button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button> -->
															</div>
															<div class="modal-body">
																<form id="editDept" action="editDepartment"
																	method="post" modelAttribute="modifydept">
																	<input type="hidden" id="deptIdInput"
																		name="deptId" />

																	<div class="input-container ic2">
																		<label for="deptName" class="placeholder">Change
																			Department Name</label>
																		<div class="cut"></div>
																		<input id="deptName" name="deptName"
																			class="input required" type="text"
																			placeholder="${dept.departmentName }"
																			value=${dept.departmentName } />

																	</div>
																	<div class="input-container ic2">
																		<label for="department"
																			class="placeholder">Change
																			HOD</label>
																		<div class="cut cut-short"></div>
																		<select id="hod" name="hod"
																			class="input required" placeholder=" ">
																			<option value="null">Unassigned</option>
																			<c:forEach items="${allemployeenames}"
																				var="department">
																				<option value="${department.empId}">
																					(${department.empId})
																					${department.empName}</option>
																			</c:forEach>
																		</select>
																	</div>
																	<div class="input-container ic2">
																		<label for="employeelist"
																			class="placeholder">Add
																			Employees</label>
																		<div class="cut cut-short"></div>
																		<input type="text" id="empList" class="input"
																			placeholder="Type employee name or ID">
																		<div class="dropdown-container">
																			<ul id="suggestions" class="dropdown-menu">
																			</ul>
																		</div>
																		<div id="selectedEmployees"
																			class="selected-employees-container"></div>
																	</div>
																	<div class="input-container ic2">
																		<label for="empindept"
																			class="placeholder">Employees
																			in Department</label>
																		<div class="cut"></div>
																		<ul class="list-group" id="emplist">
																			<c:choose>
																				<c:when test="${empty dept.employees}">
																					<li class="list-group-item">No
																						Employees listed</li>
																				</c:when>
																				<c:otherwise>
																					<c:forEach items="${dept.employees}"
																						var="emp">
																						<li class="list-group-item">
																							(${emp.empId})
																							${emp.empName}</li>
																					</c:forEach>
																				</c:otherwise>
																			</c:choose>
																		</ul>
																	</div>
																	<br>
																	<div class="cut"></div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal">Close</button>
																		<button type="submit"
																			class="btn btn-primary">Submit</button>
																	</div>
																</form>
															</div>
														</div>
													</div>
												</div>
												</c:forEach>
											</tbody>
										</table>

										<div class="clearfix">
											<div class="hint-text">
												Total number of entries <b>${countOfDepartments}</b><br>
												Showing page <b>${pageNo}</b> of <b>${countPages eq 0 ? 1 :
													countPages}</b>

											</div>
											<ul class="pagination">

												<c:if test="${ pageNo > 1}">
													<li class="page-item"><a href="?pg=${pageNo-1}"
															class="page-link">Previous</a></li>
												</c:if>
												<c:if test="${ pageNo < countPages}">
													<li class="page-item"><a href="?pg=${pageNo+1}"
															class="page-link">Next</a></li>
												</c:if>


											</ul>
										</div>
									</div>
								</div>
								<!-- Add Modal HTML -->
								<div id="addDepartmentModal" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">

											<div class="modal-header">
												<h4 class="modal-title">Add Department</h4>
												<!-- <button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button> -->
											</div>
											<div class="modal-body">
												<form action="addDept" method="post" modelAttribute="newdept">
													<div class="input-container ic1">
														<label for="departmentName" class="placeholder">Department
															Name</label>
														<div class="cut"></div>
														<input id="departmentName" name="departmentName"
															class="input required" type="text" placeholder=" "
															required />
													</div>
													<div class="input-container ic2">
														<label for="hod" class="placeholder">HOD</label>
														<div class="cut cut-short"></div>
														<select id="hod" name="hod" class="input required"
															placeholder=" ">
															<option value="0">Unassigned</option>
															<c:forEach items="${allemployeenames}" var="department">
																<option value="${department.empId}">
																	(${department.empId})
																	${department.empName}</option>


															</c:forEach>
														</select>
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

								<br>
								</form>
							</div>
						</div>
					</div>
				</div>

					<!-- Password Modal HTML -->
				<div id="changePasswordModal" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">

							<div class="modal-header">
								<h4 class="modal-title">Change Password</h4>
								<!-- <button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button> -->
							</div>
							<div class="modal-body">
								<form action="changePassword" method="post"
									modelAttribute="newpass">
									<div class="input-container ic1">
										<label for="empId" class="placeholder">Employee Name</label>
										<div class="cut"></div>
										<select id="empId" name="empId" class="input required"
											placeholder=" " required>
											<c:forEach items="${allemployeenames}" var="department">
												<option value="${department.empId}">(${department.empId})
													${department.empName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="input-container ic2">
										<label for="hod" class="placeholder">New Password</label>
										<div class="cut cut-short"></div>
										<input id="changedpassword" name="changedPassword"
											class="input required" type="password" placeholder=" "
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
			<script src="../resources/lib/jquery/jquery-3.3.1.min.js" type="text/javascript" />
			<script src="../resources/lib/jquery/jquery-3.3.1.slim.min.js" type="text/javascript" />
			<script src="../resources/custom/js/admin-dashboard/navtoggle.js" type="text/javascript" />
			<script src="../resources/lib/popper/popper.min.js" type="text/javascript" />
			<script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript" />


<script type="text/javascript">
        
		$(document).ready(function(){
		  $(".xp-menubar").on('click',function(){
		    $('#sidebar').toggleClass('active');
			$('#content').toggleClass('active');
		  });
		  
		   $(".xp-menubar,.body-overlay").on('click',function(){
		     $('#sidebar,.body-overlay').toggleClass('show-nav');
		   });
		  
		});
		
</script>
</html>