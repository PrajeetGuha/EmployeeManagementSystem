<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	document
			.addEventListener(
					'DOMContentLoaded',
					function() {
						var startDateInput = document
								.getElementById('startDate');
						var endDateInput = document.getElementById('endDate');

						endDateInput.addEventListener('input', function() {
							this.setCustomValidity(''); // reset custom validity message on input
						});

						document
								.querySelector('form[modelAttribute="newproj"]')
								.addEventListener(
										'submit',
										function(e) {
											var startDate = new Date(
													startDateInput.value);
											var endDate = new Date(
													endDateInput.value);

											if (endDate < startDate) {
												endDateInput
														.setCustomValidity('End date cannot be before start date.');
												e.preventDefault(); // prevent form submission
											}
										});
					});
</script>
<script>

document.addEventListener('DOMContentLoaded', function() {
    var departmentNameInput = document.getElementById('projectName');

    var listdept = [
        <c:forEach var="project" items="${listprojects}">
          "${project.projectName.toLowerCase()}",
        </c:forEach> // assuming "listdepartments" is a model attribute
    ];

    // add an "oninput" event listener to clear custom validity message
    departmentNameInput.addEventListener('input', function() {
        this.setCustomValidity('');
    });

    document.querySelector('form[modelAttribute="newproj"]').addEventListener('submit', function(e) {
        // check if department name already exists
        var departmentName = departmentNameInput.value.toLowerCase();
        var pattern = /^[_a-zA-Z][_a-zA-Z0-9]*(\s[_a-zA-Z][_a-zA-Z0-9]*)*$/;
        if (departmentName == "") {
            departmentNameInput.setCustomValidity('Project name cannot be empty.');
            e.preventDefault(); // prevent form submission
        }else if (!pattern.test(departmentName)) {
            departmentNameInput.setCustomValidity('Department name can only contain alphabetic characters, underscores, and numbers, with at most one space between each word.');
            e.preventDefault(); // prevent form submission
        } else if (listdept.includes(departmentName)) {
            departmentNameInput.setCustomValidity('Project already exists. Please choose a different name.');
            e.preventDefault(); // prevent form submission
        } else {
            departmentNameInput.value = departmentName; // set value to lowercase
        }
    });
});

  </script>

<script>
document.addEventListener('DOMContentLoaded', function() {
	
    // Get the form element
    const form = document.querySelector('#modifyproj');

    // Get the input elements
    const projectNameInput = form.querySelector('#projName');
    const startDateInput = form.querySelector('#changedstartDate');
    const endDateInput = form.querySelector('#changedendDate');

    // Get the list of project names sent by the model attribute
    var projectNames = [
        <c:forEach var="department" items="${listprojects}">
        "${department.projectName}",
      </c:forEach>];

    // Add an event listener to the form submit event
    form.addEventListener('submit', (event) => {
        // Check if the project name is already in the list of project names
        if (projectNames.includes(projectNameInput.value)) {
            projectNameInput.setCustomValidity('This project name already exists. Please choose a different name.');
        } else {
            projectNameInput.setCustomValidity('');
        }

        // Check if the end date is greater than the start date
        if (new Date(endDateInput.value) <= new Date(startDateInput.value)) {
            endDateInput.setCustomValidity('The end date should be greater than the start date.');
        } else {
            endDateInput.setCustomValidity('');
        }

        // Check if any input is invalid
        if (!form.checkValidity()) {
            // Prevent form submission if inputs are invalid
            event.preventDefault();
        }
    });

    // Add an event listener to clear the validity message on input change
    projectNameInput.addEventListener('input', () => {
        projectNameInput.setCustomValidity('');
    });

    startDateInput.addEventListener('input', () => {
        startDateInput.setCustomValidity('');
    });

    endDateInput.addEventListener('input', () => {
        endDateInput.setCustomValidity('');
    });


	});

</script>

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


				<li class="active"><a href="projectallocation?pg=1"> <i
						class="material-icons">laptop</i>Project
				</a></li>
				<li><a href="teamallocation?pg=1"> <i
						class="material-icons">groups</i>Team
				</a></li>
				<li><a href="departmentallocation?pg=1"> <i
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
				<li><a href="analytics" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">analytics</i>Analytics
				</a></li>
				<li><a href="#changePasswordModal" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">vpn_key</i>Change
						Password
				</a></li>
				<li><a href="#adminprofile" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">account_circle</i>Profile
				</a></li>


			</ul>


		</nav>
		<!-- <div id="project" class="modal fade">
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
									required>
							</div>
							<div class="form-group">
								<label>Project Name</label> <input type="text"
									class="form-control" required>
							</div>

						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> <input type="submit"
								class="btn btn-success" value="Add">
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
								<label>Employee Name</label> <input type="text"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label>Team Name</label> <input type="text" class="form-control"
									required>
							</div>

						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> <input type="submit"
								class="btn btn-success" value="Add">
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
								<label>Employee Name</label> <input type="text"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label>Department Name</label> <input type="text"
									class="form-control" required>
							</div>

						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> <input type="submit"
								class="btn btn-success" value="Add">
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
								<label>Employee Name</label> <input type="text"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label>Hike</label> <input type="text" class="form-control"
									required>
							</div>
							<div class="form-group">
								<label>Hike Date</label> <input type="date" class="form-control"
									required>
							</div>

						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> <input type="submit"
								class="btn btn-success" value="Add">
						</div>
					</form>
				</div>
			</div>
		</div> -->

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
					<h4 class="page-title">Manage Projects</h4>
					<!-- <ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Booster</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol> -->
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
										<h2 class="ml-lg-2">Manage Projects</h2>
									</div>
									<div
										class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">
										<a href="#addProjectModal" class="btn btn-success"
											data-toggle="modal"> <i class="material-icons">&#xE147;</i>
											<span>Add New Project</span></a>
										<!--  <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
		  <i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->
										<!-- <a href="javascript: void(0)" onclick="window.open('allocateproject','_blank','width=900,height=300');" class="btn btn-success"
											> <i class="material-icons">&#xE147;</i>
											<span>Allocate Project</span></a> -->
									</div>
								</div>
							</div>
							<table class="table table-striped table-hover">
								<thead>
									<tr>

										<th>ID</th>
										<th>PROJECT NAME</th>
										<th>PROJECT MANAGER</th>
										<th>START DATE</th>
										<th>END DATE</th>
										<th>ACTIONS</th>
									</tr>
								</thead>
								<tbody>
									<tr>

										<c:forEach items="${listprojects}" var="project">
											<tr>
												<td><c:out value="${project.projId}" /></td>
												<td><c:out
														value="${project.projectName.substring(0,1).toUpperCase()}${project.projectName.substring(1).toLowerCase()}" /></td>

												<td><c:out value="${project.pm.empName}" /></td>
												<td><fmt:formatDate value="${project.startDate}"
														pattern="dd-MMMM-yyyy" /></td>
												<td><fmt:formatDate value="${project.endDate}"
														pattern="dd-MMMM-yyyy" /></td>
												<td><a href="#editProjectModal${project.projId }"
													class="edit" data-toggle="modal"> <i
														class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
													<a href="#generateProjectReportModal" class="report"
													data-toggle="modal"> <i class="material-icons"
														data-toggle="tooltip" title="Report">summarize</i></a></td>
											</tr>

											<!-- Edit Modal HTML -->
											<div id="editProjectModal${project.projId }"
												class="modal fade">
												<div class="modal-dialog">
													<div class="modal-content">

														<div class="modal-header">
															<h4 class="modal-title">Edit Project</h4>
															<!-- <button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button> -->
														</div>
														<div class="modal-body">
															<form action="editProj" method="post"
																modelAttribute="modifyproj" id="modifyproj" >
																<div class="input-container ic1">
																	<label for="projName" class="placeholder">Change
																		Project Name</label>
																	<div class="cut"></div>
																	<input id="projName" name="projName"
																		class="input required" type="text" placeholder=" "
																		required />
																</div>
																<div class="input-container ic2">
																	<label for="pm" class="placeholder">Change
																		Project Manager</label>
																	<div class="cut cut-short"></div>
																	<select id="pm" name="pm" class="input required"
																		placeholder=" " required>

																		<c:forEach items="${allemployeenames}"
																			var="department">
																			<option value="${department.empId}">(${department.empId})
																				${department.empName}</option>


																		</c:forEach>
																	</select>
																</div>
																<div class="input-container ic2">
																	<label for="changedstartDate" class="placeholder">Start
																		Date</label>
																	<div class="cut cut-short"></div>
																	<input id="changedstartDate" name="changedstartDate"
																		class="input required" type="date" placeholder=" "
																		required />
																</div>
																<div class="input-container ic2">
																	<label for="changedendDate" class="placeholder">Tentative
																		End Date</label>
																	<div class="cut cut-short"></div>
																	<input id="changedendDate" name="changedendDate"
																		class="input required" type="date" placeholder=" "
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
										</c:forEach>

									</tr>
									<!--  <tr>
                      <td>
                        <span class="custom-checkbox">
                          <input type="checkbox" id="checkbox2" name="options[]" value="1">
                          <label for="checkbox2"></label>
                        </span>
                      </td>
                      <td>Dominique Perrier</td>
                      <td>dominiqueperrier@mail.com</td>
                      <td>Obere Str. 57, Berlin, Germany</td>
                      <td>(313) 555-5735</td>
                      <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="custom-checkbox">
                          <input type="checkbox" id="checkbox3" name="options[]" value="1">
                          <label for="checkbox3"></label>
                        </span>
                      </td>
                      <td>Maria Anders</td>
                      <td>mariaanders@mail.com</td>
                      <td>25, rue Lauriston, Paris, France</td>
                      <td>(503) 555-9931</td>
                      <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="custom-checkbox">
                          <input type="checkbox" id="checkbox4" name="options[]" value="1">
                          <label for="checkbox4"></label>
                        </span>
                      </td>
                      <td>Fran Wilson</td>
                      <td>franwilson@mail.com</td>
                      <td>C/ Araquil, 67, Madrid, Spain</td>
                      <td>(204) 619-5731</td>
                      <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <span class="custom-checkbox">
                          <input type="checkbox" id="checkbox5" name="options[]" value="1">
                          <label for="checkbox5"></label>
                        </span>
                      </td>
                      <td>Martin Blank</td>
                      <td>martinblank@mail.com</td>
                      <td>Via Monte Bianco 34, Turin, Italy</td>
                      <td>(480) 631-2097</td>
                      <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal">
                          <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                         <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
			<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                      </td>
                    </tr>-->
								</tbody>
							</table>

							<div class="clearfix">
								<div class="hint-text">
									Total number of entries <b>${countOfprojects}</b><br>
									Showing page <b>${pageNo}</b> of <b>${countPages eq 0 ? 1 : countPages}</b>

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
					<!-- Edit Modal HTML -->
					<div id="addProjectModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">

								<div class="modal-header">
									<h4 class="modal-title">Add Project</h4>
									<!-- <button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button> -->
								</div>
								<div class="modal-body">
									<form action="addProj" method="post" modelAttribute="newproj">
										<div class="input-container ic1">
											<label for="projectName" class="placeholder">Project
												Name</label>
											<div class="cut"></div>
											<input id="projectName" name="projectName"
												class="input required" type="text" placeholder=" " required />
										</div>
										<div class="input-container ic2">
											<label for="pm" class="placeholder">Project Manager</label>
											<div class="cut cut-short"></div>
											<select id="pm" name="pm" class="input required"
												placeholder=" " >
												<option value="0">Unassigned</option>
												<c:forEach items="${allemployeenames}" var="department">
													<option value="${department.empId}">(${department.empId})
														${department.empName}</option>


												</c:forEach>
											</select>
										</div>
										<div class="input-container ic2">
											<label for="startDate" class="placeholder">Start Date</label>
											<div class="cut cut-short"></div>
											<input id="startDate" name="startDate" class="input required"
												type="date" placeholder=" " required />
										</div>
										<div class="input-container ic2">
											<label for="endDate" class="placeholder">Tentative
												End Date</label>
											<div class="cut cut-short"></div>
											<input id="endDate" name="endDate" class="input required"
												type="date" placeholder=" " required />
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
						<!-- <div id="allocateProjectModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form>
									<div class="modal-header">
										<h4 class="modal-title">Allocate Project</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>Project Name</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Team Name</label> <input type="text" class="form-control"
												required>
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
											data-dismiss="modal" value="Cancel"> <input
											type="submit" class="btn btn-success" value="Add">
									</div>
								</form>
							</div>
						</div>
					</div> -->




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
												<input id="changedpassword" name="changedpassword"
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


<!-- <script type="text/javascript">
        
		$(document).ready(function(){
		  $(".xp-menubar").on('click',function(){
		    $('#sidebar').toggleClass('active');
			$('#content').toggleClass('active');
		  });
		  
		   $(".xp-menubar,.body-overlay").on('click',function(){
		     $('#sidebar,.body-overlay').toggleClass('show-nav');
		   });
		  
		});
		
</script> -->
</html>