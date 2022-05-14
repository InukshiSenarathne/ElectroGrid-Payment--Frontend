package model; 
import java.sql.*; 


public class Payment
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readPayments() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Item Code</th>" 
		 +"<th>Item Name</th><th>Item Price</th>"
		 +"<th>Item Description</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from items"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String paymentID = Integer.toString(rs.getInt("paymentID")); 
 String CardNumber = rs.getString("CardNumber"); 
 String CardName = rs.getString("CardName"); 
 String Cvv = Double.toString(rs.getDouble("Cvv")); 
 String ExpDate = rs.getString("ExpDate"); 
 
 // Add into the html table
 output += "<tr><td>" + CardNumber + "</td>"; 
 output += "<td>" + CardName + "</td>"; 
 output += "<td>" + Cvv + "</td>"; 
 output += "<td>" + ExpDate + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + paymentID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + paymentID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the payments."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertPayment(String CardNumber, String CardName, 
		 String Cvv, String ExpDate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into payments (`paymentID`,`CardNumber`,`CardName`,`Cvv`,`ExpDate`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, CardNumber); 
		 preparedStmt.setString(3, CardName); 
		 preparedStmt.setDouble(4, Double.parseDouble(Cvv)); 
		 preparedStmt.setString(5, ExpDate); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readPayments(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updatePayment(String paymentID, String CardNumber, String CardName, 
		 String Cvv, String ExpDate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE items SET CardNumber=?,CardName=?,Cvv=?,ExpDate=? WHERE paymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, CardNumber); 
		 preparedStmt.setString(2, CardName); 
		 preparedStmt.setDouble(3, Double.parseDouble(Cvv)); 
		 preparedStmt.setString(4, ExpDate); 
		 preparedStmt.setInt(5, Integer.parseInt(paymentID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readPayments(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deletePayment(String paymentID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from payments where paymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPayments = readPayments(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPayments + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Payment.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


