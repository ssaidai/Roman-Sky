package com.scraper;

import com.data.Person;
import com.graph.DynastyTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.swing.*;
import java.util.*;

public class Scraper {
    private final List<WebElement> tables;
    private ArrayList<Set<Person>> dinasties = new ArrayList<>();
    private final WebDriver driver = new HtmlUnitDriver();

    private HashMap<Integer, Integer> maxEntities = new HashMap<>(){{
        put(1, 226);
        put(2, 288);
        put(3, 305);
        put(4, 323);
        put(5, 321);
        put(6, 334);
        put(7, 351);
        put(8, 346);
        put(9, 364);
        put(10, 500);
    }};

    public Scraper(JProgressBar progressBar, int depth){
        progressBar.setMaximum(maxEntities.get(depth));

        driver.get("https://it.wikipedia.org/wiki/Imperatori_romani");
        tables = driver.findElements(By.xpath("//table[@class=\"wikitable\"][@style=\"text-align:center\"]"));

        tables.remove(0);
        int tableIndex = 0;
        for(WebElement table : tables){
            dinasties.add(new HashSet<>());
            for(WebElement name : table.findElements(By.xpath("tbody/tr/td[2]/b/a"))){
                String href = name.getAttribute("href");
                System.out.println(name.getText());
                driver.get(href);
                Utils.fetchData(driver, dinasties.get(tableIndex), progressBar, depth, depth == 10);
                driver.navigate().back();
            }
            if(table.equals(driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[16]")))) continue;
            tableIndex++;
        }
        System.out.println(Utils.contatore);
        progressBar.setString("Tutte le dinastie sono state caricate");
    }

    public DynastyTree getDynastyTree(int index){
        return new DynastyTree(dinasties.get(index));
    }
}