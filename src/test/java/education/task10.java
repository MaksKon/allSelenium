package education;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Verifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class task10 {

    private WebDriver driver;


    @Before
    public void startBrowser (){
        //driver= new ChromeDriver();
        //driver= new InternetExplorerDriver();
        driver= new FirefoxDriver();


        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task9()throws Exception{
        driver.get("http://localhost/litecart");
        //собираем данные с главной
        WebElement campaings=driver.findElement(By.xpath("*//div[@id='box-campaigns']"));
        //название товара
        String mainNameItem= campaings.findElement(By.xpath("*//div[@class='name']")).getText();
        //обычная цена
        String mainRegularPrice= campaings.findElement(By.xpath("*//s[@class='regular-price']")).getText();
        //акционная цена
        String mailnCampaingPrice= campaings.findElement(By.xpath("*//strong[@class='campaign-price']")).getText();


        // проверки на главной
        //обычня цена зачеркнутый
        Assert.assertEquals("s",campaings.findElement(By.xpath("*//s[@class='regular-price']")).getTagName());
        //обычная цена цвет серый
        Integer[] mainRegularColor=colorParser(campaings.findElement(By.xpath("*//s[@class='regular-price']")).getCssValue("color"));
        Assert.assertTrue(mainRegularColor[0]==mainRegularColor[1] | mainRegularColor[1]==mainRegularColor[2]);
        //акционная жирная цена
        Assert.assertEquals("strong",campaings.findElement(By.xpath("*//strong[@class='campaign-price']")).getTagName());

        //цвет акционной цены
        Integer[] campaingColorPrice=colorParser(campaings.findElement(By.xpath("*//strong[@class='campaign-price']")).getCssValue("color"));
        Assert.assertTrue(campaingColorPrice[1]==campaingColorPrice[2] |campaingColorPrice[2]==0);
        //сравнение размеров цены
        Float mainregularPriceSize=Float.valueOf(campaings.findElement(By.xpath("*//s[@class='regular-price']")).getCssValue("font-size").replace("px",""));
        Float maincampaingPriceSize=Float.valueOf(campaings.findElement(By.xpath("*//strong[@class='campaign-price']")).getCssValue("font-size").replace("px",""));
        Assert.assertTrue(maincampaingPriceSize>mainregularPriceSize);

        //переходим на страницу товара
        driver.findElement(By.xpath("*//div[@id='box-campaigns']//a[@class='link']")).click();
        //сравнение имени товара
        Assert.assertEquals(mainNameItem,driver.findElement(By.xpath("*//h1[@itemprop='name']")).getText());
        //сравниваем цену обычную
        Assert.assertEquals(mainRegularPrice, driver.findElement(By.xpath("*//s[@class='regular-price']")).getText());
        //сравниваем цену акционную
        Assert.assertEquals(mailnCampaingPrice, driver.findElement(By.xpath("*//strong[@class='campaign-price']")).getText());
        //цвет цены обычной на странице товара
        Integer[] campaingRegularColor=colorParser(driver.findElement(By.xpath("*//s[@class='regular-price']")).getCssValue("color"));
        Assert.assertTrue(campaingRegularColor[0]==campaingRegularColor[1] | campaingRegularColor[1]==campaingRegularColor[2]);
        //цвет акционной цены на странице товара
        Integer[] campaingCampaingColorPrice=colorParser(driver.findElement(By.xpath("*//strong[@class='campaign-price']")).getCssValue("color"));
        Assert.assertTrue(campaingCampaingColorPrice[1]==campaingCampaingColorPrice[2] |campaingCampaingColorPrice[2]==0);
        //сравнение размеров цены
        Float regularPriceSize=Float.valueOf(driver.findElement(By.xpath("*//s[@class='regular-price']")).getCssValue("font-size").replace("px",""));
        Float campaingPriceSize=Float.valueOf(driver.findElement(By.xpath("*//strong[@class='campaign-price']")).getCssValue("font-size").replace("px",""));
        Assert.assertTrue(campaingPriceSize>regularPriceSize);























    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }

    public Integer[] colorParser (String color){

        String[] colorPars= color.split(",\\s");
        Integer [] colorInt=new Integer[3];
        if (colorPars[0].startsWith("rgba")) {
            colorInt[0]=Integer.valueOf(colorPars[0].replace("rgba(",""));
        } else {
            colorInt[0] = Integer.valueOf(colorPars[0].replace("rgb(", ""));
        }
        colorInt[1]=Integer.valueOf(colorPars[1]);
        colorInt[2]=Integer.valueOf(colorPars[2].replace(")",""));
        return colorInt;


    }




}
