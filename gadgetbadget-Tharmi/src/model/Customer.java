package model;

import java.sql.*;

public class Customer {

	private Connection connect()  {   
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb", "root", "");   
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public String customerdetails(String name, String phone, String gender, String email) 
	{   
		String output = ""; 
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting."; } 
	 
	      String query = " insert into customerdetails (`cID`,`cname`,`cphone`,`cgender`,`cemail`)"  + " values (?, ?, ?, ?, ?)"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      preparedStmt.setInt(1, 0);   
	      preparedStmt.setString(2, name);   
	      preparedStmt.setString(3, phone);    
	      preparedStmt.setString(4, gender);   
	      preparedStmt.setString(5, email); 
	   
	 
	   
	      preparedStmt.execute();    
	      con.close(); 
	 
	   output = "Inserted successfully";   
	   
	  }catch (Exception e)   
	  	
	 	{    
		   output = "Error while inserting the Details";    
		   System.err.println(e.getMessage());   
		} 
	 return output;  
	}
	
	
public String readDetails()  
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
			
	 
	     output = "<table border=\"1\"><tr><th>Customer Name</th><th>Phone</th><th>Gender</th><th>Email</th><th>Update</th><th>Remove</th></tr>"; 
	 
	   
	     String query = "select * from customerdetails";    
	     Statement stmt = con.createStatement();    
	     ResultSet rs = stmt.executeQuery(query); 
	 
	     while (rs.next())    
	     {     
	    	 String cID = Integer.toString(rs.getInt("cID"));     
	    	 String cname = rs.getString("cname");     
	    	 String cphone = rs.getString("cphone");     
	    	 String cgender = rs.getString("cgender");     
	    	 String cemail= rs.getString("cemail"); 
	     
	 
	         output += "<tr><td>" + cname + "</td>";     
	         output += "<td>" + cphone + "</td>";     
	         output += "<td>" + cgender + "</td>";     
	         output += "<td>" + cemail + "</td>"; 
	 
	         
	         
	         output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
        			 + "<td><form method='post' action='Customer.jsp'>"
        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"

        			 + "<input name='cID' type='hidden' value='" + cID + "'> </form></td></tr>";
	         } 
	
	     con.close(); 
	 
	     output += "</table>";   
	     }   
		catch (Exception e)  
		{    
			output = "Error while reading the Details.";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  } 
	
	public String customerdetails( String ID, String name, String phone, String gender, String email)  
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return "Error while connecting to the database for updating."; 
				} 
	 
	   String query = "UPDATE customerdetails SET cname=?,cphone=?,cgender=?,cemail=? WHERE cID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, phone);      
	   preparedStmt.setString(3, gender);    
	   preparedStmt.setString(4, email);
	   preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   output = "Updated successfully";   
	}   
		catch (Exception e)   
	{    
			output = "Error while updating the Details.";    
			System.err.println(e.getMessage());   
			
	} 
	 
	  return output;  
	  
	}
	
	public String deletecustomerdetails(String cID)  
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for deleting."; } 
	 
	   String query = "delete from customerdetails where cID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(cID)); 
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Deleted successfully"; 
	   
	  }   
	  catch (Exception e)   
	  {    
		  output = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());   } 
	 
	  return output;  }
	
	
}
