package com.gmail;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;


public class TimeZone {


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
        String searchPage = "Geo Zones";
        searchAndClick(searchPage, driver);
        checkGeo_Zones(driver);
        System.out.println("Зоны расположены в алфавитном порядке.");
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private void searchAndClick(String item,WebDriver driver ) {
        String locator="", currentPage = "";
        int i = 0;
        while( !currentPage.equals(item) ) {
            i++;
            locator = "ul#box-apps-menu li#app-:nth-child(" + i + ")";
            if (isElementPresent(driver, By.cssSelector(locator)) )
                currentPage = driver.findElement(By.cssSelector(locator)).getText();
        }
        driver.findElement(By.cssSelector(locator)).click();
    }
    private void checkGeo_Zones(WebDriver driver) {
        String name, zone;
        String root = "td#content table.dataTable tbody";
        String table = ".//tr/td[3]//option[@selected='selected']";
        int count = driver.findElements(By.cssSelector("td#content table.dataTable tr.row")).size();
        for (int i=1; i<=count; i++) {
            zone = ".//tr[@class='row'][" + i + "]/td[3]/a";
            name = currentWE(root, zone, driver).getAttribute("textContent");
            currentWE(root, zone, driver).click();
            checkSortInList(root, table, name, true, true, driver);
            driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
            driver.findElement(By.cssSelector(root));
        }
    }
    private WebElement currentWE(String rootLoc, String element, WebDriver driver) {
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        return root.findElement(By.xpath(element));
    }
    private void checkSortInList(String rootLoc, String tableLoc, String name, boolean first, boolean last, WebDriver driver) {
        List<WebElement> listWE = fillListWE(rootLoc, tableLoc, driver);
        List<String> list = fillList(listWE, first, last);
        }

    private List<WebElement> fillListWE(String rootLoc, String tableLoc,WebDriver driver){
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        List<WebElement> listWE = root.findElements(By.xpath(tableLoc));
        return listWE;
    }
    private List<String> fillList(List<WebElement> listWE, boolean first, boolean last) {
        List<String> resultList = new ArrayList<String>();
        for (WebElement we:listWE)
            resultList.add(we.getAttribute("textContent"));
        if (!first) resultList.remove(0);
        if (!last) resultList.remove(resultList.size()-1);
        return resultList;
    }

}
