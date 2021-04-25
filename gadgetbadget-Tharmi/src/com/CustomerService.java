package com;

import model.Customer; 

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/customer") 
public class CustomerService {

	Customer cusObj = new Customer(); 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertcustomerdetails(@FormParam("cname") String cname,      
							@FormParam("cphone") String cphone,    
							@FormParam("cgender") String cgender,      
							@FormParam("cemail") String cemail)
	{  
		String output = cusObj.customerdetails(cname, cphone, cgender, cemail);  
		return output; 
	} 
	 
	 @GET  
	 @Path("/")  
	 @Produces(MediaType.TEXT_HTML) 
	public String readDetails()  
	 {   
		 return cusObj.readDetails();
		 
	 } 
	 
	 
	 @PUT 
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String updatecustomerdetails(String customerData)
	 {  
		 
		 JsonObject cusObject = new JsonParser().parse(customerData).getAsJsonObject(); 
		 
		 String cID = cusObject.get("cID").getAsString();  
		 String cname = cusObject.get("cname").getAsString();  
		 String cphone = cusObject.get("cphone").getAsString();  
		 String cgender = cusObject.get("cgender").getAsString();  
		 String cemail = cusObject.get("cemail").getAsString(); 
		 
		 String output = cusObj.customerdetails(cID, cname, cphone, cgender, cemail); 
		 
		 return output; 
		 
	 } 
	 
	 
	 @DELETE 
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_XML) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String deletecustomerdetails(String cusData)
	 {   
		 Document doc = Jsoup.parse(cusData, "", Parser.xmlParser());     
		 String cusID = doc.select("cusID").text(); 
		 
		 String output = cusObj.deletecustomerdetails(cusID); 
		 
		 return output; } 
	 
	 
}
