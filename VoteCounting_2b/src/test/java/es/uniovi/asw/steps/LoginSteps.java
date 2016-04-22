package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;
import es.uniovi.asw.util.SeleniumUtils;

@ContextConfiguration(classes = Main.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginSteps extends Factory {

	private WebDriver driver = SeleniumUtils.getDriver();

	@Cuando("^el encargado entra en la web \"([^\"]*)\"$")
	public void el_encargado_entra_en_la_web(String arg1) throws Throwable {
		driver.get("http://localhost:8080/principal.xhtml");
		SeleniumUtils.esperaCargaPagina(driver, "text", "Bienvenido", 4);
	}

	@Entonces("^hace click en \"([^\"]*)\"$")
	public void hace_click_en(String arg1) throws Throwable {
		WebElement elemento2 = driver.findElement(By.id(arg1));
		elemento2.click();
		SeleniumUtils.esperaCargaPagina(driver, "text", "Junta Electoral", 4);
	}

	@Entonces("^se logea correctamente$")
	public void se_logea_correctamente() throws Throwable {
		WebElement elemento = driver.findElement(By.id("input_loginForm:email"));
		elemento.sendKeys("junta");
		elemento = driver.findElement(By.id("input_loginForm:password"));
		elemento.sendKeys("junta");
		elemento = driver.findElement(By.id("loginForm:login"));
		elemento.click();

		SeleniumUtils.finishTest(driver);
	}
}
