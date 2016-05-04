package es.uniovi.asw.voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voterAcess.ChangePassword;
import es.uniovi.asw.voterAcess.infrastructure.ErrorFactory;
import es.uniovi.asw.voterAcess.webService.responses.ChangePasswordResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;


@RestController
@Controller
public class ChangePasswordController implements ChangePassword{

private static final Logger log = LoggerFactory.getLogger(GetVoterInfoController.class);
	
	@RequestMapping(value="/changePassword",
			method=RequestMethod.POST, 
			headers = "Accept=application/json",
			produces="application/json")
	public String changePassword(@RequestBody ChangePasswordResponse data)
	{
		log.info("Password: " + data.getPassword() + " New Password: " + data.getNewPassword());
		
		if(data.getEmail().compareTo("")==0)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.REQUIRED_EMAIL);
		
		if(data.getPassword().compareTo("")==0)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.REQUIRED_PASSWORD);
		
		if(data.getNewPassword().compareTo("")==0)
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.REQUIRED_NEW_PASSWORD);
		
		
		Voter voter = Repository.voterR.findByEmail(data.getEmail());

		if (voter != null)
		{
			if(changePassword(data.getPassword(),data.getNewPassword(), voter))
			{
				return "{\"Resultado\":\"Contraseña cambiada correctamente\"}";
			}
			
			else { throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.INCORRECT_PASSWORD); }
		}
		
		else // Voter no encontrado
		{
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND);
		}
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse excep)
	{
		return excep.getMessageJSONFormat();
	}
	
	
	private boolean changePassword(String password, String newPassword,Voter voter)
	{
		if (password.compareTo(voter.getPassword()) == 0)
		{
			voter.setPassword(newPassword);
			Repository.voterR.save(voter);
			
			return true;
		}
		
		return false;
	}
}