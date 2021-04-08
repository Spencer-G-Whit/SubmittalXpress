package PDFread.PDFread;
//Sourced from https://www.benchresources.net/jdbc-msaccess-database-connection-steps-in-java-8/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		else if() {
			
		}
	}
	//Function that will insert information into one of the following tables (Brands - Product - Product_Data)
	public void insertTuple() {
		
	}
	
}
