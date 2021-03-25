package PDFread.PDFread;

import java.io.IOException;

//http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        PDFreader PDFtest = new PDFreader("C:\\Users\\msvetlichny23\\Desktop\\specs_AquaWaikikiWave_Building_ReviewSet_150109.pdf");
        PDFtest.checkSpecs();
        PDFtest.printSpecs();
        PDFtest.pageFinder();
    }
    
    
}
