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
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class task12 {
    private WebDriver driver;



    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        //driver= new InternetExplorerDriver();
        //driver= new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task12()throws Exception{
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("*//span[contains (text(), 'Catalog')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("*//a[contains (@href, 'edit_product')]")).click();
        Thread.sleep(1000);
        //заполняем форму нового продукта
        driver.findElement(By.xpath("*//input[@name='status'][@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("Item");
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("9999");
        driver.findElement(By.xpath("//input[@name='product_groups[]'][@value='1-1']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Keys.HOME+"100");

        File directory = new File("52400676.jpg");
        String track=directory.getAbsolutePath().replace("\\","/");
        //System.out.println(track);
        driver.findElement(By.xpath("*//input[@type='file']")).sendKeys(track);
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(Keys.HOME+"15062020");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(Keys.HOME+"15102020");
        //вкладка
        driver.findElement(By.xpath("*//a[contains (@href, 'information')]")).click();
        Thread.sleep(1000);
        WebElement plant=driver.findElement(By.xpath("//select[@name='manufacturer_id']"));
        Select selectplant=new Select(plant);
        selectplant.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("rabbit, red rabbit");
        driver.findElement(By.xpath("*//input[contains (@name, 'short')]")).sendKeys("Red Rabbit");
        driver.findElement(By.xpath("*//div[@class='trumbowyg-editor']")).sendKeys("Small Red Rabbit");
        driver.findElement(By.xpath("*//input[contains (@name, 'head_title')]")).sendKeys("Rabbit");
        driver.findElement(By.xpath("*//input[contains (@name, 'meta_description')]")).sendKeys("rrRabbit");
        //переход на вкладку цены
        driver.findElement(By.xpath("*//a[contains (@href, 'prices')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.HOME+"10");
        driver.findElement(By.xpath("*//select/option[@value='USD']")).click();
        driver.findElement(By.xpath("*//input[@type='number'][contains (@name, 'USD')]")).sendKeys(Keys.HOME+"10");
        driver.findElement(By.xpath("*//input[@type='number'][contains (@name, 'EUR')]")).sendKeys(Keys.HOME+"12");
        driver.findElement(By.xpath("//button[@name='save']")).click();
        Thread.sleep(1000);
        //проверка наличия товара
        Assert.assertTrue(driver.findElements(By.xpath("*//table[@class='dataTable']//a[contains (text(),'Item')]")).size()>0);
























        


    }

    @After
    public void stop(){
    //    driver.quit();
    //    driver=null;
    }




}
