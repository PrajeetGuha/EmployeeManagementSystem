<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
<link rel="stylesheet"
	href="../resources/custom/css/admin-dashboard/form.css">
</head>
<body>
<div class="form">
	<form>
      <div class="title">WELCOME</div>
      <div class="subtitle">Add new employee</div>
      <div class="input-container ic1">
        <input id="name" name="name" modelattribute="newuser" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        <label for="name" class="placeholder">Name</label>
      </div>
      <div class="input-container ic2">
        <input id="email" name="pemail" modelattribute="newuser" class="input" type="text" placeholder=" " />
        <div class="cut cut-short"></div>
        <label for="email" class="placeholder">Personal Email</>
      </div>
        <div class="input-container ic2">
        <input id="email" name="wemail" modelattribute="newuser" class="input" type="text" placeholder=" " />
        <div class="cut cut-short"></div>
        <label for="email" class="placeholder">Work Email</>
      </div>
      <div class="input-container ic2">
        <input id="username" name="username" modelattribute="newuser" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        <label for="username" class="placeholder">Username</>
      </div>
        <div class="input-container ic2">
        <input id="password" name="password" modelattribute="newuser" class="input" type="password" placeholder=" " />
        <div class="cut"></div>
        <label for="password" class="placeholder">Password</>
      </div>
      <button type="text" class="submit">submit</button>
      </form>
    </div>
</body>
</html>