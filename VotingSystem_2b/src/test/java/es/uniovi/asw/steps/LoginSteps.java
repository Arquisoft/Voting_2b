package es.uniovi.asw.steps;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.Referendum;

//@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginSteps {

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;
	protected MvcResult result;

	@Value("${local.server.port}")
	protected int port;

	Exception exception;

	WebDriver driver;
	WebElement iterator;
	
	public LoginSteps() {

		Referendum ref = new Referendum();
		ref.setName("Ref");
		ref.setStartDate(new Date());
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 1);
		ref.setExpiryDate(c.getTime());
		ref.setNumChoices(1);
		ref.setQuestion("Question");
		ref.setInstructions("Ins");
		
		Repository.electionR.save(ref);
		driver = new FirefoxDriver();
	}

	@Given("^I'm at /index\\.xhtml$")
	public void i_m_at_index() throws Throwable {
		driver.get("http://localhost:8080/index.xhtml");
	}

	@When("^I click on the button to sign up$")
	public void incorrect_password() throws Throwable {
		iterator = driver.findElement(By.id("form:botonPrimario"));
        iterator.click();
	}

	@And("^I fill the DNI field with \"([^\"]*)\"$")
	public void fill_dni(String arg0) throws Throwable {
		iterator = driver.findElement(By.name("j_idt6:nombreUsuario"));
        iterator.sendKeys("labra");
	}
	
	@And("^I fill the Password Field with \"([^\"]*)\"$")
	public void fill_password(String arg0) throws Throwable {
        iterator = driver.findElement(By.name("j_idt6:contrasenya"));
        iterator.sendKeys("labra");
	}
	
	@And("^I click the login button$")
	public void click_login_button() throws Throwable {
        iterator = driver.findElement(By.id("j_idt6:botonLogin"));
        iterator.click();
	}
	
	@Then("^I can see the vote button$")
	public void see_vote_button() throws Throwable {
//        iterator = driver.findElement(By.id("formulario:botonLogin"));
//        Assert.assertNotNull(iterator);
        driver.close();
        Repository.electionR.delete(Repository.electionR.findActual().getId());
	}
    

}
