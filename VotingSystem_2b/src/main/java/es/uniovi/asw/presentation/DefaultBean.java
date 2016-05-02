package es.uniovi.asw.presentation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("defaultBean")
@Scope("view")
public class DefaultBean {

	private String key = "Propiedad leída del bean";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String action() {

		return "exito";
	}

}
