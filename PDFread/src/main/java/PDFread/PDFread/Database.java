package PDFread.PDFread;
//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
//import java.sql.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.net.URL;



public class Database {
	//Name of the brands
	private static Vector<String> B_Name = new Vector<String>();
	//Name of the products
	private  static Vector<String> P_Name = new Vector<String>();
	//The type of products
	private static Vector<String> P_Type = new Vector<String>();
	//Product Description
	private  static Vector<String> P_Desc = new Vector<String>(); 
	
	//Function that will put n into the vector B_Name
	public static void setB_Name(String n) {
		B_Name.add(n);
	}
	//Function that will put n into the vector P_Name
	public static void setP_Name(String n) {
		P_Name.add(n);
	}
	//Function that will put t into the vector P_Type
	public static void setP_Type(String t) {
		P_Type.add(t);
	}
	//Function that will put d into the vector P_Desc
	public static void setP_Desc(String d){
		P_Desc.add(d);
	}
	
	//Function that will return the contents of B_Name
	public Vector<String> getB_Name() {
		return B_Name;
	}
	//Function that will return the contents of P_Name
	public Vector<String> getP_Name() {
		return P_Name;
	}
	//Function that will return the contents of P_Type
	public Vector<String> getP_Type(){
		return P_Type;
	}
	//Function that will return the contents of P_Desc
	public Vector<String> getP_Desc(){
		return P_Desc;
	}	
	
	//Function that will submit a query to the database to then retrieve product cutsheets (Product_Data table) from the arguments of brand ID and product ID
	//Because of previous issues this cutsheet will be a url to the cutsheet
	public static void cutSheetQuery(String bID, String pID) {
		// variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
 
        // Loading or 
        // registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        	

        }
        catch(ClassNotFoundException cnfex) { //Exception for if the class is not found
 
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        catch (Exception e) { //Exception for initialization failure
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver"
            		+ e.getLocalizedMessage()
               		);
              e.printStackTrace();
        }
 
        // Opening database connection
        try {
            String msAccessDBName = "..//..//SubmittalXpress//Database//SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 
            //connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\sgariano22\\Desktop\\SubmitalXpress\\SubmittalXpress\\Database\\SubmittalXpress.accdb"); 
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("SELECT pd.Cut_Sheet FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_ID = '" +
                    		 pID +
                    		"' and b.Brand_ID = '" +
                    		 bID +
                    		"'");
 
            System.out.println("Cutsheet:");
 
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
	
	//Function that will submit a query to the database which will return product information, including the brand name based off of product type
	////////
	//Query to search for all pipe
	// SELECT B.Brand_Name, P.Product_Name, P.Product_Type, P.Description
	// FROM Product as P, Brand as B, Product_Data as PD
	// WHERE PD.Brand_ID = B.Brand_ID and PD.Product_ID = P.Product_ID and P.Product_Type LIKE or = "..."
	////////
	public static void productQuery(String type) {
		// variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
 
        // Loading or 
        // registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        	

        }
        catch(ClassNotFoundException cnfex) { //Exception for if the class is not found
 
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        catch (Exception e) { //Exception for initialization failure
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver"
            		+ e.getLocalizedMessage()
               		);
              e.printStackTrace();
        }
 
        // Opening database connection
        try {
            String msAccessDBName = "..//..//SubmittalXpress//Database//SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 
            //connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\sgariano22\\Desktop\\SubmitalXpress\\SubmittalXpress\\Database\\SubmittalXpress.accdb"); 
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            //In the where statement when p.Product_Type LIKE '% type %' is saying when that word is included any where in the Prodcut_Type
            //Example ... LIKE '%ar%' would include anything with the combination of a and r
            resultSet = statement
                    .executeQuery("SELECT b.Brand_Name, p.Product_Name, p.Product_Type, p.Description  "
                    		+ "FROM Product_Data AS pd, Brands AS b, Product AS p "
                    		+ "WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_Type LIKE '%"
                    		+ type
                    		+ "%'");
 
            System.out.println("Product Information:\n");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + 
                        resultSet.getString(2) + "\t" + 
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4));
                setB_Name(resultSet.getString(1));
                setP_Name(resultSet.getString(2));
                setP_Type(resultSet.getString(3));
                setP_Desc(resultSet.getString(4));

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
	
	//Conditional will check to see which section this will be apart of
	//Then calls the product query function then to get the relevant product information
	public static void productFilter(String str) {
		str = str.toUpperCase();
		
        //Each conditional will have its own query 
		if(str.contains("PIPE") || str.contains("TUBE") || str.contains("FITTINGS")){
			str = "PIPE";
			productQuery(str); 
			
		}
		else if(str.contains("JOINING")){
			productQuery(str); 
		}
		else if(str.contains("DIELECTRIC")){
			productQuery(str); 
		}
		else if(str.contains("ESCUTCHEON")){
			productQuery(str); 
		}
		else if(str.contains("LIQUID-IN-GLASS") ||str.contains("THERMOMETER")) {
			productQuery(str); 
		}
		else if(str.contains("THERMOWELL")) {
			productQuery(str); 
		}
		else if(str.contains("PRESSURE GAGE")) {
			productQuery(str); 
		}
		else if(str.contains("GAGE ATTACH")) {
			productQuery(str); 
		}
		//////////////////////////////////////////
		// VALVES
		//////////////////////////////////////////
		else if(str.contains("BALL VALVE")) {
			productQuery(str); 
		}
		else if(str.contains("GATE VALVE")) {
			productQuery(str); 
		}
		else if(str.contains("BALANCING VALVE")) {
			productQuery(str); 
		}
		else if(str.contains("CHECK VALVE")) {
			productQuery(str); 
		}
		else if(str.contains("METAL PIPE HANGER")) {
			productQuery(str); 
		}
		else if(str.contains("THERMAL-HANGER") || str.contains("SHIELD INSERT") || str.contains("THERMAL HANGER")) {
			productQuery(str); 
		}
		else if(str.contains("FASTENER SYSTEM")) {
			productQuery(str); 
		}
		else if(str.contains("EQUIPMENT LABEL")) {
			productQuery(str); 
		}
		else if(str.contains("WARNING SIGN")) {
			productQuery(str); 
		}
		else if(str.contains("PIPE LABEL")) {
			productQuery(str); 
		}
		else if(str.contains("INSULATION MATERIAL")) {
			productQuery(str); 
		}
		else if(str.contains("INSULATION CEMENT")) {
			productQuery(str); 
		}
		else if(str.contains("ADHESIVE")) {
			productQuery(str); 
		}
		else if(str.contains("MASTIC")) {
			productQuery(str); 
		}
		else if(str.contains("SEALANT")) {
			productQuery(str); 
		}
		else if(str.contains("FACTORY-APPLIED") || str.contains("FACTORY APPLIED")) {
			productQuery(str); 
		}
		//else case for product data not stored in the database
		else {
			//provide something for the UI to tell the user that product data for this item does not exist in out database
			  System.out.println("The following does not have product data in the database: |" + str + "|");

		}
		
	}
	
	//Function that will insert information to make a tuple into the Brand table
	public static void insertBrandTuple(String Brand_Name,String Website,String Address) throws SQLException {
        //variables
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Loading or 
        // registering Oracle JDBC driver class
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        //Exception if the class is not found
        catch(ClassNotFoundException cnfex) { 
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        //Exception if the initialization fails
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
            
            //To SQL portion
			String sql = "INSERT INTO Brands (Brand_Name, Brand_Website, Brand_Address) VALUES (?,?,?)";
			//Get the sql into the prepared statement then insert the values needed
			preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setString(1, Brand_Name);
            preparedStatement.setString(2, Website);
            preparedStatement.setString(3, Address);
            
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
            	System.out.println("Tuple has been inserted successfully.");
            }
        }
        //This is the end of the method
        finally {
            // Closing database connection
            try {
                if(null != connection) {
 
                    // cleanup resources, once after processing
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
	
	//Function that will insert information to make a tuple into the Product table 
	public static void insertProductTuple(String Prod_Name, String Type, String Spec_Section, String Description) throws SQLException {
        //variables
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        // Loading or 
        // registering Oracle JDBC driver class
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        //Exception if the class is not found
        catch(ClassNotFoundException cnfex) { 
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        //Exception if the initialization fails
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
            
            //To SQL portion
			String sql = "INSERT INTO Product (Product_Name, Product_Type, Spec_Section, Description) VALUES (?,?,?,?)";
			//Get the sql into the prepared statement then insert the values needed
			preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setString(1, Prod_Name);
            preparedStatement.setString(2, Type);
            preparedStatement.setString(3, Spec_Section);
            preparedStatement.setString(4, Description);
            
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
            	System.out.println("Tuple has been inserted successfully.");
            }
        }
        //This is the end of the method
        finally {
            // Closing database connection
            try {
                if(null != connection) {
 
                    // cleanup resources, once after processing
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
	
	//Function that will insert information to make a tuple into the Product_Data
	public static void insertCutSheetTuple(int Brand_ID, int Product_ID, URL CS_url ) throws SQLException {
	       //variables
			Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        // Loading or 
	        // registering Oracle JDBC driver class
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	        }
	        //Exception if the class is not found
	        catch(ClassNotFoundException cnfex) { 
	            System.out.println("Problem in loading or "
	                 + "registering MS Access JDBC driver"
	            		);
	           cnfex.printStackTrace();
	        }
	        //Exception if the initialization fails
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
	            
	            //To SQL portion
				String sql = "INSERT INTO Product_Data (Brand_ID, Product_ID, Cut_Sheet) VALUES (?,?,?)";
				//Get the sql into the prepared statement then insert the values needed
				preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, Brand_ID);
	            preparedStatement.setInt(2, Product_ID);
	            preparedStatement.setURL(3, CS_url);
	            
	            int row = preparedStatement.executeUpdate();
	            if(row > 0) {
	            	System.out.println("Tuple has been inserted successfully.");
	            }
	        }
	        //This is the end of the method
	        finally {
	            // Closing database connection
	            try {
	                if(null != connection) {
	 
	                    // cleanup resources, once after processing
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

	//Test query used to test if the connection to the database will work
	public static void testQuery() throws SQLException {
        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
 
        // Loading or 
        // registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        	

        }
        catch(ClassNotFoundException cnfex) { //Exception for if the class is not found
 
            System.out.println("Problem in loading or "
                 + "registering MS Access JDBC driver"
            		);
           cnfex.printStackTrace();
        }
        catch (Exception e) { //Exception for initialization failure
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver"
            		+ e.getLocalizedMessage()
               		);
              e.printStackTrace();
        }
 
        // Opening database connection
        try {
            String msAccessDBName = "..//..//SubmittalXpress//Database//SubmittalXpress.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccessDBName;
 
            // Create and 
            // get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 
            //connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\sgariano22\\Desktop\\SubmitalXpress\\SubmittalXpress\\Database\\SubmittalXpress.accdb"); 
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