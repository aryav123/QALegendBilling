package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RolesPage extends TestHelper {
    public WebDriver driver;
    public RolesPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String getRolesPageTitle(){
        String title=page.getPageTitle(driver);
        return  title;
    }
}
