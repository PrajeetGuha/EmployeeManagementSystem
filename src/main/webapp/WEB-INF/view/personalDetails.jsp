



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

    </head>

    <body>
        <%@ include file="/resources/defaultHtml/dashboardHeader.jsp" %> 

            <!-- Option 1: Bootstrap Bundle with Popper -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
            <script src="/resources/javascript/user.js"></script>


            <center>
                <h1>Personal Details</h1>
            </center>
            <br>

           
            <div class="container-fluid ">

                <div class="row align-items-start">
                    <div class="col-1 ">

                    </div>

                    <div class="col-2"></div>

                    <div class="col-8">
                        <div class="card">
                            <div class="card-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col">
                                            <form>
                                                <div class="input-group mb-3">
                                                    <span class="input-group-text" id="basic-addon1">Primary contact
                                                        no</span>
                                                    <input type="tel" class="form-control" placeholder="Phone number"
                                                        aria-label="Phone number" aria-describedby="basic-addon1"
                                                        pattern="[5-9]{1}[0-9]{9}" name="primaryContactno" required>
                                                </div>
                                                <br>

                                                <div class="input-group mb-3">
                                                    <span class="input-group-text" id="basic-addon1">Emergency contact
                                                        no</span>
                                                    <input type="tel" class="form-control" placeholder="Phone number"
                                                        aria-label="Phone number" aria-describedby="basic-addon1"
                                                        pattern="[5-9]{1}[0-9]{9}" name="emergencyContactno" required>
                                                </div>



                                                <br>


                                                <div class="input-group">
                                                    <span class="input-group-text">Email Id</span>
                                                    <input type="email" class="form-control" placeholder="Email id"
                                                        pattern="[a-zA-Z0-9_-]{2,30}" name="emailId" required></input>
                                                </div>
                                                <br>


                                                <div class="input-group">
                                                    <span class="input-group-text">Permanent address</span>
                                                    <input type="text" class="form-control"
                                                        placeholder="Permanent address" name="permaAddress" pattern="[a-zA-Z0-9_- ]{10,254}"
                                                        required></input>
                                                </div>
                                                <br>

                                                <div class="input-group">
                                                    <span class="input-group-text">Present address</span>
                                                    <input type="text" class="form-control"
                                                        placeholder="Present address" name="presentAddress" pattern="[a-zA-Z0-9_-]{10,254}"
                                                        required></input>
                                                </div>
                                                <br>


                                                <div class="input-group">
                                                    <span class="input-group-text">Nationality</span>
                                                    <input type="text" class="form-control" placeholder="Nationality "
                                                        pattern="[a-zA-Z0-9_-]{5,20}" name="nationality" required></input>
                                                </div>
                                                <br>

                                                <div class="input-group">
                                                    <span class="input-group-text">Blood Group</span>
                                                    <input type="text" class="form-control" placeholder="Blood Group "
                                                        pattern="[a-zA-Z0-9_-]{1,3}" name="bloodGrp" required></input>
                                                </div>
                                                <br>
                                                <div class="input-group mb-3">
                                                    <span class="input-group-text"
                                                        id="inputGroup-sizing-default">Passport number</span>
                                                    <input type="text" class="form-control"
                                                        aria-label="Sizing example input"
                                                        aria-describedby="inputGroup-sizing-default"
                                                        pattern="[1-9]{1}[0-9]{10}" name="passportno" required>
                                                </div>

                                                <br>

                                                <div class="input-group mb-3">
                                                    <label class="input-group-text" for="inputGroupFile01">Adhaar Card
                                                    </label>
                                                    <input type="file" class="form-control" id="inputGroupFile01">
                                                </div>

                                                <br>

                                                <div class="input-group mb-3">
                                                    <span class="input-group-text" id="inputGroup-sizing-default">Pan
                                                        number</span>
                                                    <input type="text" class="form-control"
                                                        aria-label="Sizing example input"
                                                        aria-describedby="inputGroup-sizing-default" pattern="[1-9]{1}[0-9]{9}" name="pancardnno" required>
                                                </div>
                                                <br>

                                                <div class="input-group mb-3">
                                                    <label class="input-group-text" for="inputGroupFile01">Pan Card
                                                    </label>
                                                    <input type="file" class="form-control" id="inputGroupFile01">
                                                </div>
                                                <br>
                                                <div class="input-group mb-3">
                                                    <span class="input-group-text"
                                                        id="inputGroup-sizing-default">Passport number</span>
                                                    <input type="text" class="form-control"
                                                        aria-label="Sizing example input"
                                                        aria-describedby="inputGroup-sizing-default" pattern="[1-9]{1}[0-9]{9}" name="passportno" required>
                                                </div>
                                                <br>
                                                <div class="input-group mb-3">
                                                    <label class="input-group-text" for="inputGroupFile01">Passport
                                                        Document </label>
                                                    <input type="file" class="form-control" id="inputGroupFile01">
                                                </div>
                                                <br>
                                                <center><input type="submit" class="btn btn-primary"></button></center>
                                            </form>


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

                                        </div>
                                    </div>

                                </div>
                            </div>
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
                <script>

                    function validateForm() {
                        let x = document.forms["myForm"]["fname"].value;
                        if (x == "") {
                            alert("Name must be filled out");
                            return false;
                        }
                    }
                </script>
    </body>

    </html>
