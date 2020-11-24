package com.gmail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckLink {
    @Test
    public void First_Test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        WebDriverWait wait = new WebDriverWait(driver, 20/*seconds*/);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name='countries_form']"))));
        driver.findElement(By.cssSelector("[title='Edit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(), 'Edit Country')]"))));

        String existingWindow = driver.getWindowHandle();
        System.out.println(existingWindow);

        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        int numberOflinks = links.size();
        for(int i = 0; i < numberOflinks; i++){
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = driver.getWindowHandles();
            windows.remove(existingWindow);
            String newWindow = windows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(existingWindow);
        }
    }
       // choiseFromListAndClick(By.cssSelector("tr.row td[style]"), driver);
      //  clickAndOpenAllWindows(By.cssSelector("i.fa-external-link"), driver, wait);
       // driver.quit();

    private void choiseFromListAndClick(By locator, WebDriver driver){

    }
    private void clickAndOpenAllWindows (By locator, WebDriver driver, WebDriverWait wait) {
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> list = driver.findElements(locator);
        for (WebElement we:list) {
            we.click();
            openAndCloseNewWindow(oldWindows, driver, wait);
            driver.switchTo().window(mainWindow);
        }
    }

    private void openAndCloseNewWindow(Set<String> oldWindows, WebDriver driver, WebDriverWait wait ) {
        implicitlyWaitOff(driver);
        String newWindow = wait.until(anyWindowOtherThan(oldWindows));
        driver.switchTo().window(newWindow);
        String title = driver.getTitle();
        if (title.contains("Wikipedia"))
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        else
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#acct_btn")));
        implicitlyWaitOn(driver);
        driver.close();
    }
    private void implicitlyWaitOn(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }



    public ExpectedCondition<String> anyWindowOtherThan (final Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver d) {
                Set<String> handles = d.getWindowHandles();
                handles.removeAll(oldWindows);
                return  handles.size()>0 ? handles.iterator().next() : null;
            }
        };
    }
}
