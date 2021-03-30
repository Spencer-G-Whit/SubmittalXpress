// Author: Michael Svetlichny, Spencer Gariano, Joshua Shetler
// Modification: 3/21/2021 - alpha
// REF: https://www.tutorialspoint.com/pdfbox/pdfbox_quick_guide.htm
// REF: https://pdfbox.apache.org/docs/2.0.11/javadocs/org/apache/pdfbox/text/PDFTextStripper.html
// REF: https://pdfbox.apache.org/docs/2.0.11/javadocs/org/apache/pdfbox/pdmodel/PDPageTree.html
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
//import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFreader {
	
	// Private members:
	PDFTextStripper pdf = new PDFTextStripper();
	private String pdfContent; //PDF file with whitespace as a string
	private String pdfContent_W; //PDF file without whitespace as a string
	private PDDocument document; //PDF file
	
	// Public members:
	public Vector<String> D22Spec =new Vector<String>(); //Holds s
	public Vector<String> pageVec =new Vector<String>(); //Holds page numbers of spec sections stored in D22Spec
	
	
	// String Constructor:
	public PDFreader(String PDFpath) throws IOException {
		
		// Check if URL was passed in or if file path is passed in
		// Condition for URL:
		if((PDFpath.charAt(0) == 'h' && PDFpath.charAt(1) == 't') || (PDFpath.charAt(0) == 'w' && PDFpath.charAt(1) == 'w')) {
			URL url = new URL(PDFpath);
			InputStream is = url.openStream();
			BufferedInputStream fileParse = new BufferedInputStream(is);
			document = null;
			document = PDDocument.load(fileParse);
			// Create our two String PDF's using setter method
			// 
			setPdfContent(new PDFTextStripper().getText(document));
			//setPdfContent_W(pdfContent.replaceAll(" ", ""));
			pdfContent_W = pdfContent.replaceAll(" ", "");
			

			
		}
		// Condition for file path:
		else {
			File file = new File(PDFpath);
			document = null;
			document = PDDocument.load(file);
			// Create our two String PDF's using setter method
			setPdfContent(new PDFTextStripper().getText(document));
			//setPdfContent_W(pdfContent.replaceAll(" ", ""));
			pdfContent_W = pdfContent.replaceAll(" ", "");
		}
	}
	
	// Method that finds page numbers of spec sections
	public void pageFinder(int pageNumber, String temp) throws IOException {
		
		//pdfContent_W = pdfContent_W.replaceAll(" ", "");
		// take the passed in string and iterate by 1
		// Manipulate strings for searching conditions
		// if the conditional statement finds the a new section, then stop and return to checkSpecs()
		//int tempInt = Integer.parseInt(temp)+1;
		//String intStr = String.valueOf(tempInt);
		String tempStr = "endofsection";
		String tempStr1 = "ENDOFSECTION";
		//pageNumber = pageNumber + 57;
		pdf.setStartPage(pageNumber);
		pdf.setEndPage(pageNumber);
		pdfContent_W = pdf.getText(document);
		pdfContent_W = pdfContent_W.replaceAll(" ", "");
		pdfContent_W = pdfContent_W.toLowerCase();
		// check if page contains the section first
		// also check if the page has two different instances of the 
		// string we're looking for
		// If the page contains the string value of the specification section
		if((pdfContent_W.contains(temp) && (pdfContent_W.lastIndexOf(temp) != pdfContent_W.indexOf(temp))) ||
			(!pdfContent_W.contains(tempStr) || !pdfContent_W.contains(tempStr1))) { 
			 
			System.out.print("Found subsection ");
			System.out.print(temp);
			System.out.print(" on page ");
			System.out.print(pageNumber);
			System.out.print("\n");
			int pageCount = 0;
			int startPage = pageNumber;
			while(!pdfContent_W.contains(tempStr) && !pdfContent_W.contains(tempStr1)) { //start counting how many pages this specification section is
				pageCount++;
				pageNumber++;
				pdf.setStartPage(pageNumber);
				pdf.setEndPage(pageNumber);
				pdfContent_W = pdf.getText(document);
				pdfContent_W = pdfContent_W.replaceAll(" ", "");
				pdfContent_W = pdfContent_W.toLowerCase();
			}
			if(pdfContent_W.contains(tempStr) || pdfContent_W.contains(tempStr1)) {
				pageCount++;
				pageNumber++;
			}
			int endPage = startPage + (pageCount-1);
			String pages = String.valueOf(startPage) + "-" + String.valueOf(endPage);
			pageVec.add(pages);
			System.out.print("Successfully added page numbers of subsection \n");
		}
	}
	

	// The below method can be modified to accommodate all other divisions at a later date.
	// Method that checks if specifications contain Division 22
	public void checkSpecs() throws IOException {
		
		int newNum = 0;
		if(pdfContent.contains("DIVISION 22") || pdfContent.contains("division 22") || pdfContent_W.contains("section22") || pdfContent_W.contains("SECTION22")) {
			for(int pageNumber = 1; pageNumber < document.getNumberOfPages(); pageNumber++) {
				pdf.setStartPage(pageNumber);
				pdf.setEndPage(pageNumber);
				pdfContent_W = pdf.getText(document);   // No whitespace
				pdfContent_W = pdfContent_W.replaceAll(" ", "");
				pdfContent = pdf.getText(document);     // Yes whitespace
				String test = "", test1 = "", test2 = "";
				//System.out.println(pdfContent_W);
				// WRITE CODE TO ITERATE TO NEXT PAGE STILL!
				//create while loop to find the first sub section of the division
				int divisionNumber;
				if(newNum != 0) {
					divisionNumber = newNum;
				}
				else {
					divisionNumber = 220000;
				}
				while(divisionNumber <230000) {
					//pdfContent_W = pdfContent.replaceAll(" ", "");
					String strDivNum = String.valueOf(divisionNumber); //convert int into string
					String temp1 = "section";
					String temp2 = "SECTION";
					//pdfContent_W = pdfContent_W.toLowerCase();
					temp1 = temp1.concat(strDivNum);
					temp2 = temp2.concat(strDivNum);
					//pdfContent_W.contains(strDivNum) && (pdfContent_W.lastIndexOf(strDivNum)!= pdfContent_W.indexOf(strDivNum))) ||
					if((((pdfContent_W.contains(temp1) || pdfContent_W.contains(temp2)) && 
							(pdfContent_W.contains("1.1") || pdfContent_W.contains("part1") || pdfContent_W.contains("PART1"))))) {
						// Three for - loops to create our test cases to determine format 
							// TEST
							for(int i = 0; i < strDivNum.length(); i++) {
								test = test.concat(String.valueOf(strDivNum.charAt(i)));
							}
							// TEST1
							for(int i = 0; i < strDivNum.length(); i++) {
								if(i != 2) {
									test1 = test1.concat(String.valueOf(strDivNum.charAt(i)));
								}
								else {
									test1 = test1.concat(" ");
									test1 = test1.concat(String.valueOf(strDivNum.charAt(i)));
								}
							}
							// TEST 2
							for(int i = 0; i < strDivNum.length(); i++) {
								if(i != 2 || i != 5) {
									test2 = test2.concat(String.valueOf(strDivNum.charAt(i)));
								}
								else {
									test2 = test2.concat(" ");
									test2 = test2.concat(String.valueOf(strDivNum.charAt(i)));
								}
							}
							if(pdfContent.contains(test1)) {
								D22Spec.add(test1);
								pageFinder(pageNumber, strDivNum);
							}
							else if(pdfContent.contains(test2)) {
								D22Spec.add(test2);
								pageFinder(pageNumber, strDivNum);
							}
							else {
								D22Spec.add(test);
								pageFinder(pageNumber, strDivNum);
							}
							
						newNum = divisionNumber+1; 
						divisionNumber = 229999;
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
	public void printPages() {
		for(int i = 0; i < pageVec.size(); i++) {
			System.out.print(D22Spec.elementAt(i));
			System.out.print("'s pages are: ");
			System.out.print(pageVec.elementAt(i));
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
