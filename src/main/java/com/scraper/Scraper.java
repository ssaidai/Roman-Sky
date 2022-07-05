package com.scraper;

import com.data.Person;
import com.graph.DynastyTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scraper {
    private final List<WebElement> tables;
    private ArrayList<Set<Person>> dinasties = new ArrayList<>();
    private Set<Person> entityList = new HashSet<>();
    private final WebDriver driver = new HtmlUnitDriver();

    public Scraper(JProgressBar progressBar, int depth){
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
                Utils.getInfo(driver, dinasties.get(tableIndex), entityList, progressBar, depth, depth == 10);
                driver.navigate().back();
            }
            progressBar.setValue(progressBar.getValue()+6);
            tableIndex++;
        }
        progressBar.setValue(100);
        progressBar.setString("Tutte le dinastie sono state caricate");
        System.out.println("FINITO");
    }

    public DynastyTree getDinastyTree(int index){
        return new DynastyTree(dinasties.get(index));
    }
}