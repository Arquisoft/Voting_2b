package es.uniovi.asw.personalLetters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.model.Voter;

public class PDFLetterWriter implements PersonalLetterWriter {

	@Override
	public void WriteLetter(Voter voter) {

		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("letters/" + voter.getNif() + ".pdf"));
			document.open();
			putPdfInfo(document);
			fillDocument(voter, document);
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void fillDocument(Voter voter, Document document) throws DocumentException {
		document.add(new Paragraph("Dear :" + "\t" + voter.getName()));
		document.add(new Paragraph("User :" + "\t" + voter.getEmail()));
		document.add(new Paragraph("Password :" + "\t" + voter.getPassword()));
		document.add(new Paragraph("Thanks for using our service, hope all went well."));
	}

	private void putPdfInfo(Document document) {
		document.addAuthor("AswCensuses2B");
		document.addCreationDate();
		document.addCreator("AswCensuses2B.com");
		document.addTitle("Personal Voter Letter");
		document.addSubject("A pdf file with your password and user at the online service.");
	}

}
