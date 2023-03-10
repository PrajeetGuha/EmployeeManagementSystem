<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	$(document)
			.ready(
					function() {
						// Loop through each cell in the highlight-column class
						$('.highlight-column')
								.each(
										function() {
											var status = $(this).text(); // Get the cell's text value

											// Set a different background color based on the status value
											if (status === 'ACTIVE') {
												$(this)
														.css(
																'background-image',
																'linear-gradient(to bottom, limegreen,white)');

												$(this).css('color', 'white');

											} else if (status === 'INACTIVE') {
												$(this)
														.css('background',
																'linear-gradient(to bottom, red, white)');

												$(this).css('color', 'white');
											}
										});
					});
</script>
<script>
  $(document).ready(function() {
    // Get the list of items from the model attribute
    var items = [
      <c:forEach var="item" items="${employees}">
        "${item.empName}",
      </c:forEach>
    ];

    // Listen for the keyup event on the search input field
    $('#search-input').on('keyup', function() {
      // Get the value of the search input field
      var searchTerm = $(this).val().toLowerCase();

      // Filter the list based on the search term
      var filteredItems = items.filter(function(item) {
        return item.toLowerCase().indexOf(searchTerm) > -1;
      });

      // Update the search results list
      var $searchResults = $('#search-results');
      $searchResults.empty();
      filteredItems.forEach(function(item) {
        var $li = $('<li>').text(item);
        $li.on('click', function() {
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
    $(document).on('click', function(event) {
      if (!$(event.target).closest('.xp-searchbar').length) {
        $searchResults.hide();
      }
    });
    
    $('#search-form').on('submit', function() {
        var searchTerm = $('#search-term').val();
        if (searchTerm === '') {
          return false; // Prevent submitting the form if search term is empty
        }
      });
  });
</script>
</head>

<c:set var="pageNo" value="${pageNo}" />
<c:set var="pageCount" value="${pageCount}" />
</head>
<script>
	function selectedEmpstatus(id,name,status){
		document.getElementById("empstatusname").innerHTML = name;
		$("#empIdStatus").attr("value",id);
		if (status == "INACTIVE"){
			$("#status-modal-form").attr("action","activateUser?pgNo="+"${pageNo}");
			$("#changestatusbtn").attr("class", "btn btn-primary");
			$("#changestatusbtn").attr("value", "Activate");
		}
		else{
			$("#status-modal-form").attr("action","deactivateUser?pgNo="+"${pageNo}");
			$("#changestatusbtn").attr("class", "btn btn-danger");
			$("#changestatusbtn").attr("value", "Deactivate");
		}
	}
</script>


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
				<li class="active"><a href="#" class="dashboard"><i
						class="material-icons">dashboard</i> <span>Dashboard</span></a></li>
				<li><a href="#homeSubmenu1" data-toggle="collapse"
					aria-expanded="false"> <i class="material-icons">playlist_add_check</i>Leave
						Approval
				</a></li>

				<li><a href="projectallocation"> <i class="material-icons">laptop</i>Project
						Allocation
				</a></li>
				<li><a href="teamallocation"> <i class="material-icons">groups</i>Team
						Allocation
				</a></li>
				<li><a href="departmentallocation"> <i
						class="material-icons">work</i>Department Allocation
				</a></li>
				<li><a href="#hike" data-toggle="modal" aria-expanded="false">
						<i class="material-icons">currency_rupee</i>Appraisal
				</a></li>



				<li><a href="#adminprofile" data-toggle="modal"
					aria-expanded="false"> <i class="material-icons">account_circle</i>Profile
				</a></li>


			</ul>


		</nav>
		<div id="project" class="modal fade">
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
		</div>

		<div id="adminprofile" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="hike" method="post">
						<div class="modal-header">
							<h4 class="modal-title">ADMIN PROFILE</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
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
							<input type="button" class="btn btn-default" data-dismiss="modal"
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
					<div class="row">
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
						<div class="col-md-5 col-lg-3 order-3 order-md-2">
							<div class="xp-searchbar">
								<form id="search-form" action="/viewdata" method="get">
									<div class="input-group">
										<input type="search" class="form-control" placeholder="Search"
											id="search-input">
										<div class="input-group-append">
											<button class="btn" type="submit" id="button-addon2">GO</button>
										</div>
									</div>
								</form>
								<ul class="dropdown-menu" id="search-results"
									style="display: none;">
								</ul>
							</div>
						</div>


						<!-- End XP Col -->

						<!-- Start XP Col -->
						<div class="col-10 col-md-6 col-lg-8 order-1 order-md-3">
							<div class="xp-profilebar text-right" align="right">
								<nav class="navbar p-0">
									<ul class="nav navbar-nav flex-row ml-auto">
										<li class="align-right"><a href="../logout"><span
												class="material-icons">logout</span> Logout</a></li>
										<!--  <li class="dropdown nav-item active">
                                <a href="#" class="nav-link" data-toggle="dropdown">
                                   <span class="material-icons">notifications</span>
								   <span class="notification">4</span>
                               </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#">You have 5 new messages</a>
                                    </li>
                                    <li>
                                        <a href="#">You're now friend with Mike</a>
                                    </li>
                                    <li>
                                        <a href="#">Wish Mary on her birthday!</a>
                                    </li>
                                    <li>
                                        <a href="#">5 warnings in Server Console</a>
                                    </li>
                                  
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
								<span class="material-icons">question_answer</span>

								</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link" href="#" data-toggle="dropdown">
								<img src="img/user.jpg" style="width:40px; border-radius:50%;"/>
								<span class="xp-user-live"></span>
								</a>
								<ul class="dropdown-menu small-menu">
                                    <li>
                                        <a href="#">
										  <span class="material-icons">person_outline</span>Profile

										</a>
                                    </li>
                                    <li>
                                        <a href="#"><span class="material-icons">settings</span>Settings</a>
                                    </li>
                                    <li>
                                        <a href="#"><span class="material-icons">logout</span>Logout</a>
                                    </li>
                                </ul>
                            </li>-->
									</ul>


								</nav>

							</div>
						</div>
						<!-- End XP Col -->

					</div>
					<!-- End XP Row -->

				</div>
				<div class="xp-breadcrumbbar text-center">
					<h4 class="page-title">Dashboard</h4>
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
										<h2 class="ml-lg-2">Manage Employees</h2>
									</div>
									<div
										class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">
										<a href="javascript: void(0)"
											onclick="window.open('addemployee','_blank','width=900,height=300');"
											class="btn btn-success"> <i class="material-icons">&#xE147;</i>
											<span>Add New Employee</span></a>
										<!--  <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
		  <i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->
									</div>
								</div>
							</div>
							<table class="table table-striped table-hover">
								<thead>
									<tr>

										<th>ID</th>
										<th>NAME</th>
										<th>EMAIL</th>
										<th>DESIGNATION</th>
										<th>STATUS</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<tr>

										<c:forEach items="${employees}" var="employee">
											<tr>
												<td><c:out value="${employee.empId}" /></td>
												<td><c:out value="${employee.empName}" /></td>
												<td><c:out value="${employee.workEmail}" /></td>
												<td><c:out value="${employee.designation}" /></td>

												<td class="highlight-column"><c:out
														value="${employee.empstatus}" /></td>
												<%-- <td><button id="editemp" class='edit' />
														<i class="material-icons" data-toggle="tooltip"
														title="Edit">&#xE254;</i>
												</a> <button id="editstat" class='edit' data-employee-id='${employee.empId }' />
														<i class="material-icons" data-toggle="tooltip"
														title="Status">&#xE152;</i>
												</a></td>

												<td><c:out value="${employee.empstatus}" /></td> --%>
												<td><a href="#editEmployeeModal" class="edit"
													data-toggle="modal"> <i class="material-icons"
														data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
													href="#deleteEmployeeModal" class="delete"
													data-toggle="modal"
													onclick="selectedEmpstatus(${employee.empId},'${employee.empName}', '${employee.empstatus}')">
														<i class="material-icons" data-toggle="tooltip"
														title="Status">&#xE152;</i>
												</a></td>

											</tr>
										</c:forEach>



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
									Total number of entries <b>${empCount}</b><br> Showing
									page <b>${pageNo}</b> of <b>${pageCount }</b>
								</div>
								<ul class="pagination">

									<c:if test="${ pageNo > 1}">
										<li class="page-item"><a href="?pg=${pageNo-1}"
											class="page-link">Previous</a></li>
									</c:if>
									<c:if test="${ pageNo < pageCount}">
										<li class="page-item"><a href="?pg=${pageNo+1}"
											class="page-link">Next</a></li>
									</c:if>


								</ul>
							</div>
						</div>
					</div>
					<!-- Edit Modal HTML -->
					<div id="addEmployeeModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form>
									<div class="modal-header">
										<h4 class="modal-title">Add Employee</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>Name</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Username</label> <input type="text"
												class="form-control" required>
										</div>
										<div class="form-group">
											<label>Password</label> <input type="password"
												class="form-control" required>
										</div>
										<!--  <div class="form-group">
											<label>Address</label>
											<textarea class="form-control" required></textarea>
										</div>
										<div class="form-group">
											<label>Phone</label> <input type="text" class="form-control"
												required>
										</div>-->
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default"
											data-dismiss="modal" value="Cancel"> <input
											type="submit" class="btn btn-success" value="Add">
									</div>
								</form>
							</div>
						</div>
					</div>



					<!-- Edit Modal HTML -->
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
											<label>Email</label> <input type="email" class="form-control"
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
											type="submit" class="btn btn-info" value="Save">
									</div>
								</form>
							</div>
						</div>
					</div>


					<!-- <!-- Delete Modal HTML -->
					<div id="deleteEmployeeModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">

								<form:form action="editStatus" method="post"
									id="status-modal-form" modelAttribute="employee">

									<div class="modal-header">
										<h4 class="modal-title">Edit Status</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">

										<p>Edit status for this employee?</p>

										<p>
											Edit status of <span id="empstatusname"></span>
										</p>


									</div>
									<input type="hidden" name="empId" id="empIdStatus" />
									<div class="modal-footer">

										<!-- <input type="submit" class="btn btn-danger" value="Inactive">
										<input type="submit" class="btn btn-primary" value="Active">
 -->
										<!-- <input type="submit" class="btn btn-primary" value="Active" id = "activate"> 
										<input type="submit" class="btn btn-danger" value="Inactive" id = "deactivate"> -->
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