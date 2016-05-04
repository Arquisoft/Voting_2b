package es.uniovi.asw.voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voterAcess.GetVoterInfo;
import es.uniovi.asw.voterAcess.infrastructure.ErrorFactory;
import es.uniovi.asw.voterAcess.webService.responses.VoterInfoResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;

@RestController
public class GetVoterInfoController implements GetVoterInfo
{
	private static final Logger log = LoggerFactory.getLogger(GetVoterInfoController.class);
	
	@RequestMapping(value="/user",
			method= RequestMethod.POST,
			headers ="Accept=application/json",
			produces={"application/json","application/xml"})
	public VoterInfoResponse getInfoVoter(@RequestBody Voter voter)
	{
		log.info("Datos peticion: "+voter.getEmail()+" "+voter.getPassword());
		
		if(voter.getEmail().compareTo("")==0)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.REQUIRED_EMAIL);
		
		else if(voter.getPassword().compareTo("")==0)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.REQUIRED_PASSWORD);
		
		
	
		Voter votr = Repository.voterR.findByEmail(voter.getEmail());
		
		if(votr == null)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND);
		
		else if(votr.getPassword().compareTo(voter.getPassword()) == 0)
			return new VoterInfoResponse(votr);
		
		else
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.INCORRECT_PASSWORD);
	}
	
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse excep)
	{
		return excep.getMessageJSONFormat();
	}
}