package com.gmail;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class First {
    @Test
    public void First_Test(){
        //System.setProperty("webdriver.chrome.driver", "C:\\Tools");
        ChromeDriver  driver= new ChromeDriver();
        driver.get("http://www.gmail.com/");
        driver.quit();
    }
}