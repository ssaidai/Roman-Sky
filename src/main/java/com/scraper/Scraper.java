package com.scraper;

import com.data.Emperor;
import com.data.Person;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.scraper.Dynasty.DINASTIA_GIULIO_CLAUDIA;

public class Scraper {
    private ArrayList<Person> emperors;
    private final String url = "https://it.wikipedia.org/wiki/Imperatori_romani";
    private final String xPath = "//table[@class=\"wikitable\"][@style=\"text-align:center\"]";
    private final String namexPath = "tbody/tr/td[2]/b/a";
    private final String deathCausexPath = "tbody/tr/td[6]/small";
    private final String infoxPath = "tbody/tr/td[7]";
    private final String startChargePath = "tbody/tr/td[4]";
    private final String endChargePath = "tbody/tr/td[5]";

    private Emperor dynastyProgenitor;

    private final List<WebElement> tables;
    private final WebDriver driver = new HtmlUnitDriver();

    // Scraper constructor
    public Scraper(){
        driver.get(url);

        tables = driver.findElements(By.xpath(xPath));

        //for(WebElement table : tables)
        //    printNames(table);
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



    //---------------------------------------------Getters----------------------------------------------------------

    public String getPersonName(){
        return driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/p[1]/b[1]")).getText();
    }

    public String getDeathCause(Dynasty period){
        return tables.get(period.getValue()).findElement(By.xpath(deathCausexPath)).getText();
    }

    public String getAdditionalInfo(Dynasty period){
        return tables.get(period.getValue()).findElement(By.xpath(infoxPath)).getText();
    }

    public String getCharge(Dynasty period){
        int periodValue = period.getValue();

        ;
        if(periodValue >= 13){              //13 is the "Riforma tetrarchica"'s periodValue
            if(getPersonName().equals("Diocleziano")){
                return driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/table[1]/tbody/tr[5]/td")).getText();
            }
            return driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/table[1]/tbody/tr[4]/td/ul/li")).getText();
        }
        return driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/table[1]/tbody/tr[5]/td")).getText();
    }

    //---------------------------------------------Persons----------------------------------------------------------





    public static void main(String[] args) {
        Scraper scraper = new Scraper();

        System.out.println("Death Cause: " + scraper.getDeathCause(DINASTIA_GIULIO_CLAUDIA));
        System.out.println("Additional Info: " + scraper.getAdditionalInfo(DINASTIA_GIULIO_CLAUDIA));

        scraper.tables.get(2).findElement(By.xpath("tbody/tr/td[2]/b/a")).click();
        System.out.println("Person Name: " + scraper.getPersonName());
        System.out.println("Charge: " + scraper.getCharge(DINASTIA_GIULIO_CLAUDIA));
    }
    }