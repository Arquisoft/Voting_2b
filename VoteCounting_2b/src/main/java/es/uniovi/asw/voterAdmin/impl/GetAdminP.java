package es.uniovi.asw.voterAdmin.impl;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.voterAdmin.GetAdmin;
import es.uniovi.asw.voterAdmin.impl.business.ValidatorService;
import es.uniovi.asw.voterAdmin.impl.business.classes.SimpleValidatorService;

@Component("BeanValidator")
@Scope("session")
public class GetAdminP implements GetAdmin{
	
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
