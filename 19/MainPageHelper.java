package com.gmail.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainPageHelper {
    WebDriver driver;
    WebDriverWait wait;

    public MainPageHelper(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        this.driver = driver;
        this.wait=wait;

    }

    public void backMainPage() {
        driver.navigate().back();
        wait.until(visibilityOfElementLocated(By.className("content")));
    }

    public void gotoMainPage() {
        driver.navigate().to("http://localhost/litecart/public_html/en/");
        wait.until(visibilityOfElementLocated(By.className("content")));
    }
    public void goTo(String url){
        driver.navigate().to(url);
        wait.until(visibilityOfElementLocated(By.id("content")));

    }
}
