<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Customer</title>
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

<form class="container" action="consumer" style="max-width:50%;">
<h3>Set up your Account</h3>
<span style="color:red;">${cuserror}</span>
<div class="form-row">
    <div class="form-group col-md-6">
      <label for="name">Name</label>
      <input type="text" class="form-control" name="name" required>
    </div>
   
    <div class="form-group col-md-6">
      <label for="email">Email</label>
      <input type="email" class="form-control" name="email" required>
    </div>
    <div class="form-group col-md-6">
      <label for="mobile">Mobile</label>
      <input type="mobile" class="form-control" name="mobile" required>
    </div>
    
    <div class="form-group col-md-6">
      <label for="mobile">DOB</label>
      <input type="dob" class="form-control" name="dob" required>
    </div>
    
  </div>
  <div class="form-group col-md-6">
    <label for="inputAddress">House No</label>
    <input type="text" class="form-control" name="hno" required>
  </div>
  <div class="form-group col-md-6">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control" name="add1" required>
  </div>
  <div class="form-group col-md-6">
    <label for="inputAddress2">Address 2</label>
    <input type="text" class="form-control" name="add2" required> 
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" name="city" required>
    </div>
    
    <div class="form-group col-md-2">
      <label for="inputZip">Zip</label>
      <input type="text" class="form-control" name="pincode" required>
    </div>
  </div>
  <div>
  <br>
  <button type="submit" class="btn btn-primary">Create an Account</button>
  </div>
</form>
<div class='container' style='max-width:50%;'>
<br>
<h4><button class='btn btn-primary' onclick='history.back()'>Back</button></h4>
</div>
</body>
</html>