<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="/resources/user-css/user.css">
<title>Qualification details</title>
</head>

<body>
	<%@ include file="/resources/defaultHtml/dashboardHeader.jsp"%>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="/resources/javascript/user.js"></script>


	<script>
		function removeTableRow(button) {
			var th = button.parentNode;
			var th1 = th.parentNode;
			var tableCount = th1.parentNode.parentNode.rows.length;

			if (tableCount > 2) {
				th1.remove();

			} else {
				alert("Ooop!! you cannot have no Qualification details.")
			}
		}
		function addQualificationTableElement() {
			// Create a new element
			var tableOfQualificationDetails = document
					.getElementById("tableOfQualificationDetails");
			var rowOfQualificationDetails = document
					.getElementById("RowOfQualificationDetails");
			var noOfRow = tableOfQualificationDetails.rows.length;

			// Clone the last row and update its data

			var newRow = rowOfQualificationDetails.cloneNode(true);// Insert the new row into the table
			tableOfQualificationDetails.appendChild(newRow);

		}
	</script>
	

	<nav class="navbar navbar-light ">
		<div class="container-fluid">
			<!-- <a class="navbar-brand" href="#">Offcanvas dark navbar</a>
             -->
			<h1></h1>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
				aria-controls="offcanvasDarkNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="offcanvas offcanvas-end dark" tabindex="-1"
				id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
				<div class="offcanvas-header">
					<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Dark
						offcanvas</h5>
					<button type="button" class="btn-close btn-close-dark"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
						<li class="nav-item"><a href="<%=request.getContextPath()%>profile">My Profile</a></li>
						<br>
						<li class="nav-item"><a href="<%=request.getContextPath()%>personal">Personal details</a></li>
						<br>
						<li class="nav-item"><a href="<%=request.getContextPath()%>family">My Family</a></li>
						<br>
						<li class="nav-item"><a href="<%=request.getContextPath()%>qualification">Qualification</a></li>
						<br>
						<li class="nav-item"><a href="<%=request.getContextPath()%>professional">Professional Details</a></li>
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
							<form id="qualificationForm">



								<div class="main-content">
									<div class="row">

										<div class="col-mb-12">
											<div class="table-wrapper">
												<div class="table-title">
													<div class="row">
														<div
															class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center">
															<h2 class="ml-lg-2">Qualification Details</h2>
														</div>
														<div
															class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">

															<button type="button" class="btn btn-success"
																onClick="addQualificationTableElement()">
																<i class="fa fa-plus-circle" aria-hidden="true"></i>Add
															Qualification
															</button>
														</div>
													</div>
												</div>
												<table class="table table-striped table-hover">
													<thead>
														<tr>
															<th>SAVE</th>
															<th>DEGREE NAME</th>
															<th>DOCUMENT PROVE</th>
															<th>PERCENTAGE</th>
															<th>DELETE</th>
														</tr>
													</thead>
													<tbody id="tableOfQualificationDetails">
														<tr id="RowOfQualificationDetails">
															<td><button type="submit" class="btn btn-primary">SAVE</button></td>
															<td>

																<div class="input-group ">
																	<input type="text" class="form-control"
																		placeholder="Degree Name" aria-label="Degree Name"
																		aria-describedby="basic-addon1"
																		pattern="[a-zA-Z]{2,50}" required>
																</div>

															</td>
															<td>

																<div class="input-group mb-3">
																	<input type="file" class="form-control"
																		id="inputGroupFile02"> <label
																		class="input-group-text" for="inputGroupFile02"
																		required>Upload</label>
																</div>


															</td>

															<td>

																<div class="input-group">
																	<input type="number" class="form-control"
																		placeholder="Occupation" aria-label="Occupation"
																		aria-describedby="basic-addon1"
																		min="0" max="100" required>
																</div>

															</td>
															<td><a href="#deleteEmployeeModal" class="delete"
																data-toggle="modal" onClick="removeTableRow(this)">
																	<i class="material-icons" data-toggle="tooltip"
																	title="Delete">&#xE872;</i>
															</a></td>
														</tr>
													</tbody>
												</table>
							</form>



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
</body>

</html>

