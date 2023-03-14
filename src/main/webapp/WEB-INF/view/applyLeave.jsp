<!-- EMP_DET_ID INT PRIMARY KEY AUTO_INCREMENT ON DELETE CASCADE,
PROFILE_PIC_DOC VARCHAR(255),
PERMA_ADDR VARCHAR(100),
PRIMARY_CONTACTNO VARCHAR(10),
EMERGENCY_CONTACTNO VARCHAR(10),
EMAIL_ID VARCHAR(30),
DOB DATE,
PRESENT_ADDR VARCHAR(100),
NATIONALITY VARCHAR(20),
BLOOD_GRP VARCHAR(3),
PANCARD_DOC VARCHAR(255),
PANCARDNO VARCHAR(10),
ADHAAR_DOC VARCHAR(255),
ADHAARNO VARCHAR(12),
PASSPORT_DOC VARCHAR(255),
PASSPORTNO VARACHAR(10)
); -->



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

<title>Apply Leave</title>
</head>

<body>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="/resources/javascript/user.js"></script>



	<%@ include file="/resources/defaultHtml/dashboardHeader.jsp"%>
	<nav class="navbar navbar-light ">
		<div class="container-fluid">*/
			<!-- <a class="navbar-brand" href="#">Offcanvas dark navbar</a>
             -->

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
						<li class="nav-item">My Profile</li>
						<br>
						<li class="nav-item">Personal details</li>
						<br>
						<li class="nav-item">My Family</li>
						<br>
						<li class="nav-item">Qualification</a></li>
						<br>
						<li class="nav-item">Professional
								Details</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
<!-- 
    LEAVE_DATE DATE,
    IS_APPROVED ENUM('Y','N'),
    APPROVAL_DONE_BY_ID REFERENCES ADMIN(ADMIN_ID),
    LEAVE_TYPE ENUM('CL','PL','SL','OL')
    LEAVE_REASON VARCHAR(255), -->

	<div class="container-fluid ">

		<div class="row align-items-start">
			<div class="col-1 "></div>


			<div class="col-12">
				<div class="container-fluid">
					<div class="row">
						<div class="col">
							<form id="familyForm">



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
																onClick="addLeaveTableElement()">
																<i class="fa fa-plus-circle" aria-hidden="true"></i>New Leave
															</button>
														</div>
													</div>
												</div>
												<table class="table table-striped table-hover">
													<thead>
														<tr>
															<th>APPLY</th>
															<th>LEAVE TYPE</th>
															<th>LEAVE REASON</th>
															<th>LEAVE DATE</th>
															<th>IS APPROVED</th>
														</tr>
													</thead>
													<tbody id="tableOfLeaveDetails">
														<tr id="RowOfLeaveDetails">
															<td><button type="submit" class="btn btn-success">APPLY</button></td>
															<td><select class="form-select"
																aria-label="Default select example">
																	<option value="SL">SL</option>
																	<option value="PL" >PL</option>
																	<option value="CL" selected>CL</option>
																	<option value="OL" >OL</option>
															</select></td>
                                                            <td>

																<div class="input-group ">
																	<input type="text" class="form-control"
																		placeholder="REASON" aria-label="REASON"
																		aria-describedby="basic-addon1"
																		pattern="[a-zA-Z]{4,254}" required>
																</div>

															</td>
                                                            
															<td><input type="date" class="form-control"
																aria-describedby="basic-addon1" required></td>
															

                                                                <td><select class="form-select"
                                                                    aria-label="Default select example">
                                                                        <option value="YES">YES</option>
                                                                        <option value="NO" selected>NO</option>
                                                                </select></td>

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
		function addLeaveTableElement() {
			// Create a new element
			var tableOfFmailyDetails = document
					.getElementById("tableOfLeaveDetails");
			var rowOfFmailyDetails = document
					.getElementById("RowOfLeaveDetails");
			var noOfRow = tableOfFmailyDetails.rows.length;

			// Clone the last row and update its data

			var newRow = rowOfFmailyDetails.cloneNode(true);// Insert the new row into the table
			tableOfFmailyDetails.appendChild(newRow);

		}
	</script>
</body>

</html>

