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
                name.click();
                WebElement synopticTable =
                        driver.findElement(
                                By.xpath("//table[@class=\"sinottico\"]")
                        );      //  Check if this is empty
                                //  If so it means there is not a synoptic table and we need to create an instance of Person instead of Emperor
                if(synopticTable.getText().equals("")){
                    String pName = Utils.getPersonName(driver);
                    LocalDate bornDate = null;     //  Da valutare effettiva utilità della data di nascita
                    entityList.add(new Person(pName, bornDate, name.getAttribute("href")));
                }
                else{
                    String pName = Utils.getPersonName(synopticTable);
                    LocalDate bornDate = null;
                    entityList.add(new Emperor(pName, bornDate, name.getAttribute("href")));
                    // if its emperor check for sons in the synoptic table

                    }
                    WebElement sonList = bla bla bla;
                    for(WebElement son : sonList){
                        //  click and get info
                    }
                }
            }
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