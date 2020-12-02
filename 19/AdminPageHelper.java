package com.gmail.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AdminPageHelper {
    WebDriver driver;
    WebDriverWait wait;

    public AdminPageHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait=wait;
    }

    public void doLiginByAdmin() {
        driver.navigate().to("http://localhost/litecart/public_html/admin/login.php");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }
}
