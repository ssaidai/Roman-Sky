package com.scraper;

import com.data.Person;
import com.shapesecurity.salvation2.Values.Hash;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Person getInfo(WebDriver driver, String href, Set<Person> entityList){
        Person person = getFrom(entityList, href);
        if(person != null){
            return person;
        }
        System.out.println(href);
        try{
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            String title = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_divisione\"]/th")).getText();
/*          String bornDate = null;
            try{
                 bornDate = getHeaderContent("Nascita", synopticTable).getText();
            }
            catch (NullPointerException ignored){}*/
            Set<String> parents = getRelatives(synopticTable, "Genitori");
            Set<String> married = getRelatives(synopticTable, "Coniuge");
            HashMap<String, Boolean> children = getChildren(synopticTable);
            person = new Person(name, href, parents, married, children, true);
            entityList.add(person);
            return person;
        }catch (NoSuchElementException e){
            person = new Person(driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText(), href, null, null, null, false);
            entityList.add(person);
            return person;
        }
    }

    private static WebElement getHeaderContent(String header, WebElement table){
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));
        for(WebElement row : rows){
            try{
                if(row.findElement(By.tagName("th")).getText().equals(header)){
                    return row.findElement(By.tagName("td"));
                }
            }catch (NoSuchElementException ignored){}
        }
        return null;
    }
    private static Person getFrom(final Set<Person> list, final String href){
        return list.stream().filter(o -> o.getHref().equals(href)).findFirst().orElse(null);
    }

    private static boolean isInBrackets(final List<String> list, final String str){
        return list.stream().anyMatch(e -> e.contains(str));
    }

    // TODO: DA FINIRE
    private static HashMap<String, Boolean> getChildren(WebElement table){
        HashMap<String, Boolean> result = new HashMap<>();
        WebElement childrenHeader = getHeaderContent("Figli", table);
        if(childrenHeader != null){

        }

        return result;
    }

    // TODO: DA AGGIUNGERE 2 IF (SE TYPE == CONIUGE PRENDERE ANCHE I CONSTORTI), (SE TYPE == GENITORI, PRENDERE PADRE E MADRE ED EVENTUALI GENITORI ADOTTIVI)
    private static Set<String> getRelatives(WebElement table, String type){
        WebElement relativeHeader = getHeaderContent(type, table);
        if (relativeHeader == null) return null;

        Set<String> results = new HashSet<>();

        List<String> hrefsInBrackets = new ArrayList<>();
        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher = regex.matcher(relativeHeader.getText());

        while (regexMatcher.find()) {
            hrefsInBrackets.add(regexMatcher.group(1));
        }

        for(WebElement relative : relativeHeader.findElements(By.tagName("a"))){
            String href = relative.getAttribute("href");
            if(!href.matches(".*\\d.*") && !isInBrackets(hrefsInBrackets, relative.getText())){
                results.add(href);
            }
        }
        return results;
    }
}