package com.gmail;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class FindStickers {
    @Test
    public void First_Test() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/public_html/en/");
        int duck_sum=0; WebElement link;  List<WebElement>  sticker;
        List<WebElement> duck_crowd=driver.findElements(By.className("product"));
        duck_sum = duck_crowd.size();int check =0;
        System.out.println("Всего "+ duck_sum+ " уток");
     for (WebElement duck : duck_crowd) {
         sticker = duck.findElements(By.xpath(".//div[starts-with(@class,'sticker')]"));
         if (sticker.size() >1 && sticker.size() <1)
         {System.out.println("У утки проблемы");check++;}
         else System.out.println("Утка имеет один стикер!");
     }
     if (check == 0) System.out.println("У всех уток ровно один стикер!");
        driver.quit();

}
}
