package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1, description = "TC001_Verify Login page title",groups = {"Smoke"})
    public void TC001_verifyLoginPageTitle() {
        extentTest.get().assignCategory("Smoke");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expHomePageTitle = data.get(1).get(0);
        login = new LoginPage(driver);
        String actualHomePageTitle = login.getLoginPageTitle();
        extentTest.get().log(Status.PASS, "Login page title received");
        Assert.assertEquals(actualHomePageTitle, expHomePageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, "Expected login page title match with actual login page title");
    }

    @Test(priority = 1, description = "TC002_Verify valid login",groups = {"Regression"})
    public void TC002_verifyValidLogin() {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String userName = data.get(1).get(1);
        login.enterUserName(userName);
        String userPassword = data.get(1).get(2);
        login.enterUserPassword(userPassword);
        home = login.clickLoginButton();
        String actUserName = home.getUserAccountNameText();
        String expUserName = data.get(1).get(3);
        Assert.assertEquals(actUserName, expUserName, ErrorMessages.USERNAME_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, "Expected username match with actual username");
    }

    @Test(priority = 1, description = "TC003_Verify invalid login",groups = {"Smoke"})
    public void TC003_verifyInValidLoginErrorMessage() {
        extentTest.get().assignCategory("Smoke");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String fName = RandomUtility.getfName();
        String password = fName + "@123";
        login = new LoginPage(driver);
        login.enterUserName(fName);
        login.enterUserPassword(password);
        login.clickLoginButton();
        String expectedErrorMessage = data.get(1).get(4);
        String actualErrorMessage = login.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, ErrorMessages.USERNAME_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, "Expected username match with actual username");
    }

    @Test(priority = 1, description = "TC004_Verify click on remember me check box",groups = {"Sanity"})
    public void TC004_verifyClickOnRememberMeCheckBox() {
        extentTest.get().assignCategory("Sanity");
        login = new LoginPage(driver);
        login.clickOnRememberMeButton();
    }
}
