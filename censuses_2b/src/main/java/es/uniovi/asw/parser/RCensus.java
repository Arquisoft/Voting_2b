package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.personalLetters.PersonalLetterGenerator;

public abstract class RCensus implements ReadCensus {
	
	private static Insert insertDB = new InsertR();
	private PersonalLetterGenerator letterGenerator;
	
	public RCensus(String... types) {
		this.letterGenerator = new PersonalLetterGenerator(types);
		if (types.length == 0)
			this.letterGenerator.chooseWritters("-t");
	}
	
	@Override
	public List<Voter> read(String path) {
		
		List<VoterInfo> votersValues = readFile(path);
		
		List<Voter> voters = insertDB.insert(votersValues, path);
		
		if (letterGenerator != null) {
			letterGenerator.setVoters(voters);
			letterGenerator.writeAllLetters();
		}
		
		return voters;
	}
	
	abstract List<VoterInfo> readFile(String path);
}
