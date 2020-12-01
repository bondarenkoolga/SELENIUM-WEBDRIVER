package com.gmail;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class CheckLog extends TestBase{

    @Test
    public void First_Test() throws InterruptedException {
        System.out.println("Product page errors test started.");
        driver.get("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        // save product names in a list
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for (int i = 0; i < products.size(); i += 2) {
            productNames.add(products.get(i).getText());
        }
        for (String productName : productNames) {
            driver.findElement(By.linkText(productName)).click();
            driver.navigate().back();
        }
        driver.manage().logs().get("browser").forEach(l -> {
            System.out.println(l);
            Assert.assertTrue(l.equals(""));
        });
        System.out.println("Product page errors test ended.");
    }
}

