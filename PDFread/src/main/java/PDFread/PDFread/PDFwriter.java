package PDFread.PDFread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Vector;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFwriter {

	// Private Members:
	
	//private static PDDocument doc = new PDDocument();
	//, TOC, sectionTitle, submittal = new PDDocument();	
	//PDPage page = new PDPage();
	//coversheet.addPage( page );
	private PDFont font = PDType1Font.HELVETICA_BOLD;
	private PDPage page = new PDPage();
	private String titleStr, bodyStr = "";
	private Vector<String> filePathFromDataBase = new Vector<String>();
	private Vector<String> subsectionTitles = new Vector<String>();
	private int pageNumber = 0;
	
	

	
	// Public Members
	
	

   
	
	// Constructor
	public PDFwriter(Vector<String> dbVec, Vector<String> subsecVec) throws IOException {
		//Make sure we aren't pulling pri-ori files
		File reset = new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
		reset.delete();
		
		filePathFromDataBase.addAll(dbVec);
		subsectionTitles.addAll(subsecVec);
		
		//two for loops to create submittal without coversheet and TOC
		for(int i = 0; i < subsectionTitles.size(); i++) {
			writeSectionTitle(subsectionTitles.elementAt(i));
			for(int j = 0; j < filePathFromDataBase.size(); j++) {
				attachCutsheet(filePathFromDataBase.elementAt(j));
			}
			// Delete the working SubSection Title file
			File file = new File("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
			file.delete();
		}
		// Add Page numbers to our submittal
		File file = new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
        PDDocument document = PDDocument.load(file);
        //addPageNumbers(PDDocument, "format style", X_Offset, Y_Offset) 
        addPageNumbers(document,"Page {0}",60,35);
        document.save(new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf"));
        document.close();
		
        
        // Need to create table of contents
        
        // Need to create Coversheet
        
	};
	
	
	public void attachCutsheet(String path) throws IOException {
		
		boolean pdfFile = new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf").createNewFile();
		if(!pdfFile) {
			PDFMergerUtility PDFmerger = new PDFMergerUtility();
			File file = new File(path);
			
			PDFmerger.addSource("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
			PDFmerger.addSource("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
			PDFmerger.addSource(file);
			PDFmerger.setDestinationFileName("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
			PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
			
//			File tempFile1 = new File("..\\\\PDFread\\\\src\\\\main\\\\output\\\\test_submittal1.pdf");
//			File tempFile2 = new File("..\\\\PDFread\\\\src\\\\main\\\\output\\\\test_submittal.pdf");
//			tempFile1.renameTo(tempFile2);
		}
		else {
			PDFMergerUtility PDFmerger = new PDFMergerUtility();
			PDFmerger.setDestinationFileName("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
			File file = new File(path);
			PDFmerger.addSource("..\\PDFread\\src\\main\\output\\subsection_sheet.pdf");
			PDFmerger.addSource(file);
			PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		}
		
		
		//doc.load(file);
	}
	
	
	// Reference: https://zetcode.com/java/pdfbox/
	// change this to PDDocument instead of void after testing
	public void writeSectionTitle(String sectionTitle) throws IOException {
//		File file = new File("..\\PDFread\\src\\main\\output\\test_submittal.pdf");
//		doc = PDDocument.load(file);
//		if(doc.getNumberOfPages() > 0) {
//			doc.removePage(0);
//		}
		PDDocument doc = new PDDocument();
		
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
    doc.close();
}
	
	// Method that parses out the subsection title into a section title and body description
	public void parseString(String str) {
		
		int firstIndex = str.indexOf(".") - 1;
		int lastIndex = str.indexOf(" ", firstIndex);
		titleStr = "SECTION " + str.substring(firstIndex,lastIndex);
		bodyStr = str.substring(lastIndex + 1, str.length());	
	}
	
	//REF: https://stackoverflow.com/questions/16581471/adding-page-numbers-using-pdfbox
	public static void addPageNumbers(PDDocument document, String numberingFormat, int offset_X, int offset_Y) throws IOException {
        int page_counter = 1;
        for(PDPage page : document.getPages()){
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, false);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
            PDRectangle pageSize = page.getMediaBox();
            float x = pageSize.getLowerLeftX();
            float y = pageSize.getLowerLeftY();
            contentStream.newLineAtOffset(x+ pageSize.getWidth()-offset_X, y+offset_Y);
            String text = MessageFormat.format(numberingFormat,page_counter);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
            ++page_counter;
        }
    }

	
	
}
