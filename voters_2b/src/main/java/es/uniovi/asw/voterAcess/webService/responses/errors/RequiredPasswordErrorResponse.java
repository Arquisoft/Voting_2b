package es.uniovi.asw.voterAcess.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="The field 'Password' is required")
public class RequiredPasswordErrorResponse extends ErrorResponse
{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessageJSONFormat()
	{
		return "{\"reason\": \"The field 'Password' is required\"}";
	}


	@Override
	public String getMessageStringFormat() {
		return "The field 'Password' is required";
	}
}