package com.gmail.page;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;


public class ApplicationMenager {
    public WebDriver driver;
    public WebDriverWait wait;
    public CardPageHelper cardPageHelper;
    public AdminPageHelper adminPageHelper;
    public MainPageHelper mainPageHelper;

    public void init() throws InterruptedException {
     //   driver = new ChromeDriver();
        driver = new FirefoxDriver();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        mainPageHelper = new MainPageHelper(driver, wait);
        adminPageHelper = new AdminPageHelper(driver, wait);
        cardPageHelper = new CardPageHelper(driver, wait);
    }
    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public MainPageHelper getMainPageHelper() {
        return mainPageHelper;
    }

    public AdminPageHelper getAdminPageHelper() {
        return adminPageHelper;
    }

    public CardPageHelper getCardPageHelper() {
        return cardPageHelper;
    }
    public void stop() {
        driver.quit();
        driver = null;
    }
}
