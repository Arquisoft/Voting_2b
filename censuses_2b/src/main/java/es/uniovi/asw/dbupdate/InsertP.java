package es.uniovi.asw.dbupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.parser.CheckFails;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.VoterInfo;
import es.uniovi.asw.reportGeneration.PasswordGenerator;


public class InsertP implements Insert {
	
	@Override
	public List<Voter> insert(List<VoterInfo> voterValues, String path) {
		CheckFails.file = path;
		List<Voter> voters = new ArrayList<Voter>();
		Voter voter;
		// Inserta y verifica en la base de datos
		for (VoterInfo v : voterValues) {
			if (CheckFails.check(v)) {
				voter = new Voter(v.getName(), v.getNIF(), v.getEmail(),
						Integer.parseInt((v.getPollingPlace().replace(".0", ""))));
				PasswordGenerator.generatePasswords(voter);
				
				try {
					Parser.voterRepository.save(voter);
					voters.add(voter);
				} catch (DataIntegrityViolationException e) {
					voter = Parser.voterRepository.findByEmail(voter.getEmail());
					voters.add(voter);
				}
			}
		}
		
		System.out.println(voterValues.size() + " filas leídas del fichero");
		
		if (voterValues.size() > voters.size())
			System.out.println((voterValues.size() - voters.size()) + " filas con errores en el fichero (ver fails.log para más información)");
		
		System.out.println("Se han registrado " + voters.size() + " votantes y generado las cartas personales");
		
		// Devuelve los votantes insertados
		return voters;
	}

}
