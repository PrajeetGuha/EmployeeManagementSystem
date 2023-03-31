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
<title>EMPLOYEE DASHBOARD</title>
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



</head>

<c:set var="pageNo" value="${pageNo}" />
<c:set var="pageCount" value="${pageCount}" />
</head>


<body>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="/resources/javascript/user.js"></script>



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
				<li><a href="dashboard" class="dashboard"><i
						class="material-icons">dashboard</i> <span>Dashboard</span></a></li>
				<li><a href="leaveApplication?pg=1"> <i
						class="material-icons">playlist_add_check</i>Apply Leave

				</a></li>


				<li><a href="resign"> <i class="material-icons">directions_walk</i>Apply
						Resignation



				</a></li>

				<li class="active"><a href="employeepersonaldetails"> <i
						class="material-icons">contact_mail</i>Personal Details <!-- <span>Personal Details</span></a></li> -->


								<li><a href="professionaldetails">
									<i class="material-icons">work</i> 
										<span>Professional Details</span></a></li>

								<li><a href="qualificationdetails">
										<!-- <i class='fas fa-user-alt-slash' >Resignation</i> -->
										<i class="material-icons">school</i> 
										<span>Qualification Details</span></a></li>
								<li><a href="familyDetails"><i class="material-icons">group</i>
										<span>Family Details</span></a></li>



			</ul>


		</nav>
	</div>




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
				<h4 class="page-title">Personal Details</h4>
				<!--  <ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Booster</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol>-->
			</div>

		</div>


<br><br>

		<div class="container-fluid ">

			<div class="row align-items-start">
			<div class="col-1"></div>

				<div class="col-10">

					<div class="card">
						<center>
							<h2 class="card-title" style="background:linear-gradient(120deg, #0cf9e5, #5a84eb)">Personal Details</h2>
						</center>
						<div class="card-body" style=" background-color: lightcyan">
							<div class="container-fluid">
								<div class="row">
									<div class="col">
										<form method="post" action="personaldetailsofemployee"
											modelAttribute="emppersonaldetails">
											<div class="input-group mb-3">
												<span class="input-group-text" id="basic-addon1">Primary
													contact no</span> <input type="" class="form-control"
													placeholder="Phone number" aria-label="Phone number"
													aria-describedby="basic-addon1" pattern="[5-9]{1}[0-9]{9}"
													name="primaryContactno"
													value="${employeeinfomation.primaryContactno}" required
													oninvalid = "setCustomValidity('Not a valid phone number')"
													oninput = "setCustomValidity('')">
											</div>
											<br>

											<div class="input-group mb-3">
												<span class="input-group-text" id="basic-addon1">Emergency
													contact no</span> <input type="tel" class="form-control"
													placeholder="Phone number" aria-label="Phone number"
													aria-describedby="basic-addon1" pattern="[5-9]{1}[0-9]{9}"
													name="emergencyContactno"
													value="${employeeinfomation.emergencyContactno}" required>
											</div>



											<br>


											<div class="input-group">
												<span class="input-group-text">Email Id</span> <input
													type="email" class="form-control" placeholder="Email id"
													name="emailId" value="${employeeinfomation.emailId}"
													required></input>
											</div>
											<br>


											<div class="input-group">
												<span class="input-group-text">Permanent address</span> <input
													type="text" class="form-control"
													placeholder="Permanent address" name="permaAddress"
													value="${employeeinfomation.permaAddress}"
													pattern="[a-zA-Z0-9_-\@#&()+=\\\:',]{10,254}" required></input>
											</div>
											<br>

											<div class="input-group">
												<span class="input-group-text">Present address</span> <input
													type="text" class="form-control"
													placeholder="Present address" name="presentAddress"
													value="${employeeinfomation.presentAddress}"
													pattern="[a-zA-Z0-9_-\@#&()+=\\\:',]{10,254}" required></input>
											</div>
											<br>


											<div class="input-group">
												<span class="input-group-text">Nationality</span> <input
													type="text" class="form-control" placeholder="Nationality "
													pattern="[a-zA-Z0-9_-]{5,20}" name="nationality"
													value="${employeeinfomation.nationality}" required></input>
											</div>
											<br>

											<div class="input-group">
												<span class="input-group-text">Blood Group</span> <input
													type="text" class="form-control" placeholder="Blood Group "
													pattern="[a-zA-Z0-9_-]{1,3}" name="bloodGrp"
													value="${employeeinfomation.bloodGrp}" required></input>
											</div>
											<br>
											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default">Adhaar number</span> <input
													type="text" class="form-control"
													aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default"
													pattern="[1-9]{1}[0-9]{11}" name="adhaarno"
													value="${employeeinfomation.adhaarno}" placeholder="Adhaar number" required>
											</div>

											<br>

											

											

											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default">Pan number</span> <input
													type="text" class="form-control"
													aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default"
													pattern="[1-9]{1}[0-9]{9}" name="pancardnno"
													value="${employeeinfomation.pancardnno}" placeholder="Pan number" required>
											</div>
											<br>

											
											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default">Passport number</span> <input
													type="text" class="form-control"
													aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default"
													pattern="[1-9]{1}[0-9]{9}" name="passportno"
													value="${employeeinfomation.passportno}" placeholder="Passport number" required>
											</div>
											<br>
											

											<div class="input-group mb-3">
												<label class="input-group-text" for="inputGroupFile01">Marital
													status</label>

												<c:set var="maritalstatus"
													value="${['married', 'single', 'divorced']}" />
												<!-- ${employee.maritalStatus} -->
												<select class="form-select"
													aria-label="Default select example" name="maritalStatus">
													<c:forEach items="${maritalstatus}" var="option">
														<c:if test="${option!=employeeinfomation.maritalStatus}">
															<!-- <option value="${option}" ><b>${fn:toUpperCase(option)}</b></option> -->
															<option value="${option}"><b>${fn:toUpperCase(option)}</b></option>



														</c:if>
														<c:if test="${option==employeeinfomation.maritalStatus}">
															<!-- <option value="${option}" ><b>${fn:toUpperCase(option)}</b></option> -->
															<option value="${option}" selected><b>${fn:toUpperCase(option)}</b></option>



														</c:if>

													</c:forEach>
												</select>

											</div>
											<input type="hidden" name="empDetId"
												value="${employeeinfomation.empDetId}">
											<!-- <input type="hidden" name="empDetId" value="${employeeinfomation.empDetId}"> -->
											<center>
												<input type="submit" class="btn btn-primary">
												</button>
											</center>
										</form>


									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-1"></div>
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

	<script>
						function validateForm() {
							let x = document.forms["myForm"]["fname"].value;
							if (x == "") {
								alert("Name must be filled out");
								return false;
							}
						}
					</script>

</html>
