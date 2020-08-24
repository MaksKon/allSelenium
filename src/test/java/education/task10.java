package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class task10 {

    private WebDriver driver;


    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task9()throws Exception{
        driver.get("http://localhost/litecart");
        //driver.findElement(By.xpath(""))



    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }




}
