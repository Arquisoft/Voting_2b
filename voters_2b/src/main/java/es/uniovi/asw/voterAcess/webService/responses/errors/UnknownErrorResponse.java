package es.uniovi.asw.voterAcess.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Unknown error")
public class UnknownErrorResponse extends ErrorResponse
{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessageJSONFormat()
	{
		return "{\"reason\": \"Unknown error\"}";
	}


	@Override
	public String getMessageStringFormat() {
		return "Unknown error";
	}
}