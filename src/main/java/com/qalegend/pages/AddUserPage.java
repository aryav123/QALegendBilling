package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage extends TestHelper {
    WebDriver driver;
    public AddUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _firstNameField="//input[@id='first_name']";
    @FindBy(xpath = _firstNameField) private WebElement firstNameField;
    private final String _lastNameField="//input[@id='last_name']";
    @FindBy(xpath = _lastNameField) private WebElement lastNameField;
    private final String _emailField="//input[@id='email']";
    @FindBy(xpath = _emailField) private WebElement emailField;
    private final String _usernameField="//input[@id='username']";
    @FindBy(xpath = _usernameField) private WebElement usernameField;
    private final String _passwordField="//input[@id='password']";
    @FindBy(xpath = _passwordField) private WebElement passwordField;
    private final String _confirmPasswordField="//input[@id='confirm_password']";
    @FindBy(xpath = _confirmPasswordField) private WebElement confirmPasswordField;
    private final String _prefixField="//input[@id='surname']";
    @FindBy(xpath = _prefixField) private WebElement prefixField;
    private final String _saveButton="//button[@id='submit_user_button']";
    @FindBy(xpath = _saveButton) private WebElement saveButton;
    private final String _percentageField="//input[@id='cmmsn_percent']";
    @FindBy(xpath = _percentageField) private WebElement percentageField;
    private final String _firstNameFieldValidation="//label[@id='first_name-error']";
    @FindBy(xpath = _firstNameFieldValidation) private WebElement firstNameFieldValidation;
    public void enterPrefix(String prefix) {
        page.enterText(prefixField, prefix);
    }
    public void enterFirstName(String firstname) {
        page.enterText(firstNameField, firstname);
    }
    public String firstNameFieldValidation() {
        String validationMessage=page.getElementText(firstNameFieldValidation);
        return validationMessage;
    }
    public String getAddUserPageTitle() {
        String title = page.getPageTitle(driver);
        return title;
    }
    public void enterLastName(String lastname) {
        page.enterText(lastNameField, lastname);
    }
    public void enterEmail(String email) {
        page.enterText(emailField, email);
    }
    public void enterUsername(String username) {
        page.enterText(usernameField, username);
    }
    public void enterPassword(String password) {
        page.enterText(passwordField, password);
    }
    public void enterConfirmPassword(String cpassword) {
        page.enterText(confirmPasswordField, cpassword);
    }
    public void enterPercentage(String percentage) {
        page.enterText(percentageField, percentage);
    }
    public UsersPage clickSaveButton() {
        page.submitElement(saveButton);
        return new UsersPage(driver);
    }
}
