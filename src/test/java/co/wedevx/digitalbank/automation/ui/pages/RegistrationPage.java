package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends  BasePage {


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    MockData mockData = new MockData();


    @FindBy(id = "title")
    private WebElement titleDropDown;

    @FindBy(id = "firstName")
    private WebElement firstNameTxt;

    @FindBy(id = "lastName")
    private WebElement lastNameTxt;

    @FindBy(xpath = "//input[@value='M']")
    private WebElement genderMaleRadio;

    @FindBy(xpath = "//input[@value='F']")
    private WebElement genderFemaleRadio;

    @FindBy(id = "dob")
    private WebElement dobTxt;

    @FindBy(id = "ssn")
    private WebElement ssntTxt;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressTxt;

    @FindBy(id = "password")
    private WebElement passwordTxt;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTxt;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    private WebElement registerBtn;

    @FindBy(id = "address")
    private WebElement addressTxt;

    @FindBy(id = "locality")
    private WebElement localityTxt;

    @FindBy(id = "region")
    private WebElement regionTxt;

    @FindBy(id = "postalCode")
    public WebElement postCodeTxt;

    @FindBy(id = "country")
    public WebElement countryTxt;

    @FindBy(id = "homePhone")
    public WebElement homePhoneTxt;

    @FindBy(id = "mobilePhone")
    public WebElement mobilePhoneTxt;

    @FindBy(id = "workPhone")
    public WebElement workPhoneTxt;

    @FindBy(id = "agree-terms")
    public WebElement agreeTermsCheckbox;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement messageLabel;

    public void fillOutRegistrationForm(List<Map<String, String>> registrationPageTestDataListOfMap) {
        //Selenium class that helps to work with dropdowns
        Select titleSelect = new Select(titleDropDown);

        //as parameter for this method we added list of map where from feature file headers will be keys and first row will be values,
        // so we created firstRow and we wil  get values from first row of the fauture file


        Map<String, String> firsRow = registrationPageTestDataListOfMap.get(0);

        //if condition added for NEGATIVE scenarios where we will validate empty rows
        //if we don't add "if" , it throws null pointer exception
        if (firsRow.get("title") != null) {
            titleSelect.selectByVisibleText(firsRow.get("title"));
        }

        if (firsRow.get("firstName") != null) {
            firstNameTxt.sendKeys(firsRow.get("firstName"));
        }

        if (firsRow.get("lastName") != null) {
            lastNameTxt.sendKeys(firsRow.get("lastName"));
        }

        if (firsRow.get("gender") != null) {
            if (firsRow.get("gender").equalsIgnoreCase("M")) {
                genderMaleRadio.click();
            } else if (firsRow.get("gender").equalsIgnoreCase("F")) {
                genderFemaleRadio.click();
            } else {
                System.out.println("Wrong Gender");
            }
        }
        if (firsRow.get("dob") != null) {
            dobTxt.sendKeys(firsRow.get("dob"));
        }

        if (firsRow.get("ssn") != null) {
            if (firsRow.get("ssn").equalsIgnoreCase("random")) {
                ssntTxt.sendKeys(mockData.generateRandomSSN());
            }
        }

        if (firsRow.get("email") != null) {
            if (firsRow.get("email").equalsIgnoreCase("random")) {
                //we are taking mockNameAndEmail from mockData class
                //and after since :"email" is a key, so we are taking value of thsi key whicch is generated email
                Map<String, String> mockNameAndEmail = mockData.generateRandomNameAndEmail();
                emailAddressTxt.sendKeys(mockNameAndEmail.get("email"));
            }
        }
        if (firsRow.get("password") != null) {
            passwordTxt.sendKeys(firsRow.get("password"));
            confirmPasswordTxt.sendKeys(firsRow.get("password"));
        }

        nextBtn.click();

        if (addressTxt.isDisplayed()) {

            if (firsRow.get("address") != null) {
                addressTxt.sendKeys(firsRow.get("address"));
            }
            if (firsRow.get("locality") != null) {
                localityTxt.sendKeys(firsRow.get("locality"));
            }
            if (firsRow.get("region") != null) {
                regionTxt.sendKeys(firsRow.get("region"));
            }
            if (firsRow.get("postalCode") != null) {
                postCodeTxt.sendKeys(firsRow.get("postalCode"));
            }
            if (firsRow.get("country") != null) {
                countryTxt.sendKeys(firsRow.get("country"));
            }
            if (firsRow.get("homePhone") != null) {
                homePhoneTxt.sendKeys(firsRow.get("homePhone"));
            }
            if (firsRow.get("mobilePhone") != null) {
                mobilePhoneTxt.sendKeys(firsRow.get("mobilePhone"));
            }
            if (firsRow.get("workingPhone") != null) {
                workPhoneTxt.sendKeys(firsRow.get("workingPhone"));
            }

            if (firsRow.get("termsCheckMark") != null) {
                if (firsRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsCheckbox.click();
                }
            }
            registerBtn.click();
        }
    }

    public String getMesage() {
        return messageLabel.getText().substring(0, messageLabel.getText().lastIndexOf(".") + 1);
    }
    //      | title | firstName | lastName | gender | dob | ssn | email | password | address
    //      | locality | region | postalCode | country | homePhone | mobilePhone |
    //      workingPhone | termsCheckMark | fieldWithError | errorMessage |

    public String getRequiredFieldErrorMesage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "title":
                return titleDropDown.getAttribute("validationMessage");
            case "firstname":
                return firstNameTxt.getAttribute("validationMessage");
            case "lastname":
                return lastNameTxt.getAttribute("validationMessage");
            case "gender":
                return genderMaleRadio.getAttribute("validationMessage");
            case "dob":
                return dobTxt.getAttribute("validationMessage");
            case "ssn":
                return ssntTxt.getAttribute("validationMessage");
            case "email":
                return emailAddressTxt.getAttribute("validationMessage");
            case "password":
                return passwordTxt.getAttribute("validationMessage");
            case "address":
                return addressTxt.getAttribute("validationMessage");
            case "locality":
                return localityTxt.getAttribute("validationMessage");
            case "region":
                return registerBtn.getAttribute("validationMessage");
            case "postalcode":
                return postCodeTxt.getAttribute("validationMessage");
            case "country":
                return countryTxt.getAttribute("validationMessage");
            case "phonehome":
                return homePhoneTxt.getAttribute("validationMessage");
            case "mobilephone":
                return mobilePhoneTxt.getAttribute("validationMessage");
            case "workingphone":
                return workPhoneTxt.getAttribute("validationMessage");
            case "termscheckmark":
                return titleDropDown.getAttribute("validationMessage");
            default:
                return null;

        }
    }
}
