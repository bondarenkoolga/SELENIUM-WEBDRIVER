package com.gmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.junit.Test;

public class ProductCheck {

    @Test
    public void First_Test() {
       WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost/litecart/public_html/en/");
        String [] listMainPage;
        String [] listItemPage;
        String result;
        WebElement item = driver.findElement(By.xpath(".//div[@id='box-campaigns']//li[1]"));
        listMainPage = StartList(item);
        listMainPage[0] = item.findElement(By.cssSelector("div.name")).getText();
        item.click();

        item = driver.findElement(By.cssSelector("div#box-product div.information"));
        listItemPage = StartList(item);
        listItemPage[0] = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();
        if (listMainPage[0].equals(listItemPage[0])) System.out.println("Текст названия товара: "+ listMainPage[0]);
        if (listMainPage[1].equals(listItemPage[1])) System.out.println("Обычная цена товара: "+ listMainPage[1]);
        if (listMainPage[5].equals(listItemPage[5])) System.out.println("Акционная цена товара: "+ listMainPage[5]);

        System.out.println("Цвет обычной цены на главной странице: " + listMainPage[2]);
        System.out.println("Text decoration обычной цены на главной странице: " + listMainPage[3]);
        System.out.println("Цвет акционной цены на главной странице: " + listMainPage[6]);
        System.out.println("Weight акционной цены на главной странице: " + listMainPage[7]);
        System.out.println("Цвет обычной цены на странице продукта: " + listItemPage[2]);
        System.out.println("Text decoration обычной цены на странице продукта: " + listItemPage[3]);
        System.out.println("Цвет акционной цены на странице продукта:  " + listItemPage[6]);
        System.out.println("Weight акционной цены на странице продукта: " + listItemPage[7]);
        if (listMainPage[8].compareTo(listMainPage[4])>0)
        System.out.println("На главной странице акционная цена крупнее, чем обычная. " );
        if (listItemPage[8].compareTo(listItemPage[4])>0)
            System.out.println("На странице товара акционная цена крупнее, чем обычная. " );
    }
    private String[] StartList (WebElement root) {
        String[] list = new String[9];
        list[1] = root.findElement(By.cssSelector("s.regular-price")).getText();
        list[2] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        list[3] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        list[4] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        list[5] = root.findElement(By.cssSelector("strong.campaign-price")).getText();
        list[6] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        list[7] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        list[8] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        return list;
    }



}
