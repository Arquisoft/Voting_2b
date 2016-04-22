package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import es.uniovi.asw.util.SeleniumUtils;

public class LookTiposEleccionesSteps {

	protected WebDriver driver = SeleniumUtils.getDriver();

	// Scenario 3, 4, 5

	@Cuando("^el cliente entra en la web \"([^\"]*)\"$")
	public void el_cliente_entra_en_la_web(String arg1) throws Throwable {
		driver.get("http://localhost:8080" + arg1);
	}

	@Entonces("^el cliente ve la tabla de con las respectivas elecciones de tipo \"([^\"]*)\"$")
	public void el_cliente_ve_la_tabla_de_con_las_respectivas_elecciones_de_tipo(String tipo) throws Throwable {
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
		driver.findElement(By.cssSelector("th.sorting_asc")).click();
		driver.findElement(By.cssSelector("th.sorting")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
		driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
	}

	@Y("^el cliente elije aquella que sea de su interés")
	public void el_cliente_elije_aquella_que_sea_de_su_interes() throws Throwable {
		driver.findElement(By.id("bElection:0:j_idt29:j_idt30")).click();
		
		SeleniumUtils.esperaCargaPagina(driver, "text", "Opciones", 4);
		
		SeleniumUtils.finishTest(driver);
	}
}
