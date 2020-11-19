package com.gmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;
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
         String[] MainRegularColor= listMainPage[2].replaceAll("rgba\\(", "").
                 replaceAll(" ", "").
                 replaceAll("\\)", "").
                 replaceAll("\\s", "").split(",");
         int  iMainRegularR= Integer.parseInt(MainRegularColor[0]);
        int  iMainRegularG= Integer.parseInt(MainRegularColor[1]);
        int  iMainRegularB= Integer.parseInt(MainRegularColor[2]);
         if((iMainRegularR ==iMainRegularG)&&(iMainRegularG ==iMainRegularB))
        System.out.println("Цвет обычной цены на главной странице: серый" );
        System.out.println("Text decoration обычной цены на главной странице: " + listMainPage[3]);
        String[] MainSaleColor= listMainPage[6].replaceAll("rgba\\(", "").
                replaceAll(" ", "").
                replaceAll("\\)", "").
                replaceAll("\\s", "").split(",");
        int  iMainSaleR= Integer.parseInt(MainSaleColor[0]);
        int  iMainSaleG= Integer.parseInt(MainSaleColor[1]);
        int  iMainRSaleB= Integer.parseInt(MainSaleColor[2]);
        if ((iMainSaleG==0)&&(iMainRSaleB==0))
        System.out.println("Цвет акционной цены на главной странице: красный");
        System.out.println("Weight акционной цены на главной странице: " + listMainPage[7]);
        String[] ListRegularColor= listItemPage[2].replaceAll("rgba\\(", "").
                replaceAll(" ", "").
                replaceAll("\\)", "").
                replaceAll("\\s", "").split(",");
        int  iListRegularR= Integer.parseInt(ListRegularColor[0]);
        int  iListRegularG= Integer.parseInt(ListRegularColor[1]);
        int  iListRegularB= Integer.parseInt(ListRegularColor[2]);
        if((iListRegularR ==iListRegularG)&&(iListRegularG ==iListRegularB))
        System.out.println("Цвет обычной цены на странице продукта: серый" );
        System.out.println("Text decoration обычной цены на странице продукта: " + listItemPage[3]);
        String[] ListSaleColor= listItemPage[6].replaceAll("rgba\\(", "").
                replaceAll(" ", "").
                replaceAll("\\)", "").
                replaceAll("\\s", "").split(",");
        int  iListSaleR= Integer.parseInt(ListSaleColor[0]);
        int  iListSaleG= Integer.parseInt(ListSaleColor[1]);
        int  iListRSaleB= Integer.parseInt(ListSaleColor[2]);
        if ((iListSaleG==0)&&(iListRSaleB==0))
        System.out.println("Цвет акционной цены на странице продукта: красный" );
        System.out.println("Weight акционной цены на странице продукта: " + listItemPage[7]);
        String px = "px";
       String listMainPagestrsale  = listMainPage[8].replace(px,"");
        String listMainPagestrregular = listMainPage[4].replace(px,"");
        Double ilistMainPagestrsale = Double. parseDouble(listMainPagestrsale);
        Double ilistMainPagestrregular  = Double. parseDouble(listMainPagestrregular);
        if (ilistMainPagestrsale>ilistMainPagestrregular)
        System.out.println("На главной странице акционная цена крупнее, чем обычная. " );
        String listItemPagestrsale  = listItemPage[8].replace(px,"");
        String listItemPagestrregular = listItemPage[4].replace(px,"");
        Double ilistItemPagestrsale  =Double. parseDouble(listItemPagestrsale);
        Double ilistItemPagestrregular  =Double. parseDouble(listItemPagestrregular);
        if (ilistItemPagestrsale>ilistItemPagestrregular)
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
