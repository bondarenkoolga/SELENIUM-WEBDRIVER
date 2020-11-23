package com.gmail;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class CheckDelete {
    @Test


    public void First_Test() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20/*seconds*/);
        driver.get("http://localhost/litecart/public_html/en/");
        for (int i = 1; i<=3; i++) {
            addItem(driver, wait);
            driver.findElement(By.id("logotype-wrapper")).click();
        }
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        clearCart(driver, wait);
        Thread.sleep(2000);
        }
    private void addItem(WebDriver driver, WebDriverWait wait) {
        WebElement quantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        By locator = By.cssSelector("div#cart span.quantity");
        String itemCount = quantity.getText();
        Integer next = Integer.parseInt(itemCount) + 1;
        itemCount = next.toString();
        driver.findElement(By.cssSelector("div.content div.name")).click();
       if (!isElementNotPresent(driver, By.cssSelector("td.options")) ) {
            int count = driver.findElements(By.cssSelector("td.options option")).size();
            choiseFromSelect(By.cssSelector("select[name='options[Size]'"), count, false, driver);
        }
        implicitlyWaitOff(driver);
        driver.findElement(By.name("add_cart_product")).click();
         wait.until(ExpectedConditions.textToBe(locator, itemCount));
        quantity = driver.findElement(locator);
        Assert.assertTrue(quantity.getText().equals(itemCount));
        implicitlyWaitOn(driver);
    }

    private void choiseFromSelect(By locator, int size, boolean isFirst, WebDriver driver) {
        Select menu = new Select(driver.findElement(locator));
        int index = (int)(Math.random()*size);
        if (!isFirst && index==0) index++;
        menu.selectByIndex(index);
    }

    private void clearCart(WebDriver driver, WebDriverWait wait){
        int count = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i=count; i>1; i--) {
            driver.findElement(By.cssSelector("li.shortcut")).click();
            driver.findElement(By.name("remove_cart_item")).click();
            implicitlyWaitOff(driver);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.cssSelector("table.dataTable td.item"), i));
            implicitlyWaitOn(driver);
        }
        driver.findElement(By.name("remove_cart_item")).click();
        implicitlyWaitOff(driver);
        wait.until(ExpectedConditions.stalenessOf(
                driver.findElement(By.cssSelector("table.dataTable"))));
        implicitlyWaitOn(driver);
    }

    private void implicitlyWaitOn(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            implicitlyWaitOff(driver);
            return driver.findElements(locator).size() == 0;
        } finally {
            implicitlyWaitOn(driver);
        }
    }
   }

