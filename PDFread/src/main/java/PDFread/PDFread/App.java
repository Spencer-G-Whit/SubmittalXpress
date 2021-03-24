package PDFread.PDFread;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        PDFreader PDFtest = new PDFreader("http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf");
        PDFtest.checkSpecs();
        PDFtest.printSpecs();
    }
    
    
}
