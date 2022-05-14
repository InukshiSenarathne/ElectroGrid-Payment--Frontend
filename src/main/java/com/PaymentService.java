package com; 
import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payments") 
public class PaymentService 
{ 
	Payment paymentObj = new Payment(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readPayments() 
  { 
  return paymentObj.readPayments(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("CardNumber") String CardNumber, 
  @FormParam("CardName") String CardName, 
  @FormParam("Cvv") String Cvv, 
  @FormParam("ExpDate") String ExpDate) 
 { 
  String output = paymentObj.insertPayment(CardNumber, CardName, Cvv, ExpDate); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String paymentData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
 //Read the values from the JSON object
  String paymentID = paymentObject.get("paymentID").getAsString(); 
  String CardNumber = paymentObject.get("CardNumber").getAsString(); 
  String CardName = paymentObject.get("CardName").getAsString(); 
  String Cvv = paymentObject.get("Cvv").getAsString(); 
  String ExpDate = paymentObject.get("ExpDate").getAsString(); 
  String output = paymentObj.updatePayment(paymentID, CardNumber, CardName, Cvv, ExpDate); 
 return output; 
 }

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteItem(String paymentData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String paymentID = doc.select("paymentID").text(); 
  String output = paymentObj.deletePayment(paymentID); 
 return output; 
 }
}
