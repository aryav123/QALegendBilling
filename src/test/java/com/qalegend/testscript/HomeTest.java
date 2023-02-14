package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.utilities.DateUtility;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomeTest extends Base {
    HomePage home;
    LoginPage login;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,enabled = true,description = "TC006_Verify home page title",groups = {"Regression"})
    public void TC006_verifyHomePageTitle(){
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        login.enterUserName(userName);
        String userPassword=data.get(1).get(2);
        login.enterUserPassword(userPassword);
        home = login.clickLoginButton();
        String actUserName=home.getUserAccountNameText();
        String expUserName=data.get(1).get(3);
        home.getHomePageTitle();
        Assert.assertEquals(actUserName,expUserName, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS,ExtentLogMessage.TITLE_MATCH_WITH_ACTUAL_TITLE);
    }
    @Test(priority = 1,enabled = true,description = "TC007_Verify date is displayed in home page",groups = {"Sanity"})
    public void TC007_verifyDateIsDisplayed(){
        extentTest.get().assignCategory("Sanity");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String userName = loginData.get(1).get(1);
        String userPassword = loginData.get(1).get(2);
        login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterUserPassword(userPassword);
        home = login.clickLoginButton();
        String actualDate = home.getDate();
        String expDate = DateUtility.getSystemDate();
        extentTest.get().log(Status.PASS, ExtentLogMessage.DATE_DISPLAYED_IN_HOME_PAGE);
        Assert.assertEquals(actualDate, expDate, ErrorMessages.INVALID_DATE_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.HOME_DATE_VALIDATION_MESSAGE);
    }
}
