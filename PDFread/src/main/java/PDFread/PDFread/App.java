package PDFread.PDFread;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import java.net.URL;
//http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException
    {
    //	System.out.print("Starting PDF reader \n");
    	// LIST OF DIFFERENT SPECIFICATIONS TO TEST
    	//"C:\\Users\\msvetlichny23\\Desktop\\2018-10-29_WFXO6300_P-946_Final_Design_Spec_Book_Vol_2_of_3 (Div 3 to 22).pdf"
    	//PDFreader PDFtest = new PDFreader("C:\\\\Users\\\\Michael\\\\Desktop\\\\SPECS\\2018-10-29_WFXO6300_P-946_Final_Design_Spec_Book_Vol_2_of_3 (Div 3 to 22).pdf");
    	//PDFreader PDFtest = new PDFreader("C:\\Users\\sgariano22\\Desktop\\SubmitalXpress\\SubmittalXpress\\specs_AquaWaikikiWave_Building_ReviewSet_150109.pdf");
   //     PDFreader PDFtest = new PDFreader(VVM.getFilePath());
   //     PDFtest.checkSpecs();
//        System.out.print("Here are the specification sections we found in Division 22: \n");
//        PDFtest.printSpecs();
   //     System.out.print("================================================");
   //     System.out.print("\nHere are the corresponding page numbers: \n");
   //     PDFtest.printPages();
   //     System.out.print("================================================");
   //     System.out.print("\nHere are the sub sections in each spec section: \n");
   //     PDFtest.findProductData();
   //     PDFtest.printSpecInfo();
        //PDFtest.pageFinder();
   //     System.out.print("================================================");
   //     System.out.print("\nProgram finished running");
        
    	
    	//Tests for Insert
    	//Database.insertBrandTuple("TestBrand", null, null);
    	//Database.insertProductTuple("TestProd2", "Pipe", "22", "Long 12 diamter pipe");
    	//URL url = new URL("https://www.hunterindustries.com/sites/default/files/CA-Cutsheet-Pro-Spray-US.pdf");
    	//Database.insertCutSheetTuple(2, 2, url);
    	
    	//Tests for Query
    	//Test for database connection
    	//Database.productFilter("Pipe");
    	//Database.productFilter("Nuke");
    	//Database.productQuery("Testies");
    	//Database.cutSheetQuery("1","1");
    	//Database.testQuery(); 
    	//Database.testQuery2();
    }
    
    
}
