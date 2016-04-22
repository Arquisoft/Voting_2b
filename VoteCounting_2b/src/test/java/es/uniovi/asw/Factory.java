package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Factory {


	static public void textoPresentePagina(WebDriver driver, String texto)
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));		
		assertTrue("Texto " + texto + " no localizado!", list.size() > 0);			
	}
	//Mueve el ratón a la opción de menú submenu(desplegable). Evento hover
	 //y clicka la opcion opcionclick
	 static public void clickSubopcionMenuHover(WebDriver driver, String submenu, String opcionclick)
	 {
	  //Pasamos el raton por el submenu de Gestion de alumnos para
	  //que aparezca el menu desplegable
	  Actions builder = new Actions(driver);
	  WebElement hoverElement = driver.findElement(By.id(submenu));
	  builder.moveToElement(hoverElement).perform();  
	  //Pinchamos la opcion opcionclick
	  By locator = By.id(opcionclick);
	  driver.findElement(locator).click();   
	 }
}