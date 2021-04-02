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
	
	/////////////////////////////////////////////////////////////////////////
	
	
	// Public members:
	public Vector<String> specInfo = new Vector<String>(); // Store data with "Spec Section - *product data*", per entry
	public Vector<String> D22Spec  = new Vector<String>(); //Holds spec sections within Division 22
	public Vector<String> pageVec  = new Vector<String>(); //Holds page numbers of spec sections stored in D22Spec
	public int curStartPage; 
	public int curEndPage;
	
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
		
		String tempStr = "endofsection";
		String tempStr1 = "ENDOFSECTION";
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
							// TEST XXXXXX or 220000
							for(int i = 0; i < strDivNum.length(); i++) {
								test = test.concat(String.valueOf(strDivNum.charAt(i)));
							}
							// TEST1 XX XXXX or 22 0000
							for(int i = 0; i < strDivNum.length(); i++) {
								if(i != 2) {
									test1 = test1.concat(String.valueOf(strDivNum.charAt(i)));
								}
								else {
									test1 = test1.concat(" ");
									test1 = test1.concat(String.valueOf(strDivNum.charAt(i)));
								}
							}
							// TEST 2 XX XX XX 22 00 00 
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
	
	// Method that finds product data that requires submittals
	public void findProductData() throws IOException {
		
		for(int i = 0; i < pageVec.size(); i++) {
			
			// Get our page numbers
			ptoi(pageVec.elementAt(i));
			//Reading page by page
			pdf.setStartPage(curStartPage);
			pdf.setEndPage(curEndPage);
			pdfContent   = pdf.getText(document);
			pdfContent_W   = pdf.getText(document);
			
			//Set pages to be our spec sections
			// Figure out what format our spec sections are
			// either "2.1" or "2.01"
			String tempStr = "2.1";
			String altStr = "2.01";
			int minIndex = pdfContent.indexOf("\n" + "2.1") - 10;
			int tempIndex = pdfContent.indexOf("\n2.1");
			int altIndex = pdfContent.indexOf("\n2.01");
			if((tempIndex - 10 > 0  && altIndex - 10 > 0) && (tempIndex > altIndex)) {
				tempStr = "2.01";
				minIndex = altIndex - 10;
			}
			String section = D22Spec.elementAt(i) + " ";
			
			
			//  .1  // .0.1 // .0.0.1 // .01
			int counter_1 = 1, counter_01 = 1, counter_001 = 1, counter_X1 = 1;
			
			
			if(tempStr.length() < 4) {
				//if pdfContent contains "2.#" - i.e. *2.1*
				while(pdfContent.contains("\n" + tempStr + " ") && pdfContent.indexOf(tempStr + " ") > minIndex) {
					
					// Get index of the sub section
					int startIndex = pdfContent.indexOf("\n" + tempStr + " ");
					// Get index of the rest of that line = description
					int endIndex = pdfContent.indexOf("\r",startIndex);
					String pushString = section.concat(" " + pdfContent.substring(startIndex + 1,endIndex));
					specInfo.add(pushString);
					//System.out.print(specInfo.elementAt(specInfo.size()-1));
					//System.out.print("\n");
					
					//if pdfContent contains "2.#.#" - i.e. *2.1.1*
					String tempStr2 = tempStr.concat(".1");
					while(pdfContent.contains("\n" + tempStr2 + " ") && pdfContent.indexOf(tempStr2 + " ") >= minIndex) {
			
						// Get index of the sub section
						startIndex = pdfContent.indexOf("\n" + tempStr2 + " ");
						//int testIndex = pdfContent.lastIndexOf("\n" + tempStr2 + " ");
						// Get index of the rest of that line = description
						endIndex = pdfContent.indexOf("\r",startIndex);
						pushString = section.concat(" " + pdfContent.substring(startIndex + 1,endIndex));
						specInfo.add(pushString);
						//System.out.print(specInfo.elementAt(specInfo.size()-1));
						//System.out.print("\n");
						
						//Check if pdfContent contains "2.#.#.#" - i.e. *2.1.1.1*
						String tempStr3 = tempStr2.concat(".1");
						while(pdfContent.contains("\n" + tempStr3 + " ") && pdfContent.indexOf(tempStr3 + " ") >= minIndex) {
							
							// Get index of the sub section
							startIndex = pdfContent.indexOf("\n" + tempStr3+ " ");
							// Get index of the rest of that line = description
							endIndex = pdfContent.indexOf("\r",startIndex);
							pushString = section.concat(" " + pdfContent.substring(startIndex + 1,endIndex));
							specInfo.add(pushString);
							//System.out.print(specInfo.elementAt(specInfo.size()-1));
							//System.out.print("\n");
							
							// Rebuild the string before iterating
							// 2.#.#.#
							tempStr3 = "2.";
							tempStr3 = tempStr3.concat(String.valueOf(counter_1));
							tempStr3 = tempStr3.concat("." + String.valueOf(counter_01));
							counter_001++;
							tempStr3 = tempStr3.concat("." + String.valueOf(counter_001));
								
						}
						//reset our check string and counter
						counter_001 = 1;
						tempStr3 = "";
						// Rebuild the string before iterating
						// 2.#.#
						tempStr2 = "2.";
						tempStr2 = tempStr2.concat(String.valueOf(counter_1));
						counter_01++;
						tempStr2 = tempStr2.concat("." + String.valueOf(counter_01));
					}
					
					//reset counter and tempStr2
					counter_01 = 1;
					tempStr2 = "";
					// Rebuild the string before iterating
					tempStr = "2.";
					counter_1++;	
					tempStr = tempStr.concat(String.valueOf(counter_1));
				}
				
			}
			 // Perform our same search as above but without "\n"
			else {	
					while(pdfContent_W.contains("\n" + tempStr) && pdfContent_W.lastIndexOf("\n" + tempStr) > minIndex) {
					// Get index of the sub section
					int startIndex = pdfContent_W.indexOf("\n" + tempStr);
					// Get index of the rest of that line = description
					int endIndex = pdfContent_W.indexOf("\r",startIndex);
					String pushString = section.concat(" " + pdfContent_W.substring(startIndex + 1,endIndex));
					specInfo.add(pushString);
				
					counter_X1++;
					if(counter_X1 < 10) {
						tempStr = "2.0";
					}
					else {
						tempStr = "2.";
					}	
					tempStr = tempStr.concat(String.valueOf(counter_X1));
				}
			}
		}
		
	}
		

	
	// Method that converts the page numbers from strings to integers
	public void ptoi(String str) {
		
		// find the index of the "-"
		int tempInt = str.indexOf('-');
		
		// find the last index of the string
		int endInt = str.length();
		
		// Using string methods, get start page and end page of passed in string
		// and modify public members.
		String tempStr = str.substring(0,tempInt);
		curStartPage = Integer.parseInt(tempStr);
		tempStr = str.substring(tempInt+1,endInt);
		curEndPage = Integer.parseInt(tempStr);
		
	}
	
	public void printSpecInfo() {
		for (int i = 0; i < specInfo.size(); i++) {
			System.out.print(specInfo.get(i));
			System.out.print("\n");
		}
	}
	
	// Method that prints Spec sections
	public void printSpecs() {
		for (int i = 0; i < D22Spec.size(); i++) {
			System.out.print(D22Spec.get(i));
			System.out.print("\n");
		}
	}
	// Method that prints page numbers of found Spec sections
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
