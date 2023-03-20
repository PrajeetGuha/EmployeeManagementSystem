<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="utf-8">
					<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
					<meta name="viewport"
						content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
					<title>EMPLOYEE DASHBOARD</title>
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
					<c:set var="pageCount" value="${pageCount}" />
					<script>
						$(document)
							.ready(
								function () {
									// Loop through each cell in the highlight-column class
									$('.highlight-column')
										.each(
											function () {
												var status = $(this).text(); // Get the cell's text value

												// Set a different background color based on the status value
												if (status === 'active') {


													$(this).css('color', 'limegreen');

												} else if (status === 'inactive') {


													$(this).css('color', 'red');
												}
											});
								});
					</script>
					<script>
function updateMin() {
  var startDate = document.getElementById("start_date").value;
  document.getElementById("end_date").setAttribute("min", startDate);
}
</script>

				</head>

				<c:set var="pageNo" value="${pageNo}" />
				<c:set var="pageCount" value="${pageCount}" />
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
								<li><a href="dashboard" class="dashboard"><i class="material-icons">dashboard</i>
										<span>Dashboard</span></a></li>
								<li><a href="leaveApplication?pg=1" data-toggle="collapse"
										aria-expanded="false"> <i class="material-icons">playlist_add_check</i>Apply
										Leave

									</a></li>



								<li><a href="resign" > <i
											class="material-icons">directions_walk</i>Apply
										Resignation



									</a></li>

                
									<li ><a href="employeepersonaldetails" >
										<i class="material-icons">contact_mail</i>Personal Details

								<li><a href="professionaldetails">
									<i class="material-icons">work</i> 
										<span>Professional Details</span></a></li>

								<li ><a href="qualificationdetails">
										<!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
										<i class="material-icons">school</i> 
										<span>Qualification Details</span></a></li>
								<li class="active"><a href="familyDetails"><i class="material-icons">group</i>
										<span>Family Details</span></a></li>




							</ul>


						</nav>






						<!-- <div id="applyresignation" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<form action="deptAlloc" method="post">
										<div class="modal-header">
											<h4 class="modal-title">APPLY RESIGNATION</h4>
											
										</div>
										<div class="modal-body">
											<div class="form-group">
												<label> REASON FOR RESIGNATION</label> <textarea class="form-control" name="reason" required></textarea>
											</div>
										<br><br>
										<div>
											<div class
											<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
											<input type="submit" class="btn btn-success" value="Apply"></div>
										
										</div>
									</form>
								</div>
							</div>
						</div> -->







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
														<li class="align-right">
															<a href="../logout" class="nav-link"><span
																	class="material-icons">logout</span>
																Logout
															</a>
														</li>

													</ul>


												</nav>

											</div>
										</div>
										<!-- End XP Col -->

									</div>
									<!-- End XP Row -->

								</div>
								<div class="xp-breadcrumbbar text-center">
									<h4 class="page-title">Family Details</h4>
									<!--  <ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Booster</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol>-->
								</div>

							</div>


								<br>

							<!--------main-content------------->


							<div class="row">
								<div class="col-1"></div>
								<div class="col-10">
									<div class="table-wrapper">
										<div class="table-title">
											<div class="row">
												<div
													class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center">
													<h2 class="ml-lg-2">Manage Family Details</h2>
												</div>
												<div
													class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">
													<a href="#addFamilyModal" class="btn btn-success"
														data-toggle="modal">
														<i class="material-icons">&#xE147;</i>
														<span>Add Family Details</span></a>

												</div>

											</div>
										</div>
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>Relation</th>
													<th>Family Member Name</th>
													<th>Occupation</th>
													<th>Date Of Birth</th>
													<th>Contact Number</th>
												</tr>
											</thead>
											<tbody>
												<tr>

													<c:forEach items="${listOfFamilyDetails}" var="familyInfo">
												<tr>
													<td>
													<button type="button" class="btn btn-primary" disabled>
														<c:out value="${familyInfo.relation}" /></button>
													</td>
													
													<td>
														<c:out value="${familyInfo.name}" />
													</td>
													
													<td>
														<c:out value="${familyInfo.occupation}" /> 
														</td>
														
																											<td>
														<c:out value="${familyInfo.dob}" />
													</td>													<td>
														<c:out value="${familyInfo.contactno}" />
													</td>
												</tr>






												</c:forEach>

												</tr>
											</tbody>
										</table>

										<!-- <div class="clearfix">
										<div class="hint-text">
											Total number of entries <b>${countOfDepartments}</b><br>
											Showing page <b>${pageNo}</b> of <b>${countPages }</b>
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
									</div> -->
									</div>
								</div>

								<div class="col-1"></div>
								<!-- Edit Modal HTML -->
								<div id="addFamilyModal" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">

											<div class="modal-header">
												<h4 class="modal-title">Add Family</h4>
											</div>
											<form action="addFamily" method="post" modelAttribute="family">
											<div class="modal-body">
													<div class="form-group">
														<label>Family Member Name</label><input type="text" class="form-control" name="name"
															required>
													</div>
													<div class="form-group">
														<label>Relation</label><input type="text" class="form-control" name="relation"
															required>
													</div>
													<div class="form-group">
														<label>Occupation</label><input type="text" class="form-control" name="occupation"
															required>
													</div>
													<div class="form-group">
														<label>Date Of Birth</label> <input type="date" id="start_date" class="form-control"  
														name="dob"
															required>
													</div>
													
													<div class="form-group">
														<label>Contact Number  </label> <input type="text" id="end_date" pattern="[5-9]{1}[0-9]{9}" class="form-control" name="contactno"
															required>
															
													</div>
													
													<!-- <div class="input-group ">
														<input type="date" class="form-control"
															placeholder="Date of Joining"
															aria-label="Date of Joining"
															aria-describedby="basic-addon1" name="leaveAppliedFor"/>
													</div> -->
													<!-- <input type="date" name="leaveAppliedFor"/> -->
											</div>
											<div class="modal-footer">
												<input type="button" class="btn btn-danger" data-dismiss="modal"
													value="Cancel"> <input type="submit" class="btn btn-success"
													value="Add">
											</div>
											</form>
										</div>
									</div>
								</div>
							</div>






							<!---footer---->                        <br>
                        <br>

                        <footer class="footer">
                            <div class="container-fluid">
                                <div class="footer-in">
                                    <p class="mb-0">NRI FinTech - All Rights Reserved.</p>
                                </div>
                            </div>
                        </footer>


						</div>

					</div>
					</div>


					<!----------html code complete----------->





					</div>



					<!-- Optional JavaScript -->
					<!-- jQuery first, then Popper.js, then Bootstrap JS -->

				</body>
				<script src="../resources/lib/jquery/jquery-3.3.1.min.js" type="text/javascript" />
				<script src="../resources/lib/jquery/jquery-3.3.1.slim.min.js" type="text/javascript" />
				<script src="../resources/custom/js/admin-dashboard/navtoggle.js" type="text/javascript" />
				<script src="../resources/lib/popper/popper.min.js" type="text/javascript" />
				<script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript" />


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