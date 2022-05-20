package com.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    public static HashMap<String, String> getInfo(Dynasty period){ // regardless hashMap or Arraylist
        HashMap<String, String> map = new HashMap<>();             // we may need two getInfo one for "String info",
        map.put("Charge", getCharge(period));                      // the other one for "Array info" like childs?

        return map;
    }



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
