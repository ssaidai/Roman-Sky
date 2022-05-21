package com.scraper;

import com.data.Vip;
import com.data.Person;
import com.graph.DynastyTree;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

public class Scraper {
    private ArrayList<Person> emperors;
    private final String url = "https://it.wikipedia.org/wiki/Imperatori_romani";
    private final String xPath = "//table[@class=\"wikitable\"][@style=\"text-align:center\"]";
    private final String namexPath = "tbody/tr/td[2]/b/a";
    private final String categoryxPath = "tbody/tr";
    private final String deathCausexPath = "tbody/tr/td[6]/small";
    private final String infoxPath = "tbody/tr/td[7]";
    private final String startChargePath = "tbody/tr/td[4]";
    private final String endChargePath = "tbody/tr/td[5]";

    private List<Person> entityList;

    private final List<WebElement> tables;
    private final WebDriver driver = new HtmlUnitDriver();

    // Scraper constructor
    public Scraper(){
        driver.get(url);

        tables = driver.findElements(By.xpath(xPath));

        for(WebElement table : tables)
            for(WebElement name : table.findElements(By.xpath(namexPath))){
                String href = name.getAttribute("href");
                driver.get(href);
                Person temp = Utils.getInfo(driver, href);
                entityList.add(temp);
                entityList.addAll(temp.getChildren().keySet());
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