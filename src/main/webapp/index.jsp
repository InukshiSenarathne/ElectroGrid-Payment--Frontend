<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("CardNumber") != null) 
{ 
 Payment paymentObj = new Payment(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidItemIDSave") == "") 
 { 
 stsMsg = paymentObj.insertPayment(request.getParameter("CardNumber"), 
 request.getParameter("CardName"), 
 request.getParameter("Cvv"), 
 request.getParameter("ExpDate")); 
 } 
else//Update----------------------
 { 
 stsMsg = paymentObj.updatePayment(request.getParameter("hidPaymentIDSave"), 
 request.getParameter("CardNumber"), 
 request.getParameter("CardName"), 
 request.getParameter("Cvv"), 
 request.getParameter("ExpDate")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidPaymentIDDelete") != null) 
{ 
	Payment paymentObj = new Payment(); 
 String stsMsg = 
 paymentObj.deletePayment(request.getParameter("hidPaymentIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payments Management V10.1</h1>
<form id="formPayment" name="formPayment">
 CardNumber: 
 <input id="CardNumber" name="CardNumber" type="text" 
 class="form-control form-control-sm">
 <br> CardName: 
 <input id="CardName" name="CardName" type="text" 
 class="form-control form-control-sm">
 <br> Cvv: 
 <input id="Cvv" name="Cvv" type="text" 
 class="form-control form-control-sm">
 <br> Item description: 
 <input id="itemDesc" name="itemDesc" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPaymentIDSave" 
 name="hidPaymentIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentsGrid">
 <%
 Payment paymentObj = new Payment(); 
 out.print(paymentObj.readPayments()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
