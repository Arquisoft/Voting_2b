package es.uniovi.asw.voterCount;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import es.uniovi.asw.voterCount.business.ValidatorService;
import es.uniovi.asw.voterCount.business.impl.SimpleValidatorService;

@Component("BeanValidator")
@Scope("session")
public class BeanValidator {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validar(){
		
		ValidatorService vs = new SimpleValidatorService();
		
		boolean resultado = vs.validar(email, password);
		
		if(!resultado)
			return "fracaso";
		return "exito";
	}

	public String cerrarSesion(){
		setEmail("");
		setPassword("");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "exito";
	}
}
