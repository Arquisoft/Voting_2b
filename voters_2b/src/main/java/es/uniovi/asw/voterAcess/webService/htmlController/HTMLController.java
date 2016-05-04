package es.uniovi.asw.voterAcess.webService.htmlController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voterAcess.infrastructure.ErrorFactory;
import es.uniovi.asw.voterAcess.infrastructure.ErrorFactory.Errors;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;

/**
 * Se utiliza para gestionar las peticiones de tipo "get" que son recibidas por
 * el servidor web
 *
 */
@Controller
public class HTMLController
{
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userHTMLget(Model model)
	{
		return "user";
	}

	@RequestMapping(value = "/validarse", method = RequestMethod.POST)
	public String userHTMLpost(@RequestBody String parametros, Model model)
	{
		String[] parametro = parametros.split("&");
		
		if(parametro[0].split("=").length<=1)
			throw ErrorFactory.getErrorResponse(Errors.REQUIRED_EMAIL);
		
		if(parametro[1].split("=").length<=1)
			throw ErrorFactory.getErrorResponse(Errors.REQUIRED_PASSWORD);
		
		String email = parametro[0].split("=")[1].replace("%40", "@");
		String contraseña = parametro[1].split("=")[1];

		
		
		Voter voter = Repository.voterR.findByEmail(email);	
		
		if (voter != null)
		{
			if(voter.getPassword().compareTo(contraseña) == 0)
			{
				model.addAttribute("email", voter.getEmail());
				model.addAttribute("name", voter.getName());
				model.addAttribute("nif", voter.getNif());
				model.addAttribute("polling", voter.getPollingPlace().getId());
			}
			
			else { throw ErrorFactory.getErrorResponse(Errors.INCORRECT_PASSWORD); }
		}
		
		else { throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND); }
		
		
		return "result";
	}
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorResponse excep, Model model)
	{
		model.addAttribute("error", excep.getMessageStringFormat());
		
		return "error";
	}
}