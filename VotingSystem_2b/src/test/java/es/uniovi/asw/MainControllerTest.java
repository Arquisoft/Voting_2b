package es.uniovi.asw;

import static es.uniovi.asw.TestingUtils.EsperaCargaPaginaxpath;
import static es.uniovi.asw.TestingUtils.esperar;
import static es.uniovi.asw.TestingUtils.insertVoterDB;
import static es.uniovi.asw.TestingUtils.restoreDB;
import static es.uniovi.asw.TestingUtils.textoPresentePagina;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import es.uniovi.asw.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.dbupdate.InsertElectionInfoP;
import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.dbupdate.repositories.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class MainControllerTest {


    WebDriver driver;
    WebElement iterator;

    @Before
    public void setUp() {

        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/index.xhtml");
    }


    @After
    public void closeDriver() {


        driver.close();

    }

    @Test
    public void test01() {

        iterator = driver.findElement(By.id("form:botonPrimario"));
        iterator.click();

    }


    @Test
    public void test02() {

        test01();
        logIN("pepin", "pepon");
        textoPresentePagina(driver, "No existe el usuario o la contraseña es erronea");

    }

    @Test
    public void test03() {

        test01();
        logIN("admin", "admin");
        textoPresentePagina(driver, "Portal de administración");

    }

    @Test
    public void test04() {

        test03();
        iterator = EsperaCargaPaginaxpath(driver, "/html/body/div/ul/li[1]/div[2]/div[1]/a", 1);
        iterator.click();

    }


    @Test
    public void test05() {

        chooseConfigOption("abi");
        esperar(1);
        textoPresentePagina(driver, "Listas Abiertas");
    }

    @Test
    public void test06() {

        chooseConfigOption("cerr");
        esperar(1);
        textoPresentePagina(driver, "Listas Cerradas");


    }


    @Test
    public void test07() {


        insertVoterDB("1234567");
        String fecha = fechaLater(-1);
        System.out.println(fecha + "de ayer");
        fillRef(driver, fecha, "Elecciones creadas con exito");
        iterator = driver.findElement(By.id("linkInicio"));
        esperar(1);
        iterator.click();
        esperar(3);
        test01();
        esperar(1);
        long t1,t2;
        t1= System.currentTimeMillis();
        logIN("1234567", "1");
        textoPresentePagina(driver, "TestR");
        t2=System.currentTimeMillis();
        assertEquals(true,((t2-t1)/1000)<3L);


        iterator = EsperaCargaPaginaxpath(driver, "//*[@id=\"formId:treebox\"]/div/div", 1);
        iterator.click();
        iterator = EsperaCargaPaginaxpath(driver, "//*[@id=\"formId:treebox\"]/div/div/input", 1);
        iterator.click();
        iterator.sendKeys("Si");
        iterator.sendKeys(Keys.ARROW_DOWN);
        iterator.sendKeys(Keys.ENTER);
        iterator = driver.findElement(By.id("formId:Votar"));
        /*
        * Atribuo de calidad 24
        * */
        t1= System.currentTimeMillis();
        iterator.click();
        esperar(1);
        textoPresentePagina(driver, "Ha votado correctamente, muchas gracias por su participación.");
        t2 = System.currentTimeMillis();
        assertEquals(true,((t2-t1)/1000)<4L);


    }


    @Test
    public void test08() {

        long t1,t2;
        t1= System.currentTimeMillis();
        insertEleccionesAbiertasTest();
        t2=System.currentTimeMillis();
        System.out.println(t2-t1);
        assertEquals(true,((t2-t1)/1000)<3L);

        iterator = driver.findElement(By.id("form:botonPrimario"));
        iterator.click();
        logIN("1234567", "1");
        esperar(2);
        iterator = EsperaCargaPaginaxpath(driver, "//*[@id=\"formulario:j_idt7:0:j_idt13\"]/div[2]/span", 2);
        iterator.click();
        iterator = driver.findElement(By.id("formulario:botonLogin"));
        iterator.click();

        textoPresentePagina(driver, "¡Gracias por votar!");

    }


    public void insertEleccionesAbiertasTest() {
        restoreDB();
        Calendar c = Calendar.getInstance();
        OpenList openList = new OpenList();
        openList.setName("OpenList");
        openList.setStartDate(c.getTime());
        openList.setNumChoices(1);
        c.add(Calendar.DATE, 2);
        openList.setExpiryDate(c.getTime());
        Object result = new InsertElectionInfoP(true).insertOpenList(openList);
        assertTrue(result!=null);



    }

    @Test
    public void test09() {

        long t1,t2;
        t1= System.currentTimeMillis();
        creaCerradas();
        t2=System.currentTimeMillis();
        System.out.println(t2-t1);
        assertEquals(true,((t2-t1)/1000)<3L);
        esperar(10);
        iterator = driver.findElement(By.id("form:botonPrimario"));
        esperar(5);
        iterator.click();
        logIN("1234567", "1");
        esperar(10);
        iterator = driver.findElement(By.id("j_idt7:table:0:j_idt10"));
        iterator.click();
        esperar(3);
        textoPresentePagina(driver, "Ha votado correctamente, muchas gracias por su participación.");
        Election election = Repository.electionR.findActual();
        int numParticipaciones =  Repository.turnoutR.findByElection(election).size();
        List<Vote> vote = Repository.voteR.findByElection(election);
        int numvotes  =vote.size();
        assertEquals(numParticipaciones,numvotes);






    }

    @Test
    public void test10() {


        test03();
        esperar(2);
        iterator = EsperaCargaPaginaxpath(driver, "/html/body/div/ul/li[3]/div[2]/div[1]/a", 1);
        esperar(2);
        iterator.click();
        esperar(5);
        iterator = driver.findElement(By.id("input_formId:j_idt9"));
        esperar(2);
        iterator.click();
        iterator.sendKeys("1234567");
        esperar(2);
        iterator = driver.findElement(By.id("formId:j_idt10"));
        iterator.click();
        esperar(5);
        textoPresentePagina(driver, "Ya ha emitido su voto");



    }




    private void creaCerradas() {

        Calendar c = Calendar.getInstance();
        ClosedList closedList = new ClosedList();
        closedList.setName("ClosedList");
        closedList.setStartDate(c.getTime());
        c.add(Calendar.DATE, 3);
        closedList.setExpiryDate(c.getTime());

        Repository.turnoutR.deleteAll();
        Repository.voteR.deleteAll();
        OpenList current = (OpenList) Repository.electionR.findActual();
        List<Candidate> candidates = Repository.candidateR.findByElection(current);

        candidates.forEach(x -> {
            x.removeElection(current);
            Repository.candidateR.save(x);
            current.removeCandidate(x);
        });
        Repository.electionR.deleteAll();

        Object result = new InsertElectionInfoP(true).insertClosedList(closedList);
        assertTrue(result!=null);

    }


    private void chooseConfigOption(String choice) {


        test04();
        iterator = EsperaCargaPaginaxpath(driver, "//*[@id=\"j_idt7:treebox\"]/div/div", 1);
        iterator.click();
        iterator = EsperaCargaPaginaxpath(driver, " //*[@id=\"j_idt7:treebox\"]/div/div/input", 1);
        iterator.click();
        iterator.sendKeys(choice);
        iterator.sendKeys(Keys.ARROW_DOWN);
        iterator.sendKeys(Keys.ENTER);
        EsperaCargaPaginaxpath(driver, "//*[@id=\"j_idt7:Configurar\"]/span[2]", 1).click();


    }


    private void logIN(String user, String password) {
        iterator = driver.findElement(By.name("j_idt6:nombreUsuario"));
        iterator.sendKeys(user);
        iterator = driver.findElement(By.name("j_idt6:contrasenya"));
        iterator.sendKeys(password);
        iterator = driver.findElement(By.id("j_idt6:botonLogin"));
        iterator.click();
    }

    private void fillInput(String text, String idInput) {

        iterator = driver.findElement(By.name(idInput));
        iterator.click();
        iterator.sendKeys(text);

    }

    private void fillReferendum(String name, String fechaInicio, String fechafinal, String instrucciones, String question) {

        fillInput(name, "formReferendum:nombreElecciones");
        fillInput(fechaInicio, "formReferendum:calendarioInicio");
        fillInput(fechafinal, "formReferendum:calendarioFinal");
        fillInput(instrucciones, "formReferendum:instrucciones");
        fillInput(question, "formReferendum:referendumInput");
    }


    private void fillRef(WebDriver driver, String fechaInicio, String texto) {

        String fecha = fechaLater(1);
        System.out.println(fecha + "de mañana");


        chooseConfigOption("ref");
        esperar(1);
        textoPresentePagina(driver, "Referendum");
        esperar(2);
        fillReferendum("TestR", fechaInicio, fecha, "instrucciones", "RefQ");
        iterator = driver.findElement(By.id("formReferendum:crearReferendum"));
        iterator.click();
        esperar(3);
        textoPresentePagina(driver, texto);

    }


    private String fechaLater(int resta) {
        int day, month, year;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, resta);
        day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        month = cal.get(Calendar.MONTH);
        month++;
        year = cal.get(Calendar.YEAR);
        String fecha = month + "/" + day + "/" + year + " 0:00 PM";
        return fecha;
    }
}