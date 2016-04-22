package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.Main;
import es.uniovi.asw.util.SeleniumUtils;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps{
	
	private WebDriver driver = SeleniumUtils.getDriver();
	
  //Scenario 1
  @Cuando("^el cliente entra en la web /principal\\.xhtml$$")
  public void el_cliente_entra_en_la_web() throws Throwable {
	  driver.get("http://localhost:8080/principal.xhtml");
	  
  }

  @Entonces("^el cliente visualiza un mensaje de bienvenida")
  public void el_cliente_visualiza_un_mensaje_de_bienvenida() throws Throwable {
	  SeleniumUtils.esperaCargaPagina(driver, "text", "Principal", 5);  
	  driver.findElement(By.linkText("Principal")).click();
	  SeleniumUtils.finishTest(driver);
  }
  
	

	
  

  
}
