package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.pages.ResetPage;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ResetTest extends Base {
    ResetPage reset;
    LoginPage login;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,enabled = true,description = "TC005_Verify click on forgot password link",groups = {"Smoke"})
    public void TC005_verifyClickForgotPasswordLink(){
        extentTest.get().assignCategory("Smoke");
        login=new LoginPage(driver);
        reset = login.clickForgotPasswordButton();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("ResetPage");
        String expectedInvalidEmailErrorMessage=data.get(1).get(0);
        String invalidEmail= RandomUtility.getRandomEmail();
        reset.enterInvalidEmail(invalidEmail);
        reset.resetEmailButtonClick();
        String actualInvalidEmailErrorMessage= reset.getInvalidResetEmailText();
        Assert.assertEquals(actualInvalidEmailErrorMessage,expectedInvalidEmailErrorMessage, ErrorMessages.INVALID_EMAIL_ID);
    }
}
