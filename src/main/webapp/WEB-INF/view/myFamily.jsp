<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<!-- Bootstrap CSS -->
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
					crossorigin="anonymous">
				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

				<link rel="stylesheet" href="/resources/user-css/user.css">

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

				<title>Family details</title>
			</head>

			<body>

				<!-- Option 1: Bootstrap Bundle with Popper -->
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
					crossorigin="anonymous"></script>
				<script src="/resources/javascript/user.js"></script>



				<%@ include file="/resources/defaultHtml/dashboardHeader.jsp" %>
					<nav class="navbar navbar-light ">
						<div class="container-fluid">
							<!-- <a class="navbar-brand" href="#">Offcanvas dark navbar</a>
             -->

							<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
								data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="offcanvas offcanvas-end dark" tabindex="-1" id="offcanvasDarkNavbar"
								aria-labelledby="offcanvasDarkNavbarLabel">
								<div class="offcanvas-header">
									<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Dark
										offcanvas</h5>
									<button type="button" class="btn-close btn-close-dark" data-bs-dismiss="offcanvas"
										aria-label="Close"></button>
								</div>
								<div class="offcanvas-body">
									<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">

										<li class="nav-item"><a href="<%=request.getContextPath()%>profile">My
												Profile</a>
										</li>
										<br>
										<li class="nav-item"><a href="<%=request.getContextPath()%>personal">Personal
												details</a></li>
										<br>
										<li class="nav-item"><a href="<%=request.getContextPath()%>family">My Family</a>
										</li>
										<br>
										<li class="nav-item"><a
												href="<%=request.getContextPath()%>qualification">Qualification</a></li>
										<br>
										<li class="nav-item"><a
												href="<%=request.getContextPath()%>professional">Professional
												Details</a>
										</li>

									</ul>
								</div>
							</div>
						</div>
					</nav>


					<div class="container-fluid ">

						<div class="row align-items-start">
							<div class="col-1 "></div>


							<div class="col-12">
								<div class="container-fluid">
									<div class="row">
										<div class="col">
											<!-- <form id="familyForm"> -->



											<div class="main-content">
												<div class="row">

													<div class="col-mb-12">
														<div class="table-wrapper">
															<div class="table-title">
																<div class="row">
																	<div
																		class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center">
																		<h2 class="ml-lg-2">Family Details</h2>
																	</div>
																	<div
																		class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">

																		<button type="button" class="btn btn-success"
																			onClick="addFamilyTableElement()">
																			<i class="fa fa-plus-circle"
																				aria-hidden="true"></i>Add
																			family member
																		</button>
																	</div>
																</div>
															</div>
															<form:form method="POST" action="postfamilydetails" modelAttribute="listOfFamily"
															>
																<!-- <input type="hidden" name="empId"> -->
																<table class="table table-striped table-hover">
																	<thead>
																		<tr>
																			<th>RELATIONSHIP</th>
																			<th>MEMBER NAME</th>
																			<th>DATE OF BIRTH</th>
																			<th>OCCUPATION</th>
																			<th>CONTACT NO.</th>
																			<th>DELETE</th>
																		</tr>
																	</thead>
																	<tbody id="tableOfFmailyDetails">

																		<c:forEach items="${listOfFamily}" var="family"
																			varStatus="status">
																			<div class="row">
																					<div class="col-6">${listOfFamily[status.index].relation}</div>
																					<div class="col-6">
																						${status.index}
																						${listOfFamily}
																						</div>
																			</div>
																		<tr>



																			<td>
																					<form:select class="form-select"
																						aria-label="Default select example"
																						path="listOfFamily[${status.index}].relation"
																						
																						 
																						>
																						<form:option value="SPOUSE">
																							SPOUSE
																						</form:option>
																						<form:option value="FATHER">
																							FATHER
																						</form:option>
																						<form:option value="MOTHER">
																							MOTHER
																						</form:option>
																						<form:option value="BROTHER">
																							BROTHER
																						</form:option>
																						<form:option value="SISTER">
																							SISTER
																						</form:option>
																						<form:option value="CHILD">
																							CHILD
																						</form:option>
																					</form:select>
																				</td>
																				<td>

																					<div class="input-group ">
																						<form:input type="text"
																							path="listOfFamily[${status.index}].name"
																							class="form-control"
																							placeholder="Name"
																							aria-label="Name"
																							aria-describedby="basic-addon1"
																							pattern="[a-zA-Z]{4,254}"
																							value="${family.name}" />
																					</div>

																				</td>
																				<td>
																					<form:input type="date"
																						path="listOfFamily[${status.index}].dob"
																						class="form-control"
																						aria-describedby="basic-addon1"
																						value="${family.dob}" />
																				</td>

																				<td>

																					<div class="input-group">
																						<form:input type="text"
																							path="listOfFamily[${status.index}].occupation"
																							class="form-control"
																							placeholder="Occupation"
																							aria-label="Occupation"
																							aria-describedby="basic-addon1"
																							pattern="[a-zA-Z]{4,254}"
																							value="${family.occupation}" />
																					</div>

																				</td>
																				<td>

																					<div class="input-group">
																						<form:input type="tel"
																							path="listOfFamily[${status.index}].contactno"
																							class="form-control"
																							placeholder="Phone no."
																							pattern="[5-9]{1}[0-9]{9}"
																							value="${family.contactno}" />
																					</div>
																				</td>


																				<td><a href="#deleteEmployeeModal"
																						class="delete"
																						data-toggle="modal"
																						onClick="removeTableRow(this)">
																						<i class="material-icons"
																							data-toggle="tooltip"
																							title="Delete">&#xE872;</i>
																					</a></td>
																				<td>
																					<form:hidden
																						path="listOfFamily[${status.index}].familyId" value="${family.familyId}" />
																				</td>

																			</tr>
																		</c:forEach>
																		<tr>

																			<td>
																			</td>
																		</tr>

																		<tr id="RowOfFmailyDetails">



																		</tr>
																		<tr>

																			<td>

																			</td>
																		</tr>
																	</tbody>
																	<!-- </form> -->

																</table>
															</form:form>
															<input type="submit"  />

														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
								<br>
								<br>

								<footer class="footer">
									<div class="container-fluid">
										<div class="footer-in">
											<p class="mb-0">NRI FinTech - All Rights Reserved.</p>
										</div>
									</div>
								</footer>
								<script>
									function removeTableRow(button) {
										var th = button.parentNode;
										var th1 = th.parentNode;
										var tableCount = th1.parentNode.parentNode.rows.length;

										if (tableCount > 2) {
											th1.remove();

										} else {
											alert("Ooop!! you cannot have no family details.")
										}
									}
									function addFamilyTableElement() {
										// Create a new element
										var tableOfFmailyDetails = document
											.getElementById("tableOfFmailyDetails");
										var rowOfFmailyDetails = document
											.getElementById("RowOfFmailyDetails");
										var noOfRow = tableOfFmailyDetails.rows.length;

										// Clone the last row and update its data

										var newRow = rowOfFmailyDetails.cloneNode(true);// Insert the new row into the table
										tableOfFmailyDetails.appendChild(newRow);

									}
								</script>
			</body>

			</html>