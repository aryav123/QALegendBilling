package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditUserPage extends TestHelper {
    WebDriver driver;
    public EditUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _editFirstNameField="//input[@id='first_name']";
    @FindBy(xpath = _editFirstNameField) private WebElement editFirstNameField;
    private final String _editLastNameField="//input[@id='last_name']";
    @FindBy(xpath = _editLastNameField) private WebElement editLastNameField;
    private final String _editEmailField="//input[@id='email']";
    @FindBy(xpath = _editEmailField) private WebElement editEmailField;
    private final String _editUsernameField="//input[@id='username']";
    @FindBy(xpath = _editUsernameField) private WebElement editUsernameField;
    private final String _editPasswordField="//input[@id='password']";
    @FindBy(xpath = _editPasswordField) private WebElement editPasswordField;
    private final String _editConfirmPasswordField="//input[@id='confirm_password']";
    @FindBy(xpath = _editConfirmPasswordField) private WebElement editConfirmPasswordField;
    private final String _editPrefixField="//input[@id='surname']";
    @FindBy(xpath = _editPrefixField) private WebElement editPrefixField;
    private final String _saveButton="//button[@id='submit_user_button']";
    @FindBy(xpath = _saveButton) private WebElement saveButton;
    private final String _editPercentageField="//input[@id='cmmsn_percent']";
    @FindBy(xpath = _editPercentageField) private WebElement editPercentageField;
    private final String _firstNameFieldValidation="//label[@id='first_name-error']";
    @FindBy(xpath = _firstNameFieldValidation) private WebElement firstNameFieldValidation;
    public void editFirstName(String firstname) {
        page.enterText(editFirstNameField, firstname);
    }
    public void editLastName(String lastname) {
        page.enterText(editLastNameField, lastname);
    }
    public void editPassword(String password) {
        page.enterText(editPasswordField, password);
    }
    public void editConfirmPassword(String cpassword) {
        page.enterText(editConfirmPasswordField, cpassword);
    }
    public UsersPage clickSaveButton() {
        page.submitElement(saveButton);
        return new UsersPage(driver);
    }
    public String getEditUserPageTitle() {
        String title = page.getPageTitle(driver);
        return title;
    }
}
