package model;

import java.sql.*;

public class product {
	Connection connect;
	public Connection getConnection() {
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");		 
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "");
				
				
		}catch(Exception ex){
			
		}
		return connect;
	}
	
	//for Insert Details....
	public String insertProductDetails(String name, String code, String qty, String price, String desc) {
		String status = ""; 
		try
		 {			
					Connection c = getConnection();
					
					String insertQuery = " insert into gbweb (productName, productCode, productQty, productPrice, productDesc)" +" values(?,?,?,?,?)";
					PreparedStatement pst = c.prepareStatement(insertQuery);
					pst.setString(1, name); 
					pst.setString(2, code); 
					pst.setInt(3, Integer.parseInt(qty)); 
					pst.setDouble(4, Double.parseDouble(price));
					pst.setString(5, desc);	             
					pst.execute(); 
				c.close(); 
				status = "Product data Inserted Successfully. ";
				System.out.println("Inserted Successfully...");
				
		}catch (Exception ex){
			ex.printStackTrace();
			System.err.println("Inserted Unsuccessfully!!!");	 
		} 
		return status; 
		
	}
	//for Update Details....
	public String updateProductDetails(String id, String name, String code, String qty, String price, String desc) {
		String status = ""; 
		try
		 {
				Connection c = getConnection();
				
				String updateQuery = "update gbweb set productName=?,productCode=?,productQty=?,productPrice=?,productDesc=? where productId=?";
				PreparedStatement pst = c.prepareStatement(updateQuery);
					pst.setString(1, name); 
					pst.setString(2, code); 
					pst.setInt(3, Integer.parseInt(qty)); 
					pst.setDouble(4, Double.parseDouble(price));
					pst.setString(5, desc);	 
					pst.setInt(6, Integer.parseInt(id)); 
					pst.executeUpdate(); 
				c.close(); 
				status = "Product data Updated Successfully. ";
				System.out.println("Updated Successfully...");
				
		}catch (Exception ex){
			ex.printStackTrace();
			System.err.println("Updated Unsuccessfully!!!");	 
		} 
		return status; 
		
	}
	//for Delete Details....
	public String deleteProductDetails(String id) {
		String status = ""; 
		try
		{
			Connection c = getConnection();
			
			String deleteQuery = "delete from gbweb where productId=?"; 
			PreparedStatement pst = c.prepareStatement(deleteQuery);
				pst.setInt(1,Integer.parseInt(id));	             
				pst.execute(); 
			c.close(); 
			status = "Data deleted Successfully.";	
			System.out.println("Product data Deleted Successfully. ");
					
		}catch (Exception ex){
			ex.printStackTrace();
			System.err.println("deleted Unsuccessfully!!!");	 
		} 
			return status; 
	}
	//for read Details....
	public String readProductDetails() 
	{ 
		String status = ""; 
		try	{
			Connection c = getConnection();
			
			 // for make table...
			 status = "<head >Product Details</head>"+
					 "<table border='1'><tr>  <th>Item Id</th>"+"<th>Item Name</th>"+"<th>Item Code</th>" + "<th>Item Qty</th>" + "<th>Item Price</th>"+"<th>Item Description</th></tr>"; 
			 
			 String displayQuery = "select * from gbweb"; 
			 Statement st = c.createStatement(); 
			 ResultSet rs = st.executeQuery(displayQuery); 
			 
				 while (rs.next()) 
				 { 
					 String pId = Integer.toString(rs.getInt("productId")); 
					 String pName = rs.getString("productName"); 
					 String pCode = rs.getString("productCode"); 
					 String pQty = Integer.toString(rs.getInt("productQty")); 
					 String pPrice = Double.toString(rs.getDouble("productPrice")); 
					 String pDesc = rs.getString("productDesc"); 
					 // add into the html table...
					 status += "<tr><td>"+pId+"</td>";
					 status += "<td>" + pName + "</td>"; 
					 status += "<td>" + pCode+ "</td>"; 
					 status += "<td>" + pQty+ "</td>"; 
					 status += "<td>" + pPrice + "</td>"; 
					 status += "<td>" + pDesc + "</td>"; 
					
				 } 
			 c.close(); 
			
			 status +=  "</table>"; 
			 
		}catch (Exception ex) { 
			 status = "Data Reading error!!!"; 
			 System.err.println(ex.getMessage()); 
		} 
		return status; 
	} 		

}
			
			
