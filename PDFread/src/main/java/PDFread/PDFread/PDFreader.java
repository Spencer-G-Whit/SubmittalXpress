// Author: Michael Svetlichny, Spencer Gariano, Joshua Shetler
// Modification: 3/21/2021 - alpha
// REF: https://www.tutorialspoint.com/pdfbox/pdfbox_quick_guide.htm
// REF: https://pdfbox.apache.org/docs/2.0.11/javadocs/org/apache/pdfbox/text/PDFTextStripper.html
///////////////////////////////
// 3/20/2021
// TODO: 
//  - store section's into a vector - DONE
//  - add private members to the PDF reader class - DONE
//  - differentiate the public methods - DONE
//  - need more specifications to test, but make the search method 
//    such that it starts at the location of the division


/////////////////////////////////////
// LIBRARY METHODS:
// checkSpecs() : checks the provided specifications to determine which sections exist in Division 22
// specSetter() : pushes a specification section number as a string into a vector
// printSpecs() : prints out all specification numbers found and stored in the specification vector


package PDFread.PDFread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFreader {
	
	// Private members:
	private String pdfContent;
	private String pdfContent_W;
	
	// Public members:
	public Vector<String> D22Spec =new Vector<String>(); //Holds s
	
	
	// String Constructor:
	public PDFreader(String PDFpath) throws IOException {
		
		// Check if URL was passed in or if file path is passed in
		// Condition for URL:
		if((PDFpath.charAt(0) == 'h' && PDFpath.charAt(1) == 't') || (PDFpath.charAt(0) == 'w' && PDFpath.charAt(1) == 'w')) {
			URL url = new URL(PDFpath);
			InputStream is = url.openStream();
			BufferedInputStream fileParse = new BufferedInputStream(is);
			PDDocument document = null;
			document = PDDocument.load(fileParse);
			// Create our two String PDF's using setter method
			setPdfContent(new PDFTextStripper().getText(document));
			setPdfContent_W(pdfContent.replaceAll(" ", ""));
			
		}
		// Condition for file path:
		else {
			File file = new File(PDFpath);
			PDDocument document = null;
			document = PDDocument.load(file);
			// Create our two String PDF's using setter method
			setPdfContent(new PDFTextStripper().getText(document));
			setPdfContent_W(pdfContent.replaceAll(" ", ""));
		}
	}
	


	public void checkSpecs() throws IOException {
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
		Vector<String> D22Spec =new Vector<String>();
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
						// Update the vector
						specSetter(test);
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
						// Update the vector
						specSetter(test);
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
						
						// Update the vector
						specSetter(test);
					}
					divisionNumber++;
				}
			}
		}
		// If the provided specs do not contain division 22
		else {
			System.out.print("Specifications do not contain Division 22");
		}
	}
	
	
	public void printSpecs() {
		for (int i = 0; i < D22Spec.size(); i++) {
			System.out.print(D22Spec.get(i));
			System.out.print("\n");
		}
	}
	
	public void specSetter(String specNum) {
		D22Spec.add(specNum);
	}

	// Getter method
	public String getPdfContent() {
		return pdfContent;
	}
	
	// Setter Method - probably unused
	public void setPdfContent(String pdfContent) {
		this.pdfContent = pdfContent;
	}

	public String getPdfContent_W() {
		return pdfContent_W;
	}

	public void setPdfContent_W(String pdfContent_W) {
		this.pdfContent_W = pdfContent_W;
	}
}
