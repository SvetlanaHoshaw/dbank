package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage = new RegistrationPage(getDriver());

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        //next row: it will navoagte to our website
        //we used digitalbank.properties file where we enter url link as a value, in this case it will be easier
        //to chnage url if we run our application in diffrent inviroments, so base url will be changed
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration page Titile mismatch");


    }
    @When("User creates account with following fields with mocked email and ssn")
    public void user_creates_account_with_following_fields_with_mocked_email_and_ssn(List<Map<String, String>> registrationTestDataListMap)  {
       //it will fill out all form of registration ,
        registrationPage.fillOutRegistrationForm(registrationTestDataListMap);
    }
    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
assertEquals(expectedSuccessMessage, registrationPage.getMesage().trim(), "SuccessMessage Mismatch");
    }

    @Then("the User should see the {string} required field error message  {string}")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        System.out.println(registrationPage.getRequiredFieldErrorMesage(fieldName));
        String actualErrorMessage = registrationPage.getRequiredFieldErrorMesage(fieldName);
        assertEquals(expectedErrorMessage, actualErrorMessage, "the error message of required " + fieldName + " mismatch");
    }
}
