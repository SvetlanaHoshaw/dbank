package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.concurrent.TimeUnit;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {
//this  hook suppoes to prevent registration scenario running this step, but it does not work
    @Before ("not @Registration")
    //make sure you take @Before from cucumber that will run before each
    //this step does not have to be in Cucumber feature file, it will run
    //on the background beafire each sceanrio
    //besides REgistration scenario where we add (Note: without this notRegistration also works)
    public void the_user_is_on_dbank_homepage() {

        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
     //   driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    //"not @NegativeRegistrationCases" - this does not work
    @After ("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario) {
        //so after running we cal method takeScrenshot from Driver class
        // to take screenshot if scenario is failed accodrind to method
        Driver.takeScreenshot(scenario);

        //we close driver after each scenario
        Driver.closeDriver();
    }
}
