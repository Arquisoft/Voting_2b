package es.uniovi.asw;

import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.uniovi.asw.dbupdate.repositories.Repository;
import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;
import es.uniovi.asw.model.Voter;

/**
 * Created by Ignacio Fernandez on 21/04/2016.
 */
public class TestingUtils {

    static public void textoPresentePagina(WebDriver driver, String texto)
    {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
        assertTrue("Texto " + texto + " no localizado!", list.size() > 0);
    }

    static public WebElement EsperaCargaPaginaxpath(WebDriver driver, String xpath, int timeout)
    {
        WebElement resultado =
                (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return resultado;
    }

    static public void esperar(int seconds){
        try {
            java.lang.Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static   void restoreDB(){
        Repository.voteR.deleteAll();
        Repository.turnoutR.deleteAll();
        Repository.electionR.deleteAll();
    }



    static void insertVoterDB(String nif) {

        Voter v = new Voter();
        v.setEmail(nif+"@gmail.com");
        v.setName("prueba");
        v.setNif(nif);
        v.setPassword("1");
        if (Repository.voterR.findByNif(v.getNif()) != null)
            v = Repository.voterR.findByNif(v.getNif());
        Region r = new Region();
        r.setName("Barcelona");
        if (Repository.regionR.findByName(r.getName()) != null)
            r = Repository.regionR.findByName(r.getName());
        Constituency cons = new Constituency();
        cons.setName("Tarragona");
        if (Repository.constituencyR.findByName(cons.getName()) != null)
            cons = Repository.constituencyR.findByName(cons.getName());
        cons.setRegion(r);
        PollingPlace pp = new PollingPlace();
        pp.setId(100000L);
        if (Repository.pollingPlaceR.findOne(pp.getId()) != null)
            pp = Repository.pollingPlaceR.findOne(pp.getId());
        pp.setConstituency(cons);
        v.setPollingPlace(pp);
        Repository.regionR.save(r);
        Repository.constituencyR.save(cons);
        Repository.pollingPlaceR.save(pp);
        Repository.voterR.save(v);

    }


}
