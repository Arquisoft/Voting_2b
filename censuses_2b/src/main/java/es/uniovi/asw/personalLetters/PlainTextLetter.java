package es.uniovi.asw.personalLetters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import es.uniovi.asw.model.Voter;

public class PlainTextLetter implements PersonalLetterWriter {

	private FileWriter writer;

	public PlainTextLetter() {

	}

	@Override
	public void WriteLetter(Voter voter)  {

		try {
			setWriter(new FileWriter( "letters/" +voter.getNif() + ".txt"));
			getWriter().write("Dear :" + "\t" + voter.getName() + "\n");
			getWriter().write("User :" + "\t" + voter.getEmail() + "\n");
			getWriter().write("Password :" + "\t" + voter.getPassword() + "\n");
			getWriter().write("Thanks for using our service, hope all went well." + "\n");
			getWriter().write(new Date().toString());
			getWriter().close();
		} catch (IOException e) {
			System.err.println("No se ha podido crear el archivo" + e.getMessage());
		}

	}

	public FileWriter getWriter() {
		return writer;
	}

	public void setWriter(FileWriter writer) {
		this.writer = writer;
	}

}
