package com.gmail;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class LoginTest {
    @Test
    public void First_Test(){
        ChromeDriver driver= new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.quit();
    }
}
