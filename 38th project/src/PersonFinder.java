

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
/**
 * Servlet implementation class helloworld
 */



@WebServlet("/PersonFinder")
public class PersonFinder extends HttpServlet {
	
	
	

	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  String info = "";
		  int i = 0;
		
		  
		  String lastname = request.getParameter("search");
		  String cname = request.getParameter("search2");
		  
		  //System.out.println(lastname);
		  //System.out.println(cname);
		 
		  
		  try
			{
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
				
		    	String url = "jdbc:oracle:thin:testuser/password@localhost"; 

		    	
				//properties for creating connection to Oracle database
				Properties props = new Properties();
				props.setProperty("user", "testdb");
				props.setProperty("password", "password");
			   
		    	
		    	Connection conn = DriverManager.getConnection(url,props);
		    	
		    	PreparedStatement preStatement = null;
		    	
				String sql = "";
				if(cname.isEmpty())
				{
					sql ="select firstname,lastname from people where lastname like ?";
					 preStatement = conn.prepareStatement(sql);
					String tmp = "Smith";
					String in = lastname + "%";
					preStatement.setString(1,in);
					
				}
				else
				{
					sql = "select people.lastname from people inner join companies on companies.companyid = people.compandyid where companies.company like ?";
					 preStatement = conn.prepareStatement(sql);
					String tmp = "Smith";
					String in = cname + "%";
					preStatement.setString(1,in);
				}
				
				//creating PreparedStatement object to execute query
				
				/*
				String tmp = "Smith";
				String in = lastname + "%";
				preStatement.setString(1,in);
				*/
				
				ResultSet result;
				result = preStatement.executeQuery();
				
			
				
				while(result.next())
				{
					
					String curlname = ""; 
					if(cname.isEmpty())
					{
						curlname = result.getString("lastname");
					}
					else
					{
						curlname = result.getString("lastname");
					}
					//System.out.println(curlname);
					
					String sql2 ="select fullname, title, streetaddress, zipcode, emailaddress, position, compandyid, placeid from people where lastname = ?";  
					
					PreparedStatement preStatement2 = conn.prepareStatement(sql2);
					
					preStatement2.setString(1,curlname);
					
					
					ResultSet result2;
					result2 = preStatement2.executeQuery();
					
					if(result2.next())
					{
						
						String fullname = result2.getString("fullname");
						String title = result2.getString("title");
						String streetaddress = result2.getString("streetaddress");
						String zipcode = result2.getString("zipcode");
						String email = result2.getString("emailaddress");
						
						int compid = result2.getInt("compandyid");
						int placeid = result2.getInt("placeid");
						String company = "";
						String city = "";
						String state = "";
						
						
						
						String sql3 ="select company from companies where companyid = ?";  
						PreparedStatement preStatement3 = conn.prepareStatement(sql3);
						preStatement3.setInt(1,compid);
						ResultSet result3;
						result3 = preStatement3.executeQuery();
						
						if(result3.next())
						{
							company = result3.getString("company");
							//info = info + company + " ";
						}
						
						String sql4 ="select city, state from citystate where placeid = ?";  
						PreparedStatement preStatement4 = conn.prepareStatement(sql4);
						preStatement4.setInt(1,placeid);
						ResultSet result4;
						result4 = preStatement4.executeQuery();
						
						if(result4.next())
						{
							city = result4.getString("city");
							state = result4.getString("state");
							//info = info + company + " " + city + state + "<br><br>";
						}
						
						info = info + i++ + " " + fullname + " " + title + " " + streetaddress + " " + zipcode + " " + email + " " + company + " " + city + " " + state + "<br><br>"; 
						
					}
					
				}
				
				
				try{
					conn.close();
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
		
			
			}
		  
		    catch (Exception e)
			{
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
	      // Set response content type
	      response.setContentType("text/html");
	      
	      request.setAttribute("info",info);
	      
	      
	      getServletContext()
	      	.getRequestDispatcher("/output.jsp")
	      	.forward(request, response);
	 
	   }
	  
	  public void doPost(HttpServletRequest request,
              HttpServletResponse response)
      throws ServletException, IOException
      {
		  doGet(request, response);
      }

	   public void destroy() 
	   { 
	     // do nothing. 
	   } 

}
