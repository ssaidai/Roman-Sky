import com.scraper.Scraper;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        final String url = "https://it.wikipedia.org/wiki/Massimiano";
        final String xPath = "//table[@class=\"wikitable\"][@style=\"text-align:center\"]";
        final String namexPath = "tbody/tr/td[2]/b/a";
        final List<WebElement> tables;
        final WebDriver driver = new HtmlUnitDriver();


        Scraper scraper = new Scraper();

        String xxxxPath = "//table[@class=\"sinottico\"]/tbody/tr";
        driver.get(url);
        int size = driver.findElements(By.xpath(xxxxPath)).size();
        for(int counter = 1; counter < driver.findElements(By.xpath(xxxxPath)).size()+1; counter++){
            String zPath = xxxxPath + "[" + counter + "]";
            String test = driver.findElement(By.xpath(zPath)).getText();
            if(driver.findElement(By.xpath(zPath)).getText().startsWith("Figli")){
                String sons = driver.findElement(By.xpath(zPath)).getText().replaceFirst("Figli", "");
                String[] sonsArray = sons.split(" ");
                for(String son : sonsArray){
                    System.out.println(son);
                }
                break;
            }
        }


    }

}
