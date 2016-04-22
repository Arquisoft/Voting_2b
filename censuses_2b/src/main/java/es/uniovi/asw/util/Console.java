package es.uniovi.asw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	protected static BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

	public static String readString() {
		try {
			return kbd.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readString(String msg) {
		print(msg + ": ");
		return readString();
	}

	public static void print(String string) {
		System.out.print(string);
	}

}
