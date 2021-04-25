<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Customer" %>
<%

if (request.getParameter("cname") != null)
{
	if (request.getParameter("cname") != null)
	 {
	 Customer CustomerServiceObj = new Customer();
	 String stsMsg= CustomerServiceObj.customerdetails(request.getParameter("cname"),
	 request.getParameter("cphone"),
	 request.getParameter("cgender"),
	 request.getParameter("cemail"));
	 session.setAttribute("statusMsg", stsMsg);
	
	 } 
}

//Delete item----------------------------------
if (request.getParameter("cID") != null)
{
	Customer CustomerServiceObj = new Customer();
String stsMsg = CustomerServiceObj.deletecustomerdetails(request.getParameter("cID"));
session.setAttribute("statusMsg", stsMsg);


} 

%>

<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="ISO-8859-1">


<title>Customer Management</title>
</head>

<body>

<h1>Customer Management</h1>
<form method="post" action="Customer.jsp">
<div class="container">
 <div class="row">
 <div class="col">

 Customer Name: <input name="cname" type="text"  class="form-control"><br>
 Customer Phone: <input name="cphone" type="text"  class="form-control"><br>
 Customer Gender: <input name="cgender" type="text"  class="form-control"><br>
 Customer email: <input name="cemail" type="text"  class="form-control"><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary">

 </div>
 </div>
</div>
</form>
<div class="alert alert-success">
<%
 out.print(session.getAttribute("statusMsg"));
 
 %>
</div>
<br>
<%
Customer CustomerServiceObj = new Customer();
 out.print(CustomerServiceObj.readDetails());
%>


</body>
</html>