<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  </head>
  <body>
  
  <nav class="navbar navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
     	Stationary Shop
    </a>
  </div>
</nav>
  <form class="container" style="max-width:50%" method="post" action="login">
  	<div class="mb-3">
  	<h1>Login</h1>
  	<span style="color:red;">${logerror}</span>
  	</div>
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Mobile No:</label>
      <input type="text" name="mobile" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password(Enter DOB as your password)</label>
      <input type="password" name="dob" class="form-control" id="exampleInputPassword1">
    </div>
    <div class="mb-3">
    <button type="submit" class="btn btn-primary">Submit</button>
    <a href="newcustomer.jsp" class="btn btn-primary">Sign Up</a>
    </div>
  </form>
  
  </body>
</html>