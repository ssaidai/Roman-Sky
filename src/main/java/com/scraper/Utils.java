package com.scraper;

import com.data.Emperor;
import com.data.Person;
import com.data.Vip;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Vip getInfo(WebDriver driver, String href, String emperorHRef) {
        try {
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            String title = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_divisione\"]/th")).getText();
            String bornDate = getHeaderContent("Nascita", synopticTable).getText();     // TODO:    CHECK FOR NULLPOINTEREXCEPTION

            Vip entity = new Vip(name, title, bornDate, href);
            if  (href.equals(emperorHRef)) {                           //TODO: CHECK IF IT'S RIGHT
                entity = new Emperor(name, title, bornDate, href);
            }




            if (getHeaderContent("Figli", synopticTable) != null) {
                for (Person son : getChildren(driver, synopticTable, emperorHRef)) {
                    entity.addChild(son, false);
                }
            }
            return entity;
        }
        catch (NoSuchElementException e) {
            return new Vip(driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText(), null, null, href);
        }
    }


    private static WebElement getHeaderContent(String header, WebElement table) {
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));
        for (WebElement row : rows) {
            try {
                if (row.findElement(By.tagName("th")).getText().equals(header)) {
                    return row.findElement(By.tagName("td"));
                }
            } catch (NoSuchElementException e) {
            }
        }
        return null;
    }


    private static List<Person> getChildren(WebDriver driver, WebElement table, String emperorHRef) {
        WebElement childrenHeader = getHeaderContent("Figli", table);
        List<Person> result = new ArrayList<>();

        for (WebElement son : childrenHeader.findElements(By.tagName("a"))) {
            String href = son.getAttribute("href");
            driver.get(href);
            Person pSon = getInfo(driver, href, emperorHRef);        //  FIXME: need to check if all href is referred to their sons,
            result.add(pSon);                           //  FIXME: some of it is referred to the son's mother
            driver.navigate().back();
        }
        return result;
    }

    private static String getCharge(WebDriver driver, WebElement table){
        try{
            return getHeaderContent("Regno", table).getText();
        }
        catch(Exception e){
            return getHeaderContent("In carica", table).getText();
        }
    }


}
