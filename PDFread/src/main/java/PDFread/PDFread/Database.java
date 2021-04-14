package PDFread.PDFread;
//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.ucanaccess.jdbc.UcanaccessDriver;

public class Database {
	
	//Function that will submit a query to the database to then retrieve product cutsheets (Product_Data table)
	public void cutSheetQuery() {
		
	}
	
	//Function that will submit a query to the database which will return product information, including the brand name
	////////
	//Query to search for all pipe
	// SELECT B.Brand_Name, P.Product_Name, P.Product_Type, P.Description
	// FROM Product as P, Brand as B, Product_Data as PD
	// WHERE PD.Brand_ID = B.Brand_ID and PD.Product_ID = P.Product_ID and P.Product_Name = "Piping/Pipe"
	////////
	//Conditional will check to see which section this will be apart of 
	public void viewProductsQuery(String str) {
		str = str.toUpperCase();
		if(str.contains("PIPE") || str.contains("TUBE") || str.contains("FITTINGS")){

			
		}
		
		//else if() {
			
		
	}
	
	//Function that will insert information into one of the following tables (Brands - Product - Product_Data)
	public void insertTuple() {
		
	}
	
	//Test query used to test if the connection to the database will work
	public static void testQuery() {
        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
 
        // Loading or 
        // registering Oracle JDBC driver class
        // Step 1: Loading or 
        // registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
      catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                  + "registering MS Access JDBC driver"
            		);
            cnfex.printStackTrace();
        }
 
        // Opening database connection
        try {
 
            String msAccessDBName = "C:\\Users\\Spencer\\Desktop\\CS-378\\SubmittalExpress"
                    + "\\SubmittalXpress\\Database\\SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 
 
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("SELECT p.Product_Name FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and b.Brand_Name = 'TestComp'");
 
            System.out.println("Product Name:\n");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("We got past the connection");
        }
        finally {
            // Closing database connection
            try {
                if(null != connection) {
 
                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();
 
                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
	}
	
	//Test query used to test if the connection to the database will work
		public static void testQuery2() throws SQLException {
	        
	        // Step 1: Loading or 
	        // registering Oracle JDBC driver class
	        try {
	 
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	        }
	        catch(ClassNotFoundException cnfex) {
	 
	            System.out.println("Problem in loading or "
	                    + "registering MS Access JDBC driver");
	            cnfex.printStackTrace();   
	        }
			// variables
	        Connection con = null;
	        Statement statement = null;
	        ResultSet resultSet = null;
	       // String dbUrl = "jdbc:ucanaccess://C:/Users/Spencer/Desktop/CS-378/SubmittalExpress/SubmittalXpress/Database/SubmittalXpress.accdb";
	        con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Spencer/Desktop/CS-378//SubmittalExpress/SubmittalXpress/Database/SubmittalXpress.accdb");
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("SELECT [p.Product_Name] FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and b.Brand_Name = 'TestComp'");
	        
	        System.out.println("Product Name:\n");
	        while(resultSet.next()) {
	        	System.out.println(resultSet.getString(1));
	        }
	        
	        try {
	        	if(null != con) {
	        		// cleanup resources, once after processing
	                resultSet.close();
	                statement.close();
	 
	                // and then finally close connection
	                con.close();
	            }
	        }
	        catch (SQLException sqlex) {
	            sqlex.printStackTrace();
	        }
		}
	 
}