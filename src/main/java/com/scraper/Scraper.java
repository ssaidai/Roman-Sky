package com.scraper;

import com.data.Vip;
import com.data.Person;
import com.graph.DynastyTree;
import org.jetbrains.annotations.NotNull;
import org.jgrapht.Graph;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scraper {
    private final String url = "https://it.wikipedia.org/wiki/Imperatori_romani";
    private final String xPath = "//table[@class=\"wikitable\"][@style=\"text-align:center\"]";
    private final String namexPath = "tbody/tr/td[2]/b/a";

    private Set<Person> entityList = new HashSet<>();

    private final List<WebElement> tables;
    private final WebDriver driver = new HtmlUnitDriver();


    // Scraper constructor
    public Scraper(){
        driver.get(url);
        tables = driver.findElements(By.xpath(xPath));

        for(WebElement table : tables){
            for(WebElement name : table.findElements(By.xpath(namexPath))){
                String href = name.getAttribute("href");
                driver.get(href);
                Utils.getInfo(driver, href, entityList);
                driver.navigate().back();
            }
        }

        DynastyTree graph = new DynastyTree(entityList);
        System.out.println(graph);
    }


    //  Test method to print table names
    public void printNames(@NotNull WebElement table){
        List<WebElement> names = table.findElements(By.xpath(namexPath));
        int c = 1;
        for(WebElement name: names){
            System.out.print("|-- " + name.getAttribute("href") + "\n");
            for(int i = 0; i < c; i++){
                System.out.print("\t");
            }
            c++;
        }
        System.out.println("\n---------------------------------------");
    }

}