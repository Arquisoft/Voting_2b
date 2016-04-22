package es.uniovi.asw.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import es.uniovi.asw.model.PollingPlace;

public class CrearColegioSteps {

	PollingPlace p;
	
	@When("^Creamos colegio /$")
	  public void crear_colegio() throws Throwable {
	    p = new PollingPlace();
	  }

	  @Then("^le asignamos datos$")
	  public void asignar_datos(int status) throws Throwable {
	    long id = 1;
		  p.setId(id);
	  }

	  @Then("^comprobar_resultados \"([^\"]*)\"$")
	  public void comprobar_resultados(String str) throws Throwable {
	  
	  System.out.println(p.getId());
	  }
}
