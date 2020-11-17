package com.gmail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.apache.commons.lang3.StringUtils;
import java.util.List;


public class TimeZoneTest {
    @Test
    public void First_Test() {
        int countryColNum = 2;
        int zoneColNum = 2;
        String countryName = "";
        String currentZoneName = "";
        String nextZoneName = "";
         ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for(int i = 0; i < rows.size(); i++) {
            countryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
          // System.out.println("Название страны -  " + countryName);
            driver.findElement(By.linkText(countryName)).click();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            table = driver.findElement(By.tagName("tbody"));
            rows = table.findElements(By.tagName("tr"));
            for (int j = 0; j < rows.size() - 1; j++) {

                currentZoneName = StringUtils.stripAccents(rows.get(j).findElements(By.tagName("td")).get(zoneColNum).getText());
                nextZoneName = StringUtils.stripAccents(rows.get(j + 1).findElements(By.tagName("td")).get(zoneColNum).getText());
               // System.out.println("Название зоны -  " + currentZoneName);
                Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
                if (nextZoneName.compareTo(currentZoneName) > 0)
                    System.out.println("Зоны расположены в алфавитном порядке.");
            }
        }

        }
}
