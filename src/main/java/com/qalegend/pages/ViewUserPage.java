package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewUserPage extends TestHelper {
    public WebDriver driver;
    public ViewUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _viewEmailID="//strong[text()='Email: ']//following-sibling::text()";
    @FindBy(xpath =_viewEmailID ) private WebElement viewEmailID;
    private final String _viewRole="//strong[text()='Role: ']//following-sibling::text()";
    @FindBy(xpath =_viewRole )private WebElement viewRole;
    private final String _viewUserName="//strong[text()='Username: ']//following-sibling::text()";
    @FindBy(xpath =_viewUserName )private WebElement viewUserName;
    public String getEmailID(){
        System.out.println("test--------0"+viewEmailID);
        String email=page.getElementText(viewEmailID);
        System.out.println("test--------1"+email);
        return email;
    }
    public String getRole(){
        String role=page.getElementText(viewRole);
        return role;
    }
    public String getUserName(){
        String username=page.getElementText(viewUserName);
        return username;
    }
}
