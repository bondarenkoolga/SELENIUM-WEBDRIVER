package com.gmail;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CountryTest {
    @Test
    public void First_Test() {
        int countryColNum = 4;
        int zoneColNum = 5;
        String currentCountryName;
        String nextCountryName;
        String currentZoneName;
        String nextZoneName;
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = table.findElements(By.className("row"));
            for(int i = 0; i < rows.size()-1; i++){
            currentCountryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
            nextCountryName = rows.get(i+1).findElements(By.tagName("td")).get(countryColNum).getText();
          //  System.out.println("Название страны -  " + currentCountryName);
           Assert.assertEquals("Страны расположены в алфавитном порядке.",true, StringUtils.stripAccents(nextCountryName).compareTo(StringUtils.stripAccents(currentCountryName)) > 0);
           if (StringUtils.stripAccents(nextCountryName).compareTo(StringUtils.stripAccents(currentCountryName)) > 0)
               System.out.println("Страны расположены в алфавитном порядке.");
            int zonesCount = Integer.parseInt(rows.get(i).findElements(By.tagName("td")).get(zoneColNum).getText());
            if (zonesCount > 1) {
                driver.findElement(By.linkText(currentCountryName)).click();
                table = driver.findElement(By.tagName("tbody"));
                rows = table.findElements(By.tagName("tr"));
                List<WebElement> Zones = table.findElements(By.cssSelector(("[name$='][name]']")));
                for(int j = 0; j < rows.size()-1; j++){

                    currentZoneName = StringUtils.stripAccents((Zones.get(j).getAttribute("value")));
                    nextZoneName  = StringUtils.stripAccents(Zones.get(j+1).getAttribute("value"));
                   // System.out.println("    Название зоны -  " + currentZoneName);
                    Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
                    if (nextZoneName.compareTo(currentZoneName) > 0)
                        System.out.println("Зоны расположены в алфавитном порядке.");
                }
            }
        }
    }
}






