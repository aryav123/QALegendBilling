package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomeTest extends Base {
    HomePage home;
    LoginPage login;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,enabled = true,description = "TC006_Verify home page title",groups = {"Smoke"})
    public void TC006_verifyHomePageTitle(){
        extentTest.get().assignCategory("Smoke");
        login=new LoginPage(driver);
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        login.enterUserName(userName);
        String userPassword=data.get(1).get(2);
        login.enterUserPassword(userPassword);
        home = login.clickLoginButton();
        String actUserName=home.getUserAccountNameText();
        String expUserName=data.get(1).get(3);
        Assert.assertEquals(actUserName,expUserName, ErrorMessages.USERNAME_FAILURE_MESSAGE);
        home.getHomePageTitle();
        extentTest.get().log(Status.PASS,"Expected username match with actual username");
    }
    @Test
    public void TC007_verifyDateIsDisplayed(){
    }
}
