package com.scraper;

import com.data.Person;
import com.data.Vip;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static Vip getInfo(WebDriver driver, String href){
        try{
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            String title = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_divisione\"]/th")).getText();
            String bornDate = getHeaderContent("Nascita", synopticTable).getText();     // TODO:    CHECK FOR NULLPOINTEREXCEPTION
            Vip entity = new Vip(name, title, bornDate, href);
            for(Person son : getChildren(driver, synopticTable)){
                entity.addChild(son, false);
            }

            return entity;
        }catch (NoSuchElementException e){
            return new Vip(driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText(), null, null, href);
        }
    }

    private static WebElement getHeaderContent(String header, WebElement table){
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));
        for(WebElement row : rows){
            try{
                if(row.findElement(By.tagName("th")).getText().equals(header)){
                    return row.findElement(By.tagName("td"));
                }
            }catch (NoSuchElementException e){}
        }
        return null;    // TODO:    CHANGE THIS RETURN TO SOMETHING ELSE SO WE DON'T HAVE TO HANDLE NULL POINTERS
    }

    private static List<Person> getChildren(WebDriver driver, WebElement table){
        WebElement childrenHeader = getHeaderContent("Figli", table);
        List<Person> result = new ArrayList<>();

        for(WebElement son : childrenHeader.findElements(By.tagName("a"))){
            String href = son.getAttribute("href");     //  FIXME:      ERROR HERE  -   IDK WTF IS WRONG, TRY TO DEBUG
            driver.get(href);
            Person pSon = getInfo(driver, href);
            result.add(pSon);
        }
        return result;
    }

}
