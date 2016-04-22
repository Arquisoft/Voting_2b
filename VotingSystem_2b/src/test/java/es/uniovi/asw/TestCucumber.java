package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@IntegrationTest("server.port:8080")
@CucumberOptions(features = "src/test/resources/features")
public class TestCucumber{
}


