package es.uniovi.asw.personalLetters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import es.uniovi.asw.model.Voter;

public class WordLetterWriter implements PersonalLetterWriter {

	@Override
	public void WriteLetter(Voter voter) {

		XWPFDocument document = new XWPFDocument();
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("letters/"+voter.getNif() + ".docx"));
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			SetFontStyle(run);
			FillDocument(voter, run);
			document.write(out);
			out.close();
			document.close();

		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido crear el archivo debido a que falta el archivo" + e.getMessage());
		} catch (IOException e) {
			System.err.println("No se ha podido crear el archivo problema de entrada y salida" + e.getMessage());
		}

	}

	private void FillDocument(Voter voter, XWPFRun run) {
		run.setText("Dear :" + "\t" + voter.getName());
		run.addBreak();
		run.setText("User :" + "\t" + voter.getEmail());
		run.addBreak();
		run.setText("Password :" + "\t" + voter.getPassword());
		run.addBreak();
		run.setText("Thanks for using our service, hope all went well.");
		run.addBreak();
		run.setText(new Date().toString());
	}

	private void SetFontStyle(XWPFRun run) {
		run.setFontFamily("Calibri");
		run.setFontSize(20);
	}

}
