package PDFread.PDFread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFwriter {

	// Private Members:
	
	private static PDDocument doc = new PDDocument();
	//, TOC, sectionTitle, submittal = new PDDocument();	
	//PDPage page = new PDPage();
	//coversheet.addPage( page );
	private PDFont font = PDType1Font.HELVETICA_BOLD;
	
	private PDDocument document = new PDDocument();
	private PDPage page = new PDPage();
	private String titleStr, bodyStr = "";
	private Vector<String> filePathFromDataBase = new Vector<String>();
	private Vector<String> subsectionTitles = new Vector<String>();
	

	
	// Public Members
	
	

   
	
	// Constructor
	public PDFwriter(Vector<String> dbVec, Vector<String> subsecVec) throws IOException {
		//doc.save("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
		//call all our methods here
		filePathFromDataBase.addAll(dbVec);
		subsectionTitles.addAll(subsecVec);
		//two for loops to create submittal without coversheet and TOC
		for(int i = 0; i < subsectionTitles.size(); i++) {
			writeSectionTitle(subsectionTitles.elementAt(i));
			for(int j = 0; j < filePathFromDataBase.size(); j++) {
				attachCutsheet(filePathFromDataBase.elementAt(j));
			}
		}
		File file = new File("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
		file.delete();
	};
	
	
	public void attachCutsheet(String path) throws IOException {
		
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		PDFmerger.setDestinationFileName("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
		File file = new File(path);
		PDFmerger.addSource("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
		PDFmerger.addSource(file);
		PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		//doc.load(file);
	}
	
	
	// Reference: https://zetcode.com/java/pdfbox/
	// change this to PDDocument instead of void after testing
	public void writeSectionTitle(String sectionTitle) throws IOException {
//		File file = new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
//		doc = PDDocument.load(file);
		if(doc.getNumberOfPages() > 0) {
			doc.removePage(0);
		}
		// Call this method to parse our passed in string into a title and body
		parseString(sectionTitle);
		int fontSize = 16; // Or whatever font size you want.
		float titleWidth = font.getStringWidth(titleStr) / 1000 * fontSize;
		float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
		float bodyWidth = font.getStringWidth(bodyStr) / 1000 * fontSize;
		int marginTop1 = 275;
		
        PDPage myPage = new PDPage();
        doc.addPage(myPage);
        
        try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
	        
            cont.beginText();

            cont.setFont(PDType1Font.TIMES_ROMAN, fontSize);
            //cont.setLeading(18f); // Set space between each line

            // see https://stackoverflow.com/questions/6507124/how-to-center-a-text-using-pdfbox for centering text
            cont.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - marginTop1 - titleHeight);
            // below info will be data passed in from the UI
            String line1 = titleStr;
            cont.showText(line1);
            
            //Reset back to (0,0) coordinates
            cont.newLineAtOffset((-(page.getMediaBox().getWidth() - titleWidth) / 2), -(page.getMediaBox().getHeight() - marginTop1 - titleHeight));
            cont.newLineAtOffset((page.getMediaBox().getWidth() - bodyWidth) / 2, page.getMediaBox().getHeight() - marginTop1 - titleHeight - 18f);
            //cont.newLine();
            String line2 = bodyStr;
            cont.showText(line2);

            cont.endText();
        }
    doc.save("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
}
	
	// Method that parses out the subsection title into a section title and body description
	public void parseString(String str) {
		
		int firstIndex = str.indexOf(".") - 1;
		int lastIndex = str.indexOf(" ", firstIndex);
		titleStr = "SECTION " + str.substring(firstIndex,lastIndex);
		bodyStr = str.substring(lastIndex + 1, str.length());	
	}
	
	
	
	// Methods
	
	
	
}
