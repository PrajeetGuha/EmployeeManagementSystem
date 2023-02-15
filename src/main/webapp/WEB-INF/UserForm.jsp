<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="chocolate">
<form:form action="userInfo" modelAttribute="user">
   Name: <form:input path="name" />
   Gender:  <form:radiobuttons path="gender" items="${gender}"/>
          
    Country:<form:select path="country" items="${country}"></form:select>
    
   <input type="submit" value="submit">
</form:form>
</body>
</html>