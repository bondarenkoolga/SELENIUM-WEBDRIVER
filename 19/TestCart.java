package com.gmail.page;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
public class TestCart extends TestBase {
    public String nt = "";

    @Test
    public void cartTest() throws InterruptedException {

        app.getMainPageHelper().gotoMainPage();
        int toPrice = 3; //Сколько уникальных товаров добавить в корзину
        System.out.println("Выбранное колличество добавляемых товаров в корзину: "+toPrice);
        List<String> namesList = new ArrayList<String>();

        do {

            app.getCardPageHelper().addToCardFromPopular(toPrice, namesList);
            app.getCardPageHelper().addToCardFromCampaignns(toPrice, namesList);
            app.getCardPageHelper().addToCardFromLatest(toPrice, namesList);

        } while (namesList.size() < toPrice);

        //sleep(1000);
        Assert.assertEquals(getApp().driver.findElement(By.xpath("//span[@class='quantity']")).getText(),String.valueOf(toPrice));
        app.getCardPageHelper().goToCardPage();

        for (int i = 0; i < namesList.size(); i++) {
            app.getCardPageHelper().deleteOneCardItem();

        }

        app.getMainPageHelper().backMainPage();
        Assert.assertEquals(getApp().driver.findElement(By.xpath("//span[@class='quantity']")).getText(),"0");


    }

}
