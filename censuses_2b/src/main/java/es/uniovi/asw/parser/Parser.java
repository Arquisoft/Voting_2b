package es.uniovi.asw.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.dbupdate.VoterRepository;
import es.uniovi.asw.util.Console;

/**
 * [-c1 -c2 -cN]    file1 [-c2]    file2    fileN [-c1 -c3]
 * 
 * The first format's parameters to generate Personal Letters (-c1, -c2, ..., -cN)
 * are only apply to all of the files
 * if they haven't got any parameters on its right (for example, the file2 hasn't got).
 * 
 * All the format's parameters are optionals.
 * 
 * Formats parameters:
 * 
 * -w	Word format
 * -p	PDF format
 * -t	TXT format
 * 
 * @author Nauce Lopez
 *
 */
public class Parser {

	@Autowired
	public static VoterRepository voterRepository;
	private static String command = "";
	
	public static void run(String... args) {
		if (args.length == 0 || args.length > 0 && !args[0].equals("//test")) {
			help(null);
			parse(args);
		}
	}
	
	private static void readCommand() {
		command = Console.readString(">");
		if (!command.equals("quit")) {
			if (command.equals("-h")) {
				help(null);
				readCommand();
			}
			else
				parse(command.split(" "));
		}
	}
	
	private static void readFile(String pathFile, String... writterFormats) {
		if (pathFile.endsWith(".xlsx"))
			new RCensusExcel(writterFormats).read(pathFile);
		else
			System.out.println(pathFile + " -> Extensión de fichero no reconocida");
	}
	
	private static boolean help(String help) {
		if (help== null || help.equals("-h")) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("help (-h):\n\n");
			sb.append("[-p1 -p2 ... -pN]   file1 [-p2]   file2   ...   fileN [-p1 -p3]\n\n");
			sb.append("-t\tFormato TXT\n");
			sb.append("-p\tFormato PDF\n");
			sb.append("-w\tFormato Word\n\n");
			sb.append("Los parametros (-p1 ... -pN) se indican para elegir los formatos de salida"
					+ " de las cartas personales.\n");
			sb.append("Los parametros del comienzo seran aplicados a todos los ficheros"
					+ " en caso de que no tengan a su derecha otros parametros especificados\n");
			sb.append("Si no se especifica ningun parametro, las cartas personales seran"
						+ " generadas en formato txt por defecto.\n\n");
			sb.append("quit - salir del programa");
			
			System.out.println(sb.toString());
			
			return false;
		}
		
		return true;
	}
	
	private static void parse(String... args) {
		List<String> writterFormatsToAll = new ArrayList<String>();
		List<String> writterFormats = new ArrayList<String>();
		String pathFile = null;
		
		int i = 0;
		
		while(i < args.length && args[i].startsWith("-")) {
			if (help(args[i]));
				writterFormatsToAll.add(args[i]);
			i++;
		}
		
		while(i < args.length) {
			pathFile = args[i];
			i++;
			
			while (i < args.length && args[i].startsWith("-")) {
				if (help(args[i]));
					writterFormats.add(args[i]);
				i++;
			}
			
			if (writterFormats.isEmpty())
				readFile(pathFile, writterFormatsToAll.toArray(new String[writterFormatsToAll.size()]));
			else
				readFile(pathFile, writterFormats.toArray(new String[writterFormats.size()]));
			
			writterFormats.clear();
		}
		
		readCommand();
	}
}
