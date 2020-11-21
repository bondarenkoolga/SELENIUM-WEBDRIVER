package com.gmail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
;

public class CheckUser {
    @Test
    public void First_Test() throws InterruptedException {
         WebDriver driver = new ChromeDriver();
         driver.get("http://localhost/litecart/public_html/en/");
          String email = "bondarenkoolga363@gmail.com";
        String password = "1234567";
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("tax_id")).sendKeys("1");
        driver.findElement(By.name("company")).sendKeys("Raiffeisen");
        driver.findElement(By.name("firstname")).sendKeys("Olga");
        driver.findElement(By.name("lastname")).sendKeys("Bondarenko");
        driver.findElement(By.name("address1")).sendKeys("70 oktyabrya");
        driver.findElement(By.name("address2")).sendKeys("-");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("Alabama");
        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");
        Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        zone.selectByVisibleText("Alabama");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+18452245869");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
        //выход из аккаунта
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
        Thread.sleep(1000);
        login(email,password, driver);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

        private void login(String email, String password, WebDriver driver) {
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("login")).click();
        }

}
