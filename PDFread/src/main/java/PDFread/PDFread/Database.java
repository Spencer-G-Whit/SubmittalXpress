package PDFread.PDFread;
//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
//import java.sql.*;
import java.sql.*;
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
		else if(str.contains("JOINING")){
			
		}
		else if(str.contains("DIELECTRIC")){
			
		}
		else if(str.contains("ESCUTCHEON")){
			
		}
		else if(str.contains("LIQUID-IN-GLASS") ||str.contains("THERMOMETER")) {
			
		}
		else if(str.contains("THERMOWELL")) {
			
		}
		else if(str.contains("PRESSURE GAGE")) {
			
		}
		else if(str.contains("GAGE ATTACH")) {
			
		}
		//////////////////////////////////////////
		// VALVES
		//////////////////////////////////////////
		else if(str.contains("BALL VALVE")) {
			
		}
		else if(str.contains("GATE VALVE")) {
			
		}
		else if(str.contains("BALANCING VALVE")) {
			
		}
		else if(str.contains("CHECK VALVE")) {
			
		}
		else if(str.contains("METAL PIPE HANGER")) {
			
		}
		else if(str.contains("THERMAL-HANGER") || str.contains("SHIELD INSERT") || str.contains("THERMAL HANGER")) {
			
		}
		else if(str.contains("FASTENER SYSTEM")) {
			
		}
		else if(str.contains("EQUIPMENT LABEL")) {
			
		}
		else if(str.contains("WARNING SIGN")) {
			
		}
		else if(str.contains("PIPE LABEL")) {
			
		}
		else if(str.contains("INSULATION MATERIAL")) {
			
		}
		else if(str.contains("INSULATION CEMENT")) {
			
		}
		else if(str.contains("ADHESIVE")) {
			
		}
		else if(str.contains("MASTIC")) {
			
		}
		else if(str.contains("SEALANT")) {
			
		}
		else if(str.contains("FACTORY-APPLIED") || str.contains("FACTORY APPLIED")) {
			
		}
		//else case for product data not stored in the database
		else {
			//provide something for the UI to tell the user that product data for this item does not exist in out database
		}
		
		
		//else if() {
			
		
	}
	
	//Function that will insert information into one of the following tables (Brands - Product - Product_Data)
	public void insertBrandTuple(String Name,String Website,String Address) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver"
            		+ e.getLocalizedMessage()
               		);
              e.printStackTrace();
        }
 
        // Opening database connection
        try {
            String msAccessDBName = "..//Database//SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            //connection = DriverManager.getConnection(dbURL); 
            connection = DriverManager.getConnection(dbURL);
		
			String sql = "INSERT INTO Brands (Brand_ID, Brand_Name, Brand_Website, Brand_Address) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, Name);
            preparedStatement.setString(3, Website);
            preparedStatement.setString(4, Address);
            
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
            	System.out.println("Tuple has been inserted successfully.");
            }
        }
        finally {
            // Closing database connection
            try {
                if(null != connection) {
 
                    // cleanup resources, once after processing
                    resultSet.close();
                    preparedStatement.close();
 
                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
	}
	
	public static void insertProductTuple() {
	
	}
	
	public static void insertCutSheetTuple() {
		
		
	}

	//Test query used to test if the connection to the database will work
	public static void testQuery() throws SQLException {
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
        catch (Exception e) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver"
            		+ e.getLocalizedMessage()
               		);
              e.printStackTrace();
        }
 
        // Opening database connection
        try {
            String msAccessDBName = "C://Users//sgariano22//Desktop//SubmittalXpress"
                    + "//SubmittalXpress//Database//SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            //connection = DriverManager.getConnection(dbURL); 
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\sgariano22\\Desktop\\SubmitalXpress\\SubmittalXpress\\Database\\SubmittalXpress.accdb"); 
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("SELECT p.Product_Name FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and b.Brand_Name = 'TestComp'");
 
            System.out.println("Product Name:\n");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
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
		public static void testQuery2() {
			// variables
	        Statement statement = null;
	        ResultSet resultSet = null;
			String dbURL = "jdbc:ucanaccess://C://Users//sgariano22//Desktop//SubmittalXpress//SubmittalXpress//Database//SubmittalXpress.accdb";
	        try(Connection connection = DriverManager.getConnection(dbURL)){
	        	String sql = "SELECT p.Product_Name FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and b.Brand_Name = 'TestComp'";
	        	statement = connection.createStatement();
	        	resultSet = statement.executeQuery(sql);
	        	while(resultSet.next()) {
	                System.out.println(resultSet.getInt(1));
	            }
	        }
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		}
	 
}