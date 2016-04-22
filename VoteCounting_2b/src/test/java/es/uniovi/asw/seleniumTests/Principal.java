package es.uniovi.asw.seleniumTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Principal {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPrincipal() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.linkText("Principal")).click();
  }
  @Test
  public void testSeleniumPrincipalTitulos() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    assertEquals("Buscar por nombre:", driver.findElement(By.id("j_idt13:j_idt17")).getText());
    assertEquals("Buscar por fecha de inicio:", driver.findElement(By.id("j_idt13:j_idt23")).getText());
    assertEquals("Buscar por fecha de fin:", driver.findElement(By.id("j_idt13:j_idt29")).getText());
    assertEquals("Criterios de búsqueda de un proceso electoral", driver.findElement(By.cssSelector("h4.panel-title")).getText());
    driver.findElement(By.id("j_idt13:j_idt36")).click();
  }
  
  @Test
  public void testSeleniumReferencias() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("j_idt7")).click();
    driver.findElement(By.id("j_idt8")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt10")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt11")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt12")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
