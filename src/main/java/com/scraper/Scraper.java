package com.scraper;

import com.data.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

public class Scraper {
    private ArrayList<Person> emperors;
    private final String url = "https://it.wikipedia.org/wiki/Imperatori_romani";
    private final String nameXPath = "//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr/td[2]";
    private final WebDriver driver = new HtmlUnitDriver();

    public Scraper(){

    }

    public void fetchEmperors(){
        driver.get(url);

        List<WebElement> names = driver.findElements(By.xpath(nameXPath));

        for(WebElement name : names){
            System.out.println(name.getText());
        }

        driver.close();
    }
}