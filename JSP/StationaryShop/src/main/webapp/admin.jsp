<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
     	Stationary Shop
    </a>
    <a class="navbar-brand navbar-right" href="index.jsp">Log Out</a>
    
  </div>
</nav>
<div class='container'>
<table class='table table-striped'>
<tr><th><h4>Welcome Admin</h4></th><td></td>
</tr><tr><th>&emsp;</th><td></td></tr>
<tr><th>See all Customers</th><td><a class='btn btn-primary' href="allcus">Customers</a></td></tr>
<tr><th>See all Product</th><td><a class='btn btn-primary' href="allprod">Product</a></td></tr>
<tr><th>Register Product or Change stock</th><td><a href="newproduct.jsp" class="btn btn-primary">Register Product</a></td></tr>
<tr><th>See issues of Customer</th><td><a class='btn btn-primary' href="unsolved">Check customer's Issue</a></td></tr>
<tr><th>See solved issue of Customers</th><td><a class='btn btn-primary' href="solved">Solved Issue</a></td></tr>
</table>
</div>
</body>
</html>