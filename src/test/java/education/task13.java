package education;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class task13 {

    private WebDriver driver;
    private WebDriverWait wait;



    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        //driver= new InternetExplorerDriver();
        //driver= new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait= new WebDriverWait(driver,10);
    }

    @Test
    public void task13()throws Exception{
        driver.get("http://localhost/litecart");
        //набираем товар
        int itemСart=1;
        while (itemСart<4){
            List<WebElement> itemsList=driver.findElements(By.xpath("//div[@id='box-most-popular']//li"));
            int i=itemsList.size();
            wait.until(ExpectedConditions.visibilityOf(itemsList.get(itemСart))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add_cart_product']")));
            if (driver.findElements(By.xpath("*//select[@required='required']")).size()>0){
                driver.findElement(By.xpath("*//select[@required='required']//option[@value='Small']")).click();
            }
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='quantity']"),Integer.toString(itemСart)));
            driver.findElement(By.xpath("//div[@id='logotype-wrapper']")).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.titleContains("Checkout")));
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[@id='cart']//a[@class='link']"))));

            itemСart++;

        }
        //чистим корзину
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='cart']//a[@class='link']")))).click();

        while (driver.findElements(By.xpath("*//td[@class='item']")).size()!=0){
            List<WebElement> cartList=driver.findElements(By.xpath("*//td[@class='item']"));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("*//button[@name='remove_cart_item']")).get(0))).click();
            // контроль по таблице
            wait.until(ExpectedConditions.numberOfElementsToBe((By.xpath("*//td[@class='item']")),cartList.size()-1));

        }
    }


    @After
    public void stop(){
        driver.quit();
        driver=null;
    }








}
