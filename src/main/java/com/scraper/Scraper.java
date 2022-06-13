package com.scraper;

import com.data.Person;
import com.graph.DynastyTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scraper {
    private final List<WebElement> tables;
    private ArrayList<Set<Person>> dinasties = new ArrayList<>();
    private final WebDriver driver = new HtmlUnitDriver();

    public Scraper(){
        driver.get("https://it.wikipedia.org/wiki/Imperatori_romani");
        tables = driver.findElements(By.xpath("//table[@class=\"wikitable\"][@style=\"text-align:center\"]"));

        int tableIndex = 0;
        for(WebElement table : tables){
            dinasties.add(new HashSet<>());
            for(WebElement name : table.findElements(By.xpath("tbody/tr/td[2]/b/a"))){
                String href = name.getAttribute("href");
                driver.get(href);
                Utils.getInfo(driver, href, dinasties.get(tableIndex));
                driver.navigate().back();
            }
            break;
            //tableIndex++;
        }
    }

    public DynastyTree getDinastyTree(int index){
        return new DynastyTree(dinasties.get(index));
    }
}