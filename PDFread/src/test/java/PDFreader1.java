import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;

public class PDFreader1 {
	
	@Test
	public void readPDFTest() throws IOException {

		URL url = new URL("http://www.pavilionconstruction.com/uploaded-files/Platinum%20Place/Drawings%206.4.14/2014%2005%2016%20Project%20Manual%20INCOMPLETE.pdf");
		
		InputStream is = url.openStream();
		BufferedInputStream fileParse = new BufferedInputStream(is);
		PDDocument document = null;
		
		document = PDDocument.load(fileParse);
		String pdfContent = new PDFTextStripper().getText(document);
		System.out.println(pdfContent);
		
		Assert.assertTrue(pdfContent.contains("DIVISION 22")||pdfContent.contains("division 22"));
		Assert.assertTrue(pdfContent.contains("DIVISION 23")||pdfContent.contains("division 23"));
		
	}
}
