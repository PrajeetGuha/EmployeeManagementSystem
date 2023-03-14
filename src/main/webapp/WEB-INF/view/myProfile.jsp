<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    </head>

    <body>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
        <script src="/resources/javascript/user.js"></script>


<%@ include file="/resources/defaultHtml/dashboardHeader.jsp" %> 

        <br>
        <br>





        <div class="container-fluid ">

            <div class="row align-items-start">
                <div class="col-1 ">

                    <div class="card " style="width: 15rem;">

                        <div class="cardside">
                            <img src="/resources/image/profile.jpg" class="card-img-top">
                            <b>
                                <h4 class="card-title">&nbsp;&nbsp;John moosewala</h4>
                            </b>
                            <div class="container">
                                <div class="card-body">
                                    <div class="input-group mb-3">
                                        <input type="file" class="form-control" id="inputGroupFile02">
                                        <label class="input-group-text" for="inputGroupFile02">Image</label>
                                      </div>
                                    <div class="row">
                                        <p class="card-text">
                                            
                                        <div class="col-6">
                                            <i class="fa fa-phone" style="font-size:24px;color:black"></i>
                                        </div>
                                        <div class="col-6">
                                            <i class="fa fa-envelope" style="font-size:24px;color:black"></i>
                                        </div>
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="col-2"></div>
                
                <div class="col-8">
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
                                        <b><c:out value="${employee.empName}"/> </b>
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
                                        <b><c:out value="${employee.gender}"/> </b>
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
                                        <b><c:out value="${employee.designation}"/> </b>
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
                                        <b><c:out value="${employee.gradeLevel}"/> </b>
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
                                        <b><c:out value="${employee.doj}"/></b>
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
                                        <b><c:out value="${employee.emptype}"/></b>
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
                                        <b><c:out value="${employee.empstatus}"/> </b>
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
                                        <b><c:out value="${employee.probPeriod}"/> MONTHS </b>
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
                                        <b><c:out value="${employee.probCompDate}"/>  </b>
                                    </td>
                                </tr>

                                <tr>
                                    <td><br></td>
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
                                    <b><c:out value="${employee.trainPeriod}"/>  MONTHS </b>
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
                                            <b><c:out value="${employee.contractEndDate}"/> </b>
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
                                            <b><c:out value="${employee.servPeriod}"/>  MONTHS </b>
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
                                            <b><c:out value="${employee.workEmail}"/> </b>
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
                                            <b><c:out value="${employee.branch}"/>  </b>
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
                                            <b><c:out value="${employee.office}"/>  </b>
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
                                            <b><c:out value="${employee.workstationId}"/> </b>
                                        </td>
                                    </tr>
    
                                    <tr>
                                        <td><br></td>
                                    </tr>
    
                                    <tr>
                                        <td>
                                            CTC  :
                                        </td>
                                        <td>
                                            <b><c:out value="${employee.ctc}"/> </b>
                                        </td>
                                    </tr>
                                </table>
                                </div>
                            </div>

</div></div>
                        </div>
                        <div class="col-2"></div>
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