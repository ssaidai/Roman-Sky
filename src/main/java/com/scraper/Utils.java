package com.scraper;

import com.data.Person;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


        /*  1)
        * */


public class Utils {

    private static final Pattern articleIDPattern = Pattern.compile("\"wgArticleId\":(.*?),");
    private static final Pattern inBracketsPattern = Pattern.compile("\\((.*?)\\)");

    public static void getInfo(WebDriver driver, Set<Person> dinasty, Set<Person> entityList, JProgressBar progressBar, int depth, boolean ignoreDepth){

        if(depth == -1 && !ignoreDepth) return;

        System.out.println(driver.getCurrentUrl());
        String scriptText = driver.findElement(By.xpath("/html/head/script[1]")).getAttribute("innerHTML");
        Matcher regexMatcher = articleIDPattern.matcher(scriptText);
        regexMatcher.find();
        long articleID = Long.parseLong(regexMatcher.group(1));
        Person person = getFrom(entityList, articleID);

        if(person != null){
            if(!dinasty.contains(person)){
                dinasty.add(person);
                for(String link: Iterables.concat(person.getParentsHrefs(), person.getMarriedHrefs(), person.getChildren().keySet())){
                    driver.get(link);
                    getInfo(driver, dinasty, entityList, progressBar, depth-1, ignoreDepth);
                    driver.navigate().back();
                }
            }
            else
                return;
        }

        try{
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            progressBar.setString(name);
/*            String title = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_divisione\"]/th")).getText();
            String bornDate = null;
            try{
                 bornDate = getHeaderContent("Nascita", synopticTable).getText();
            }
            catch (NullPointerException ignored){}*/
            Set<String> parents = getRelatives(synopticTable, "Padre");
            Set<String> married = getRelatives(synopticTable, "Coniug");
            HashMap<String, Boolean> children = getChildren(synopticTable);
            person = new Person(name, driver.getCurrentUrl(), articleID, parents, married, children, true);
            dinasty.add(person);
            entityList.add(person);

            for(String link: Iterables.concat(parents, married, children.keySet())){
                driver.get(link);
                if(isDisambiguityPage(driver)){
                    driver.navigate().back();
                    break;
                }
                getInfo(driver, dinasty, entityList, progressBar, depth-1, ignoreDepth);
                driver.navigate().back();
            }
        }catch (NoSuchElementException e){
            String name = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText();
            progressBar.setString(name);
            person = new Person(name, driver.getCurrentUrl(), articleID, null, null, null, false);
            dinasty.add(person);
            entityList.add(person);
        }
    }

    private static boolean isDisambiguityPage(WebDriver driver){
        try{
            driver.findElement(By.xpath("//table[@class=\"avviso-disambigua\"]"));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    private static boolean pageExists(WebElement element){
        return !element.getAttribute("title").contains("la pagina non esiste");
    }

    private static WebElement getHeaderContent(String header, WebElement table){
        List<WebElement> rows = table.findElements(By.xpath("tbody/tr"));
        for(WebElement row : rows){
            try{
                if(row.findElement(By.tagName("th")).getText().startsWith(header)){
                    return row.findElement(By.tagName("td"));
                }
            }catch (NoSuchElementException ignored){}
        }
        return null;
    }
    private static Person getFrom(final Set<Person> list, final long articleID){
        return list.stream().filter(o -> o.getArticleID() == articleID).findFirst().orElse(null);
    }

    // FIXME: CAMBIARE NOME A QUESTA ROBA, TROPPO SPECIFICO AL MOMENTO
    private static boolean isInBrackets(final List<String> list, final String str){
        return list.stream().anyMatch(e -> e.contains(str));
    }

    private static HashMap<String, Boolean> getChildren(WebElement table){
        HashMap<String, Boolean> results = new HashMap<>();
        WebElement childrenHeader = getHeaderContent("Figli", table);
        if(childrenHeader == null) return results;

        String[] strings = childrenHeader.getText().split(":");
        if(strings.length == 2){
            for(WebElement relative : childrenHeader.findElements(By.tagName("a"))){
                if(pageExists(relative)){
                    String href = relative.getAttribute("href");
                    if(!href.matches(".*\\d.*") && strings[0].contains(relative.getText())){
                        results.put(href, false);
                    }
                    else if(!href.matches(".*\\d.*") && strings[1].contains(relative.getText())){
                        results.put(href, true);
                    }
                }
            }
        }
        else{
            List<String> wordList = Arrays.asList(StringUtils.split(childrenHeader.getText(), "\n "));
            for(WebElement relative : childrenHeader.findElements(By.tagName("a"))){
                if(pageExists(relative)){
                    String href = relative.getAttribute("href");
                    int index = wordList.indexOf(relative.getText());
                    if(!href.matches(".*\\d.*") && (index < wordList.size()-1 && wordList.get(index+1).equals("(adottivo)")) || index < wordList.size()-2 && wordList.get(index+2).equals("adottato)"))
                        results.put(href, true);
                    else if(!href.matches(".*\\d.*"))
                        results.put(href, false);
                }
            }
        }
        return results;
    }

    private static Set<String> getRelatives(WebElement table, String type){
        WebElement relativeHeader = getHeaderContent(type, table);

        Set<String> results = new HashSet<>();

        if (relativeHeader == null && type.equals("Coniug")){
            return getRelatives(table, "Consort");
        }
        else if(relativeHeader == null) return results;

        List<String> hrefsInBrackets = new ArrayList<>();

        Matcher regexMatcher = inBracketsPattern.matcher(relativeHeader.getText());

        while (regexMatcher.find()) {
            hrefsInBrackets.add(regexMatcher.group(1));
        }

        for(WebElement relative : relativeHeader.findElements(By.tagName("a"))){
            if(pageExists(relative)){
                String href = relative.getAttribute("href");
                if(!href.matches(".*\\d.*") && !isInBrackets(hrefsInBrackets, relative.getText())){
                    results.add(href);
                }
            }
        }
        if(type.equals("Padre")){
            Set<String> temp = getRelatives(table, "Madre");
            results.addAll(temp);
        }
        return results;
    }
}