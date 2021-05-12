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



public class Database {
	//The following Get, Query and Insert functions use code sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
	
	//Function that will return ID from a query that will Select Product_ID From Product Where the Product_Name = n (and possibly description from Product in the future?)
	public static int getProductID(String pName) {
		int pID = 0;
		// variables for embedded sql
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
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("Select p.Product_ID From Product as p Where p.Product_Name = '" +
                    		pName +
                    		"'");
 
            System.out.println("Product ID:");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                pID = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
		return pID;
	}
	
	//Function that will return ID from a query that will Select Brand_ID from Brands Where the Brand_Name = n (This will start to not work if different brands have the same name, more specificity will be required)
	public static int getBrandID(String bName) {
		int bID = 0;
		// variables for embedded sql
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
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("Select b.Brand_ID From Brands as b Where b.Brand_Name = '" +
                    		bName +
                    		"'");
 
            System.out.println("Brand ID:");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                bID = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
		return bID;
	}

	//Function that will submit query to the database to retrieve brand names where Product_Type is s
	public static Vector<String> getBrandQuery(String s){
		Vector<String> brands = new Vector<String>();
		// variables for embedded sql
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
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            //In the where statement when p.Product_Type LIKE '% type %' is saying when that word is included any where in the Prodcut_Type
            //Example ... LIKE '%ar%' would include anything with the combination of a and r
            resultSet = statement
                    .executeQuery("SELECT b.Brand_Name FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_Type LIKE '%"
                    		+ s
                    		+ "%'");
 
            System.out.println("Brand Information:\n");
 
            // processing returned data and printing into console
            
            while(resultSet.next()) {
            	if(resultSet.getString(1).length()>0) {
            		System.out.println(resultSet.getString(1));
                	brands.add(resultSet.getString(1));
            	}
            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
        
		
		
		
		return brands;
		
	}
	
	//Function that will submit query to the database to retrieve product names where Product_Type is s
	public static Vector<String> getProductQuery(String s){
		Vector<String> products = new Vector<String>();
		// variables for embedded sql
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
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            //In the where statement when p.Product_Type LIKE '% type %' is saying when that word is included any where in the Prodcut_Type
            //Example ... LIKE '%ar%' would include anything with the combination of a and r
            resultSet = statement
                    .executeQuery("SELECT p.Product_Name FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_Type LIKE '%"
                    		+ s
                    		+ "%'");
 
            System.out.println("Product Information:\n");
 
            // processing returned data and printing into console
            
            while(resultSet.next()) {
            	if(resultSet.getString(1).length()>0) {
            		System.out.println(resultSet.getString(1));
                	products.add(resultSet.getString(1));
            	}
            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
        
		
		
		
		return products;
		
	}
	
	//Function that will submit a query to the database to then retrieve product cutsheets (Product_Data table) from the arguments of brand ID and product ID
	//This function will return a vector of strings that are the cutsheet urls
	public static Vector<String> getCutSheetQuery(int bID, int pID) {
		Vector<String> cutsheets = new Vector<String>();
		// variables for embedded sql
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
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            resultSet = statement
                    .executeQuery("SELECT pd.Cut_Sheet FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_ID = '" +
                    		 String.valueOf(pID) +
                    		"' and b.Brand_ID = '" +
                    		 String.valueOf(bID) +
                    		"'");
 
            System.out.println("Cutsheet:");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
                cutsheets.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
        return cutsheets;
	}
	
	//Function that will submit a query to the database which gets product descriptions
	//This function will return a vector of strings that are the product descriptions
	public static Vector<String> getProduct_DescQuery(String type) {
		Vector<String> descriptions = new Vector<String>();
		// variables for embedded sql
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
            //connection = DriverManager.getConnection("jdbc:ucanaccess://..//..//SubmittalXpress//Database//SubmittalXpress.accdb"); 
            // Creating JDBC Statement 
            statement = connection.createStatement();
 
            // Executing SQL and
            // retrieve data
            //In the where statement when p.Product_Type LIKE '% type %' is saying when that word is included any where in the Prodcut_Type
            //Example ... LIKE '%ar%' would include anything with the combination of a and r
            resultSet = statement
                    .executeQuery("SELECT p.Description FROM Product_Data AS pd, Brands AS b, Product AS p WHERE pd.Brand_ID = b.Brand_ID and pd.Product_ID = p.Product_ID and p.Product_Type LIKE '%"
                    		+ type
                    		+ "%'");
 
            System.out.println("Product Information:\n");
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
                descriptions.add(resultSet.getString(1));

            }
        }
        catch(SQLException sqlex){ // catch to see if it successfully goes through query
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
        return descriptions;
	}
	
	//Method that will call filter() with boolean argument set to false so that filter method will call getProductQuery
	public static Vector<String> productFilter(String str) {
		str = str.toUpperCase();
		return filter(str, false);
	}
	
	//Method that will call filter() with boolean argument set to true so that the method will call getBrandQuery
	public static Vector<String> brandFilter(String str) {
		str = str.toUpperCase();
		return filter(str, true);
	}

	//Method takes in a string and a boolean which then checks the conditional based off the string and then a conditional that checks the boolean calling either getProdcutQuery or getBrandQuery
	public static Vector<String> filter(String str, Boolean b){
		
		//Each conditional will have its own query 
				if(str.contains("PIPE") || str.contains("TUBE")){
					str = "PIPE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
					
				}
				else if(str.contains("JOINING")){
					str = "JOINING";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("DIELECTRIC")){
					str = "DIELECTRIC";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("ESCUTCHEON")){
					str = "ESCUTCHEON";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("LIQUID-IN-GLASS") ||str.contains("THERMOMETER")) {
					str = "THERMOMETER";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("THERMOWELL")) {
					str = "THERMOWELL";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("PRESSURE GAGE")) {
					str = "PRESSURE GAGE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("GAGE ATTACH")) {
					str = "GAGE ATTACH";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				//////////////////////////////////////////
				// VALVES
				//////////////////////////////////////////
				else if(str.contains("BALL VALVE")) {
					str = "BALL VALVE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("GATE VALVE")) {
					str = "GATE VALVE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("BALANCING VALVE")) {
					str = "BALANCING VALVE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("CHECK VALVE")) {
					str = "CHECK VALVE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("METAL PIPE HANGER")) {
					str = "METAL PIPE HANGER";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("THERMAL-HANGER") || str.contains("SHIELD INSERT") || str.contains("THERMAL HANGER")) {
					str = "THERMAL-HANGER";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("FASTENER SYSTEM")) {
					str = "FASTENER SYSTEM";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("EQUIPMENT LABEL")) {
					str = "EQUIPMENT LABEL";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("WARNING SIGN")) {
					str = "WARNING SIGN";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("PIPE LABEL")) {
					str = "PIPE LABEL";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("INSULATION MATERIAL")) {
					str = "INSULATION MATERIAL";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("INSULATION CEMENT")) {
					str = "INSULATION CEMENT";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("ADHESIVE")) {
					str = "ADHESIVE";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("MASTIC")) {
					str = "MASTIC";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("SEALANT")) {
					str = "SEALANT";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				else if(str.contains("FACTORY-APPLIED") || str.contains("FACTORY APPLIED")) {
					str = "FACTORY APPLIED";
					// if this is a product query, then return product name vector
					if(!b) {
						return getProductQuery(str);
					}
					// otherwise return brand name vector
					else {
						return getBrandQuery(str);
					}
				}
				//else case for product data not stored in the database
				else {
					//provide something for the UI to tell the user that product data for this item does not exist in out database
					Vector<String> endStatement = new Vector<String>();
					endStatement.add("The following does not have product data in the database: |" + str + "|");
					  //System.out.println("The following does not have product data in the database: |" + str + "|");
					  return endStatement;
				}
				
				
		
		
	}
	
	//Function that will insert information to make a tuple into the Brand table
	public static void insertBrandTuple(String Brand_Name,String Website,String Address) throws SQLException {
		// variables for embedded sql
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
		// variables for embedded sql
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
	public static void insertCutSheetTuple(int Brand_ID, int Product_ID, String CS_url ) throws SQLException {
		    // variables for embedded sql
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
	            preparedStatement.setString(3, CS_url);
	            
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

	//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
	//Test query used to test if the connection to the database will work
	//This function is just a test function and should not be used in the main application
	public static void testQuery() throws SQLException {
		// variables for embedded sql
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
	
	//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
	//Test query used to test if the connection to the database will work
	//This function is just a test function and should not be used in the main application
	public static void testQuery2() {
		    // variables for embedded sql
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