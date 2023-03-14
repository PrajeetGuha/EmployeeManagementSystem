<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
        <title>Dashboard</title>

    </head>

    <body>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
        <script src="/resources/javascript/user.js"></script>


        <%@ include file="/resources/defaultHtml/adminDashboardHeader.jsp" %>

            <br>
            <br>


            <div class="container-fluid ">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-10">
                        <form method="POST" action="editemployee" modelAttribute="employeeinfo">

                            <div class="card">
                                <div class="card-body">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col">


                                                <table>
                                                    <tr>
                                                        <td>
                                                            NAME :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="NAME" aria-label="NAME" name="empName"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z ]{4,50}"
                                                                    value="${employee.empName}">
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            GENDER :
                                                        </td>
                                                        <td>
                                                            <select class="form-select"
                                                                aria-label="Default select example" name="gender">
                                                                <option value="female" selected>FEMALE</option>
                                                                <option value="male">MALE</option>
                                                                <option value="other">OTHER</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            DESIGNATION :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="DESIGNATION" aria-label="DESIGNATION"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z0-9 ]{1,50}" name="designation"
                                                                    value="${employee.designation}">
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            GRADE_LEVEL :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="GRADE LEVEL" aria-label="GRADE LEVEL"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z0-9]{1,2}" name="gradeLevel"
                                                                    value="${employee.gradeLevel}" />
                                                            </div>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            DATE OF JOINING :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="date" class="form-control"
                                                                    placeholder="Date of Joining"
                                                                    aria-label="Date of Joining"
                                                                    aria-describedby="basic-addon1" name="doj"
                                                                    value="${employee.doj}" />
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>




                                                    <tr>
                                                        <td>
                                                            EMPLOYEE TYPE :
                                                        </td>
                                                        <td>
                                                            <select class="form-select"
                                                                aria-label="Default select example" name="emptype">
                                                                <option values="${employee.emptype}">
                                                                <option value="full time" selected>FULL TIME</option>
                                                                <option value="intern">INTERN</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            EMPLOYEE STATUS :
                                                        </td>
                                                        <td>
                                                            <select class="form-select"
                                                                aria-label="Default select example" name="empstatus">
                                                                <option value="active" selected>ACTIVE</option>
                                                                <option value="inactive">INACTIVE</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            PROBITION PERIOD :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="number" class="form-control"
                                                                    placeholder="Probition period"
                                                                    aria-label="Probition period"
                                                                    aria-describedby="basic-addon1" name="probPeriod"
                                                                    value="${employee.probPeriod}" />
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            PROBITION COMPLETE DATE : &nbsp;&nbsp;&nbsp;
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="date" class="form-control"
                                                                    placeholder="Probition completion date"
                                                                    aria-label="Probition completion date"
                                                                    aria-describedby="basic-addon1" name="probCompDate"
                                                                    value="${employee.probCompDate}" />
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>

                                                        </td>
                                                    </tr>

                                                </table>

                                            </div>
                                            <div class="col">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            TRAINING PERIOD :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="number" class="form-control"
                                                                    placeholder="Training period"
                                                                    aria-label="Training period"
                                                                    aria-describedby="basic-addon1" name="trainPeriod"
                                                                    value="${employee.trainPeriod}" />
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            CONTRACT END DATE :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="date" class="form-control"
                                                                    placeholder="Contract end date"
                                                                    aria-label="Contract end date"
                                                                    aria-describedby="basic-addon1"
                                                                    name="contractEndDate"
                                                                    value="${employee.contractEndDate}" />
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            SERVICE PERIOD :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="number" class="form-control"
                                                                    placeholder="Service period"
                                                                    aria-label="Service period"
                                                                    aria-describedby="basic-addon1" name="servPeriod"
                                                                    value="${employee.servPeriod}" />
                                                            </div>
                                                        </td>
                                                    </tr>


                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            WORK EMAIL :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="email" class="form-control"
                                                                    placeholder="EMAIL" aria-label="EMAIL"
                                                                    aria-describedby="basic-addon1" name="workEmail"
                                                                    value="${employee.workEmail}">
                                                            </div>
                                                        </td>
                                                    </tr>


                                                    <tr>
                                                        <td><br></td>
                                                    </tr>


                                                    <tr>
                                                        <td>
                                                            BRANCH :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="BRANCH" aria-label="BRANCH"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z]{4,50}" name="branch"
                                                                    value="${employee.branch}">
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            OFFICE :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="OFFICE" aria-label="OFFICE"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z]{4,50}" name="office"
                                                                    value="${employee.office}">
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            WORKSTATION ID :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="text" class="form-control"
                                                                    placeholder="WORKSTATION ID"
                                                                    aria-label="WORKSTATION ID"
                                                                    aria-describedby="basic-addon1"
                                                                    pattern="[a-zA-Z0-9]{1,5}" name="workstationId"
                                                                    value="${employee.workstationId}">
                                                            </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>

                                                    <tr>
                                                        <td><br></td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            CTC :
                                                        </td>
                                                        <td>
                                                            <div class="input-group ">
                                                                <input type="number" class="form-control"
                                                                    placeholder="CTC"
                                                                    aria-label="CTC"
                                                                    aria-describedby="basic-addon1" name="ctc"
                                                                    value="${employee.ctc}" />
                                                            </div>
                                                        </td>
                                                    </tr>


                                                    <tr>
                                                        <td><input type="hidden" name="empId" value="${employee.empId}">
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td><br><br><br></td>
                                                    </tr>

                                                </table>
                                                <br>
                                                <input type="submit" class="btn btn-primary">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>


                        </form>
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


    <!-- EMP_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EMPNAME VARCHAR(50),
    GENDER ENUM ('M','F','O'),
    GRADE_LEVEL VARCHAR(2),
    DOJ DATE,
    DESIGNATION VARCHAR(50),
    DEPT_ID REFERENCES DEPARTMENT(DEPT_ID),
    GRP_ID REFERENCES TEAM(GRP_ID),
    EMP_DET_ID REFERENCES EMPLOYEEDETAILS(EMP_DET_ID),
    USER_ID REFERENCES USER(USER_ID) -->