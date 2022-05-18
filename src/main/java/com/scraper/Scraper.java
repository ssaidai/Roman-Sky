package com.scraper;

import com.data.Emperor;
import com.data.Person;
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
    private final String namexPath = "tbody/tr/td[2]/b/a";
    private final String xPath = "//table[@class=\"wikitable\"][@style=\"text-align:center\"]";

    private Emperor dynastyProgenitor;
    private final List<WebElement> tables;
    private final WebDriver driver = new HtmlUnitDriver();

    public Scraper(){
        driver.get(url);

        tables = driver.findElements(By.xpath(xPath));

        for(WebElement table : tables)
            printNames(table);
    }

    //  Test method to print main names
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


    private void initializeProgenitor(Dynasty period){  // Method to initialize the Dynasty's first Emperor
        //  PRENDERE PRIMO IMPERATORE DALLA TABELLA DELLA DINASTIA RICEVUTA IN INPUT

        //  ANDARE ALL'HREF DI ESSO E CREARE UN'ISTANZA DELLA CLASSE EMPEROR CON I DATI

        //  ASSEGNARE A this.dynastyProgenitor LA CLASSE APPENA CREATA
    }
    private void buildTree(String href, int recursiveLevel){
        if(recursiveLevel > 2){
            driver.get(href);

            for(Person child : this.dynastyProgenitor.getChildren()){
                // per ogni figlio ripetere il metodo in modo ricorsivo (fino a level == 2)


            }

        }
    }

    public void fetchEmperors(@NotNull Dynasty period){
        List<WebElement> names = tables.get(period.getValue()).findElements(By.xpath(xPath));
        //System.out.println(names);
    }
}