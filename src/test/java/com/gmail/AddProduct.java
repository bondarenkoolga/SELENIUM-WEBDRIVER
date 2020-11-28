package com.gmail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AddProduct {
    @Test
    public void First_Test() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Catalog")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Add New Product")).click();
        String relativePath = "./src/test/java/com/gmail/utochka.png";
        Path filePath = Paths.get(relativePath);
        String absolutePath = filePath.normalize().toAbsolutePath().toString();
        Thread.sleep(1000);
        driver.findElement(By.name("name[en]")).sendKeys("utochka");
        driver.findElement(By.name("code")).sendKeys("888");
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("quantity")).sendKeys("100" );
        driver.findElement(By.cssSelector("[value='1-3']")).click();
        driver.findElement(By.name("date_valid_from")).click();
        driver.findElement(By.name("date_valid_from")).sendKeys("05.11.2020");
        driver.findElement(By.name("date_valid_to")).click();
        driver.findElement(By.name("date_valid_to")).sendKeys("04.12.2020");
        driver.findElement(By.name("new_images[]")). sendKeys(absolutePath);
        driver.findElement(By.linkText("Information")).click();
        Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
        manufact.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("utochka!");
        driver.findElement(By.name("short_description[en]")).sendKeys("New utochka!");
        driver.findElement(By.name("head_title[en]")).sendKeys("Text about utochka!");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Еще одна уточка!");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Какая-нибудь информация");
        driver.findElement(By.linkText("Prices")).click();
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("purchase_price")).sendKeys("7");
        Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        curr_code.selectByVisibleText("Euros");
        driver.findElement(By.name("prices[USD]")).sendKeys("4");
        driver.findElement(By.name("prices[EUR]")).sendKeys("7");
        driver.findElement(By.cssSelector("button[name=save]")).click();
        Thread.sleep(1000);
        WebElement root = driver.findElement(By.cssSelector("table.dataTable tbody"));
        List<WebElement> list = root.findElements(By.xpath(".//tr/td[3]/a"));
        for (WebElement we : list) {
            String name = we.getText();
            if (name.equals("utochka") ) {
                System.out.println("Элемент добавлен");
                break;
            }
        }
        driver.quit();

    }


}
