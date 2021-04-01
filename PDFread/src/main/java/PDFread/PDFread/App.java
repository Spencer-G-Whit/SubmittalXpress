package PDFread.PDFread;

import java.io.IOException;

//http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	System.out.print("Starting PDF reader \n");
    	PDFreader PDFtest = new PDFreader("C:\\Users\\msvetlichny23\\Desktop\\2018-10-29_WFXO6300_P-946_Final_Design_Spec_Book_Vol_2_of_3 (Div 3 to 22).pdf");
    	//PDFreader PDFtest = new PDFreader("C:\\Users\\Michael\\Desktop\\SPECS\\LILIA_30_CD_Spec_V4_UPDATED.pdf");
        //PDFreader PDFtest = new PDFreader("C:\\Users\\Michael\\Desktop\\SPECS\\specs_AquaWaikikiWave_Building_ReviewSet_150109.pdf");
        PDFtest.checkSpecs();
        System.out.print("Here are the specification sections we found in Division 22: \n");
        PDFtest.printSpecs();
        System.out.print("Here are the corresponding page numbers: \n");
        PDFtest.printPages();
        PDFtest.findProductData();
        PDFtest.printSpecInfo();
        //PDFtest.pageFinder();
        System.out.print("\n Program finished running");
    }
    
    
}
