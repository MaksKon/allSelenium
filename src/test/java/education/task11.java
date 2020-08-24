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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class task11 {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        //driver= new InternetExplorerDriver();
        //driver= new FirefoxDriver();
        wait= new WebDriverWait(driver,10);


        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task11()throws Exception{
        driver.get("http://localhost/litecart");
        driver.findElement(By.xpath("*//form//a[contains (@href, 'create')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("*//input[@name='firstname']")).sendKeys("Stephen");
        driver.findElement(By.xpath("*//input[@name='lastname']")).sendKeys("King");
        driver.findElement(By.xpath("*//input[@name='address1']")).sendKeys("47 W Broadway,  ME");
        driver.findElement(By.xpath("*//input[@name='postcode']")).sendKeys("04401");
        driver.findElement(By.xpath("*//input[@name='city']")).sendKeys("Bangor");
        driver.findElement(By.xpath("//span[@role='presentation']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("United States");
        driver.findElement(By.xpath("*//li[contains (text(),'United States')]")).click();
        WebElement state=driver.findElement(By.xpath("//select[@name='zone_code']"));
        Select selectState=new Select(state);
        selectState.selectByVisibleText("Maine");
        String mailAdress=(int) (Math.random()*100)+"@com.com";
        driver.findElement(By.xpath("*//input[@name='email']")).sendKeys(mailAdress);
        driver.findElement(By.xpath("*//input[@name='phone']")).sendKeys(Keys.HOME+"+11234567890");
        driver.findElement(By.xpath("*//input[@name='password']")).sendKeys("12345");
        driver.findElement(By.xpath("*//input[@name='confirmed_password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        System.out.println(mailAdress);
        Thread.sleep(1000);
        driver.findElement(By.xpath("*//div[@id='box-account']//a[contains (@href, 'logout')]")).click();
        //повторное логирование
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(mailAdress);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        //повторный выход
        Thread.sleep(1000);
        driver.findElement(By.xpath("*//div[@id='box-account']//a[contains (@href, 'logout')]")).click();

    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }




}
