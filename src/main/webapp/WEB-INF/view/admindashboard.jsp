<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
</head>
<body>
    <div>
        AdminId: ${admin.adminId} <br/>
        AdminName: ${admin.adminName} <br/>
        WorkEmail: ${admin.adminEmail} <br/>
    </div>
    <nav>
        <ul>
            <li><a href="">Dashboard</a></li>
            <li><a href="projectallocation">Project Allocation</a></li>
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
										<a href="javascript: void(0)" onclick="window.open('addemployee','_blank','width=900,height=300');"  class="btn btn-success"
											> <i class="material-icons">&#xE147;</i>
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
												<td><c:out value="${employee.empstatus}" /></td>
												<td><a href="#editEmployeeModal" class="edit"
													data-toggle="modal"> <i class="material-icons"
														data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
													href="#deleteEmployeeModal" data-id="${employee.empId}" class="statuschange"
													data-toggle="modal"> <i class="material-icons"
														data-toggle="tooltip" title="Status">&#xE152;</i></a></td>
											</tr>
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
									Total number of entries <b>${empCount}</b><br> Showing
									page <b>${pageNo}</b>
								</div>
								<ul class="pagination">
										
										<c:if test="${ pageNo > 1}" > 
											<li class="page-item">
											<a href="?pg=${pageNo-1}" class="page-link">Previous</a> 
											</li>
										</c:if>
										<c:if test="${ pageNo < pageCount}"> 
											<li class="page-item">
											<a href="?pg=${pageNo+1}" class="page-link">Next</a> 
											</li>
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
											<label>Username</label> <input type="text" class="form-control"
												required>
										</div>
										<div class="form-group">
											<label>Password</label> <input type="password" class="form-control"
												required>
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



					<!-- Delete Modal HTML -->
					<div id="deleteEmployeeModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form action="editStatus" method="post">
									<div class="modal-header">
										<h4 class="modal-title">Edit Status</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<p>Edit status for this employee?</p>
										
									</div>
									<div class="modal-footer">
										<input type="submit" class="btn btn-primary" value="Active"> 
										<input type="submit" class="btn btn-danger" value="Inactive">
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


<script type="text/javascript">
        
		$(document).ready(function(){
		  $(".xp-menubar").on('click',function(){
		    $('#sidebar').toggleClass('active');
			$('#content').toggleClass('active');
		  });
		  
		   $(".xp-menubar,.body-overlay").on('click',function(){
		     $('#sidebar,.body-overlay').toggleClass('show-nav');
		   });

		//    $(".statuschange").on('click',function(){
		// 	var empId = $(this).attr('data-id');
		// 	(".modal-content #empId").val(empId);
		//    })
		  
		});
		
</script>
</html>