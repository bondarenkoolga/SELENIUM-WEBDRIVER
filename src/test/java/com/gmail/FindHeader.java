package com.gmail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class FindHeader {
    @Test
    public void First_Test() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        int linksCount =  driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        int docsCount;
        String pageName;
        WebElement row, link;
        for (int i=1; i<=linksCount; i++)  {
            link = refreshPage(driver, i);
            pageName = link.findElement(By.className("name")).getText();
            link.click();
           link = refreshPage(driver, i);
           docsCount = link.findElements(By.xpath("./ul[@class='docs']/li[@id]")).size();
            if (docsCount > 0) {
                for (int j=1; j<=docsCount; j++) {
                    link = refreshPage(driver, i);
                    row = link.findElement(By.xpath("./ul[@class='docs']/li[@id][" + j + "]"));
                    pageName = row.findElement(By.className("name")).getText();
                    row.click();
                    checkHeader(driver, pageName);

                }
            }
            else {
             checkHeader(driver, pageName);
           }

        }
    }

    private WebElement refreshPage(WebDriver wd, int i){
        WebElement row = wd.findElement(By.id("box-apps-menu"));
        WebElement link = row.findElement(By.xpath("./li[@id='app-'][" + i + "]"));
        return link;
    }

    private void checkHeader(WebDriver d, String pageName){
        String h1;
        String res = "Страница:  " + pageName;
        if ( isElementPresent(d, By.tagName("h1")) ) {
            h1 = d.findElement(By.tagName("h1")).getText();
            res += " заголовок - " + h1 + ".";
        }
        else
            res += " не имеет заголовка";

        System.out.println(res);
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}