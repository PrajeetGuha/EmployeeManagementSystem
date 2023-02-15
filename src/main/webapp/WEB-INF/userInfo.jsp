<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="green">
${usr }<br>
<table border="2px">
  <tr>
    <td>Name</td>
    <td>Gender</td>
    <td>Country</td>
  </tr>
  <tr>
   <td>${usr.name }</td>
    <td>${usr.gender }</td>
     <td>${usr.country }</td>
  </tr>
</table>

</body>
</html>