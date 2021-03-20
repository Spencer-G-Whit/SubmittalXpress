// Author: Michael Svetlichny, Spencer Gariano, Joshua Shetler
// REF: https://www.tutorialspoint.com/pdfbox/pdfbox_quick_guide.htm
// REF: https://pdfbox.apache.org/docs/2.0.11/javadocs/org/apache/pdfbox/text/PDFTextStripper.html

// 3/20/2021
// TODO: 
// store section's into a vector
// add private members to the PDF reader class
// differentiate the public methods 


package PDFread.PDFread;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFreader {
	
	//private int x =0;
	
	public void readPDF() throws IOException {
		//
		URL url = new URL("http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf");
		
		InputStream is = url.openStream();
		BufferedInputStream fileParse = new BufferedInputStream(is);
		PDDocument document = null;
		
		document = PDDocument.load(fileParse);
		String pdfContent = new PDFTextStripper().getText(document);
//		pdfContent.replaceAll(" ","");
//		System.out.println(pdfContent.replaceAll(" ",""));
		String pdfContent_W = pdfContent.replaceAll(" ","");

		// Check for Division 22's existence, then check for all subsections
		Vector<String> D22Sub =new Vector<String>();
		if(pdfContent.contains("DIVISION 22") || pdfContent.contains("division 22")) {
			
			String test = "", test1 = "", test2 = "";
			
			//create while loop to find the first sub section of the division
			int divisionNumber = 220000;
			while(divisionNumber <230000) {
				String strDivNum = String.valueOf(divisionNumber); //convert int into string
				if(pdfContent_W.contains(strDivNum)) {	
					for(int i = 0; i < strDivNum.length(); i++) {
						String temp = String.valueOf(strDivNum.charAt(i));
						if(i == 2) {
							test1 = test1.concat(" ");
						}
						test2 = test2.concat(temp);
						if(i == 2) {
							test2 = test2.concat(" ");
						}
						if(i == 5) {
							test2 = test2.concat(" ");
						}
						test2 = test2.concat(temp);
					}
					// Once the first instance of a spec section is found, exit the loop
					divisionNumber = 229999;
				}
				divisionNumber++;
			}
			
			// Reset the division number back to 220000 and search for format style
			divisionNumber = 220000;
			if(pdfContent.contains(test1)) {
				while(divisionNumber <230000) {
					String strDivNum = String.valueOf(divisionNumber); //convert int into string
					if(pdfContent_W.contains(strDivNum)) {	
						test = "";
						for(int i = 0; i < strDivNum.length(); i++) {
							String temp = String.valueOf(strDivNum.charAt(i));
							// Add space at second index 
							if(i == 2) {
								test = test.concat(" ");
							}
							test = test.concat(temp);
						}
						System.out.print(test);
						System.out.print("\n");
					}
					divisionNumber++;
				}
			}
			// CHECK FORMAT STYLE 1:
			//create while loop to check all sub sections of the division
			else if(pdfContent.contains(test2)) {
				while(divisionNumber <230000) {
					String strDivNum = String.valueOf(divisionNumber); //convert int into string
					if(pdfContent_W.contains(strDivNum)) {	
						test = "";
						for(int i = 0; i < strDivNum.length(); i++) {
							String temp = String.valueOf(strDivNum.charAt(i));
							if(i == 2) {
								test = test.concat(" ");
							}
							if(i == 5) {
								test = test.concat(" ");
							}
							test = test.concat(temp);
						}
						System.out.print(test);
						System.out.print("\n");
					}
					divisionNumber++;
				}
			}
			// CHECK FORMAT STYLE 2:
			//create while loop to check all sub sections of the division
			else {
				while(divisionNumber <230000) {
					String strDivNum = String.valueOf(divisionNumber); //convert int into string
					if(pdfContent_W.contains(strDivNum)) {	
						test = "";
						for(int i = 0; i < strDivNum.length(); i++) {
							String temp = String.valueOf(strDivNum.charAt(i));
							test = test.concat(temp);
						}
						// In the future, we could consider the 
						// following commented code in the case of spec section
						// 220000.0#, where # represents any number 1-9:
						// if(pdfContent.contains(test.concat(".0"))) 
						System.out.print(test);
						System.out.print("\n");
					}
					divisionNumber++;
				}
			}


			//test output
			for(int i = 0; i < D22Sub.size(); i++ ) {
				System.out.print(D22Sub.get(i));
				System.out.print("\n");
			}
		}
		else {	
		}
	}
}
