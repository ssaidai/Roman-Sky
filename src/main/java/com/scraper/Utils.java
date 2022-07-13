package main.java.com.scraper;

import main.java.com.data.Person;
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

/**
 * All necessary methods for the scraping
 */
public class Utils {

    private static final Pattern articleIDPattern = Pattern.compile("\"wgArticleId\":(.*?),");
    private static final Pattern inBracketsPattern = Pattern.compile("\\((.*?)\\)");

    private static HashSet<String> scraperedHrefs = new HashSet<>();

    /**
     * Main recursive method which navigate for every entity all his kins, married's hrefs until when depth = -1.
     *
     * Update progressBar' string every time an entity get scraped.
     *
     * @param driver
     * @param dinasty
     * @param progressBar
     * @param depth
     * @param ignoreDepth
     */
    public static void fetchData(WebDriver driver, Set<Person> dinasty, JProgressBar progressBar, int depth, boolean ignoreDepth){
        if(depth == -1 && !ignoreDepth) return;

        String scriptText = driver.findElement(By.xpath("/html/head/script[1]")).getAttribute("innerHTML");
        Matcher regexMatcher = articleIDPattern.matcher(scriptText);
        regexMatcher.find();
        long articleID = Long.parseLong(regexMatcher.group(1));
        Person person = getFrom(dinasty, articleID);

        if(person != null){
            return;
        }

        try{
            WebElement synopticTable = driver.findElement(By.xpath("//table[@class=\"sinottico\"]"));
            String name = synopticTable.findElement(By.xpath("tbody/tr[@class=\"sinottico_testata\"]/th")).getText();
            progressBar.setString(name);
            HashMap<String, Boolean> parents = getKins(synopticTable, "Padre");
            Set<String> married = getRelatives(synopticTable, "Coniug");
            HashMap<String, Boolean> children = getKins(synopticTable, "Figli");
            person = new Person(name, driver.getCurrentUrl(), articleID, parents, married, children, true);
            dinasty.add(person);
            scraperedHrefs.add(driver.getCurrentUrl());
            for(String link: Iterables.concat(parents.keySet(), married, children.keySet())){
                if(scraperedHrefs.contains(link)) continue;
                driver.get(link);
                if(isDisambiguityPage(driver)){
                    driver.navigate().back();
                    continue;
                }
                fetchData(driver, dinasty, progressBar, depth-1, ignoreDepth);
                driver.navigate().back();
            }
        }catch (NoSuchElementException e){
            String name = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[1]/b")).getText();
            progressBar.setString(name);
            person = new Person(name, driver.getCurrentUrl(), articleID, null, null, null, false);
            dinasty.add(person);
            scraperedHrefs.add(driver.getCurrentUrl());
        }
        System.out.println(driver.getCurrentUrl());
        progressBar.setValue(progressBar.getValue()+1);
    }

    /**
     * Verify if a page is disambiguated then return true else false.
     *
     * @param driver
     * @return
     */
    private static boolean isDisambiguityPage(WebDriver driver){
        try{
            driver.findElement(By.xpath("//table[@class=\"avviso-disambigua\"]"));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Verify if a page exist then return true else false
     *
     * @param element
     * @return
     */
    private static boolean pageExists(WebElement element){
        return !element.getAttribute("title").contains("la pagina non esiste");
    }

    /**
     * Get the header content from the input table
     *
     * @param header
     * @param table
     * @return
     */
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

    /**
     * Return the Person from the list if the articleID is found else null
     *
     * @param list
     * @param articleID
     * @return
     */
    private static Person getFrom(final Set<Person> list, final long articleID){
        return list.stream().filter(o -> o.getArticleID() == articleID).findFirst().orElse(null);
    }


    /**
     * Verify if a string is in the given list
     *
     * @param list
     * @param str
     * @return
     */
    private static boolean isInList(final List<String> list, final String str){
        return list.stream().anyMatch(e -> e.contains(str));
    }

    /**
     * Get a HashMap of Kins' hrefs and adopted status (boolean) using getHeaderContent
     *
     * @param table
     * @param kinType
     * @return
     */
    private static HashMap<String, Boolean> getKins(WebElement table, String kinType){
        HashMap<String, Boolean> results = new HashMap<>();
        WebElement childrenHeader = getHeaderContent(kinType, table);
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
        if(kinType.equals("Padre")){
            HashMap<String, Boolean> temp = getKins(table, "Madre");
            results.putAll(temp);
        }
        return results;
    }

    /**
     * Get a Set of married entities' hrefs using getHeaderContent
     *
     * @param table
     * @param type
     * @return
     */
    private static Set<String> getRelatives(WebElement table, String type){
        WebElement relativeHeader = getHeaderContent(type, table);

        Set<String> results = new HashSet<>();

        if (relativeHeader == null && type.equals("Coniug")){
            return getRelatives(table, "Consort");
        }
        else if(relativeHeader == null) return results;

        List<String> hrefsInBrackets = new ArrayList<>();
        String relativesText = relativeHeader.getText();

        Matcher regexMatcher = inBracketsPattern.matcher(relativesText);

        while (regexMatcher.find()) {
            hrefsInBrackets.add(regexMatcher.group(1));
        }

        for(WebElement relative : relativeHeader.findElements(By.tagName("a"))){
            if(pageExists(relative)){
                String href = relative.getAttribute("href");
                if(!href.matches(".*\\d.*") && !isInList(hrefsInBrackets, relative.getText())){
                    results.add(href);
                }
            }
        }
        return results;
    }
}