package es.uniovi.asw.seleniumTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ListaElecciones {
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
  public void testListaElecciones() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.linkText("Lista Elecciones")).click();
    driver.findElement(By.cssSelector("th.sorting_asc")).click();
    driver.findElement(By.cssSelector("th.sorting")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
    driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).sendKeys("cataluña");
    driver.findElement(By.id("bElection:0:j_idt29:j_idt30")).click();
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("25");
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("50");
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("100");
    driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).sendKeys("yes");
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
