package com.qalegend.utilities;
import org.openqa.selenium.WebDriver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
    public static String getSystemDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDateWithTime = new Date();
        String systemDate = dateFormat.format(currentDateWithTime);
        return systemDate;
    }
}