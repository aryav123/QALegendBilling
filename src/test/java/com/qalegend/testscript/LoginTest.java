package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.dataprovider.DataProviders;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1, description = "TC001_Verify Login page title",groups = {"Regression"})
    public void TC001_verifyLoginPageTitle001() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expHomePageTitle = data.get(1).get(0);
        login = new LoginPage(driver);
        String actualHomePageTitle = login.getLoginPageTitle();
        extentTest.get().log(Status.PASS, ExtentLogMessage.LOGIN_PAGE_RECEIVED);
        Assert.assertEquals(actualHomePageTitle, expHomePageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.TITLE_MATCH_WITH_ACTUAL_TITLE);
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
        extentTest.get().log(Status.PASS, ExtentLogMessage.SIGNIN_SUCCESS_MESSAGE);
    }

    @Test(priority = 1, description = "TC003_Verify invalid login",dataProvider = "InvalidUserCredentials", dataProviderClass = DataProviders.class, groups = {"Regression"})
    public void TC003_verifyInValidLoginErrorMessage(String username,String password) {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        login = new LoginPage(driver);
        login.enterUserName(username);
        login.enterUserPassword(password);
        login.loginButtonClick();
        String expectedErrorMessage = data.get(1).get(4);
        String actualErrorMessage = login.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, ErrorMessages.INVALID_ERROR_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.LOGIN_FAILED_MESSAGE);
    }

    @Test(priority = 1, description = "TC004_Verify click on remember me check box",groups = {"Regression"})
    public void TC004_verifyClickOnRememberMeCheckBox() {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        login.clickOnRememberMeButton();
        Boolean status=login.checkRememberMeCheckBoxSelected();
        Assert.assertTrue(status,ErrorMessages.CHECKBOX_NOT_SELECTED);
        extentTest.get().log(Status.PASS, ExtentLogMessage.CHECK_BOX_CLICKED_SUCCESSFULLY);
    }
}
