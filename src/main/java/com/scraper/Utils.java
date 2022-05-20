package com.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    public static String getPersonName(WebDriver driver){
        return driver.findElement(By.xpath("")).getText();
    }

    public static String getPersonName(WebElement table){
        return table.findElement(By.xpath("")).getText();
    }

    public static String getCharge(Dynasty period){
        return "";
    }
}
