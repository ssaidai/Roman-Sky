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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Vip getInfo(WebDriver driver, String href, Set<Person> entitylist){
        Person p = containsHref(entitylist, href);
        if(p != null){
            return (Vip) p;
        }
        System.out.println(href);
        try{
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            String title = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_divisione\"]/th")).getText();
            String bornDate = null;
            try{
                 bornDate = getHeaderContent("Nascita", synopticTable).getText();
            }
            catch (NullPointerException ignored){}
            Vip entity = new Vip(name, title, bornDate, href);
            addRelatives(driver, synopticTable, entity, entitylist);
            entitylist.add(entity);
            return entity;
        }catch (NoSuchElementException e){
            Vip temp = new Vip(driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText(),null, null, href);
            entitylist.add(temp);
            return temp;
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
    private static Person containsHref(final Set<Person> list, final String href){
        return list.stream().filter(o -> o.getHref().equals(href)).findFirst().orElse(null);
    }

    private static boolean listContainsTitle(final List<String> list, final String str){
        return list.stream().anyMatch(e -> e.contains(str));
    }

    private static void addRelatives(WebDriver driver, WebElement table, Person entity, Set<Person> entitylist){
        WebElement relativeHeader = getHeaderContent("Coniuge", table);
        if(relativeHeader != null)
            for(WebElement relative : relativeHeader.findElements(By.tagName("a"))){
                String href = relative.getAttribute("href");
                if(!href.matches(".*\\d.*")){
                    driver.get(href);
                    Person person = getInfo(driver, href, entitylist);
                    entity.addMarried(person);
                    driver.navigate().back();
                }
            }
        relativeHeader = getHeaderContent("Figli", table);

        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\((.*?)\\)");

        if(relativeHeader != null){
            Matcher regexMatcher = regex.matcher(relativeHeader.getText());

            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group(1));
            }

            for(WebElement relative : relativeHeader.findElements(By.tagName("a"))){
                String href = relative.getAttribute("href");
                if(!href.matches(".*\\d.*") && !listContainsTitle(matchList, relative.getText())){
                    driver.get(href);
                    Person person = getInfo(driver, href, entitylist);
                    entity.addChild(person, false);
                    driver.navigate().back();
                }
            }
        }
    }

}