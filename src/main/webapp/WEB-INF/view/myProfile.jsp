<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <!-- Bootstrap CSS -->
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
                        rel="stylesheet"
                        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                        crossorigin="anonymous">
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





                        <div class="container-fluid ">

                            <div class="row align-items-start">

                                <div class="col-2"></div>
                                <!-- <div class="main-content">
                            <div class="row">

                                <div class="col-md-12">
                                    <div class="table-wrapper">
                                        <div class="table-title">
                                            <div class="row">
                                                <div
                                                    class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center">
                                                    <h2 class="ml-lg-2">My Profile</h2>
                                                </div> -->


                                <div class="col-9">
                                    <div class="card">
                                        <center>
                                            <h2 class="card-title">My Profile</h2>
                                        </center>
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
                                                                    <b>

                                                                        <c:if test="${employee.empName==null}">
                                                                            No name assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.empName!=null}">
                                                                            <c:out value="${employee.empName}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.gender==null}">
                                                                            No gender assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.gender!=null}">
                                                                            <c:out
                                                                                value="${fn:toUpperCase(employee.gender)}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>

                                                                        <c:if test="${employee.designation==null}">
                                                                            No designation assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.designation!=null}">
                                                                            <c:out value="${employee.designation}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.gradeLevel==null}">
                                                                            No grade level assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.gradeLevel!=null}">
                                                                            <c:out value="${employee.gradeLevel}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>


                                                                        <c:if test="${employee.doj==null}">
                                                                            No joining date assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.doj!=null}">
                                                                            <fmt:setLocale value="en_US" />

                                                                            <fmt:formatDate value="${employee.doj}"
                                                                                pattern="dd, MMMM, yyyy" var="dateofjoin" />
                                                                            <c:out value="${dateofjoin}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.emptype==null}">
                                                                            Employee type not assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.emptype!=null}">
                                                                            <c:out
                                                                                value="${fn:toUpperCase(employee.emptype)}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.probPeriod==null}">
                                                                            No probition period
                                                                        </c:if>

                                                                        <c:if test="${employee.probPeriod!=null}">
                                                                            <c:out value="${employee.probPeriod}" />
                                                                            MONTHS
                                                                        </c:if>
                                                                    </b>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td><br></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    PROBITION COMPLETE DATE :
                                                                    &nbsp;&nbsp;&nbsp;
                                                                </td>
                                                                <td>
                                                                    <b>

                                                                        <c:if test="${employee.probCompDate==null}">
                                                                            <c:out value="No joining date assigned"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.probCompDate!=null}">
                                                                            <fmt:setLocale value="en_US" />

                                                                            <fmt:formatDate
                                                                                value="${employee.probCompDate}"
                                                                                pattern="dd, MMMM, yyyy" var="probDate" />
                                                                            <c:out value="${probDate}" />

                                                                        </c:if>
                                                                    </b>
                                                                </td>
                                                            </tr>
  <tr>
                                                                <td><br></td>
                                                            </tr>

                                                            <tr>
                                                                <td>
                                                                    TEAM NAME :
                                                                </td>
                                                                <td>
                                                                    <b>


                                                                        
                                                                        <c:if test="${employee.team==null}">
                                                                            Employee team not assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.empstatus!=null}">
                                                                            <c:out
                                                                                value="${fn:toUpperCase(employee.team.teamName)}" />

                                                                        </c:if>



                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.trainPeriod==null}">
                                                                            <c:out value="No training period"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.trainPeriod!=null}">
                                                                            <c:out value="${employee.trainPeriod}" />
                                                                            MONTHS
                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>


                                                                        <c:if test="${employee.contractEndDate==null}">
                                                                            <c:out value="No contract end date assigned"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.contractEndDate!=null}">
                                                                            <fmt:setLocale value="en_US" />

                                                                            <fmt:formatDate
                                                                                value="${employee.contractEndDate}"
                                                                                pattern="dd, MMMM, yyyy"
                                                                                var="contractDate" />
                                                                            <c:out value="${contractDate}" />

                                                                        </c:if>

                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.servPeriod==null}">
                                                                            <c:out value="No service period"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.servPeriod!=null}">
                                                                            <c:out value="${employee.servPeriod}" />
                                                                            MONTHS
                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.workEmail==null}">
                                                                            <c:out value="Work Email not set"></c:out>
                                                                        </c:if>

                                                                        <c:if test="${employee.workEmail!=null}">
                                                                            <c:out value="${employee.workEmail}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.branch==null || employee.branch.equals('')}">
                                                                            Employee branch not assigned
                                                                        </c:if>

                                                                        <c:if test="${employee.branch!=null }">
                                                                            <c:out
                                                                                value="${fn:toUpperCase(employee.branch)}" />

                                                                        </c:if>
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.office==null || employee.office.equals('')}">
                                                                            <c:out value="Employee office name not assigned"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.office!=null}">
                                                                            <c:out
                                                                                value="${employee.office}" />
                                                                        </c:if>
                                                                        
                                                                    </b>
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
                                                                    <b>
                                                                        <c:if test="${employee.workstationId==null || employee.workstationId.equals('')}">
                                                                            <c:out value="Workstation not assigned"/>
                                                                        </c:if>

                                                                        <c:if test="${employee.workstationId!=null}">
                                                                            <c:out value="${employee.workstationId}" />
                                                                        </c:if>
                                                                    </b>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td><br></td>
                                                            </tr>

                                                            <tr>
                                                                <td>
                                                                    CTC :
                                                                </td>
                                                                <td>
                                                                    <b>

                                                                        <c:if test="${employee.ctc==null}">
                                                                            <c:out value="CTC not assigned" />
                                                                        </c:if>
                                                                        <c:if test="${employee.ctc!=null}">
                                                                            <c:out value="${employee.ctc}" />
                                                                            lakhs
                                                                        </c:if>

                                                                    </b>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td><br></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    DEPARTMENT :
                                                                </td>
                                                                <td>
                                                                    <b>

                                                                        <c:if test="${employee.department==null}">
                                                                            <c:out value="Department not assigned" />
                                                                        </c:if>
                                                                        <c:if test="${employee.department!=null}">
                                                                            <c:out
                                                                                value="${fn:toUpperCase(employee.department)}" />
                                                                        </c:if>
                                                                    </b>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2"></div>
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