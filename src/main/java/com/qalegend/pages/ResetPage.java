package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPage extends TestHelper {
    public WebDriver driver;
    public ResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String _resetEmailField = "//input[@id='email']";
    @FindBy(xpath = _resetEmailField)
    private WebElement resetEmailField;
    private final String _resetEmailSubmitButton = "//button[@type='submit']";
    @FindBy(xpath = _resetEmailSubmitButton)
    private WebElement resetEmailSubmitButton;
    private final String _invalidEmailErrorMessage="//input[@id='email']/following::span[@class='help-block']";
    @FindBy(xpath = _invalidEmailErrorMessage)
    private WebElement invalidEmailErrorMessage;
    public void enterInvalidEmail(String invalidEmail) {
        page.enterText(resetEmailField, invalidEmail);
    }
    public void resetEmailButtonClick() {
        page.submitElement(resetEmailSubmitButton);
    }
    public String getInvalidResetEmailText(){
        String invalidEmailErrorText=page.getElementText(invalidEmailErrorMessage);
        return invalidEmailErrorText;
    }

}

