package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import com.qalegend.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestHelper {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String _userNameField = "username";
    @FindBy(id = _userNameField)
    private WebElement userNameField;
    private final String _passwordField = "password";
    @FindBy(id = _passwordField)
    private WebElement passwordField;
    private final String _loginButton = "password";
    @FindBy(id = _loginButton)
    private WebElement loginButton;
    private final String _invalidLoginErrorMessageField = "//input[@id='username']/following::span[@class='help-block']";
    @FindBy(xpath = _invalidLoginErrorMessageField)
    private WebElement invalidLoginErrorMessageField;
    private final String _rememberMeCheckBox = "//input[@name='remember']";
    @FindBy(xpath = _rememberMeCheckBox)
    private WebElement rememberMeCheckBox;
    private final String _forgotPasswordLink = "//a[@class='btn btn-link']";
    @FindBy(xpath = _forgotPasswordLink)
    private WebElement forgotPasswordLink;

    public String getLoginPageTitle() {
        String title = page.getPageTitle(driver);
        return title;
    }

    public void enterUserName(String userName) {
        page.enterText(userNameField, userName);
    }

    public void enterUserPassword(String password) {
        page.enterText(passwordField, password);
    }

    public HomePage clickLoginButton() {
        page.submitElement(loginButton);
        return new HomePage(driver);
    }
    public void clickOnRememberMeButton() {
        page.clickOnElement(rememberMeCheckBox);
    }

    public String getErrorMessageText(){
        String accountNameText=page.getElementText(invalidLoginErrorMessageField);
        return accountNameText;
    }
    public ResetPage clickForgotPasswordButton(){
        page.clickOnElement(forgotPasswordLink);
        return new ResetPage(driver);
    }
}
