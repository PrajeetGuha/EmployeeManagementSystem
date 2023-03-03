<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="resources/css/welcome.css" rel="stylesheet" type="text/css">
 
  <title>NRI FINTECH</title>
</head>
<body>
<nav>
   <div class="logo"><img src="resources/logo.png"/></div>
   <!-- toggle menu button -->
   <span class="menubtn" onclick="openNav()">&#9776;</span>
 
   <div class="navLinks">
     <ul>
       <li><a href="welcome">Home</a></li>
       <li><a href="#">About</a></li>
       <!--<li><a href="#">Services</a></li>-->
       <li><a href="#">Contact</a></li>
       
     </ul>
   </div>
 </nav>
   <!-- responsive side navbar -->
   <div class="sideNav" id="sidenav">
     <a href="#" class="closeBtn" onclick="closeNav()"> &#10006;</a>
     <a href="#">Home</a>
     <a href="#">About</a>
     <!-- <a href="#">Services</a> -->
     <a href="#">Contact</a>
     
   </div>
   
   <!-- Header content with banner image -->
   <div class="row">
     <div class="column1">
       <h1>Employee Management System</h1><br>
       <button  class="login-btn" type="button" onclick="window.location.href = 'login';">LOGIN</button>
     </div>
     <div class="column2">
       <img src="https://www.hmablogs.com/wp-content/uploads/2022/06/banner-img.png" alt="banner" width="500px">
     </div>
   </div>
   
   <!-- javascript to make side menu appear -->
 <script>
   function openNav() {
     document.getElementById("sidenav").style.width = "50%";
   }
   function closeNav() {
     document.getElementById("sidenav").style.width = "0%";
   }
 </script>
 
</body>
</html>