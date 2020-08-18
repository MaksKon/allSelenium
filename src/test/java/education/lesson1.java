package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lesson1 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver,10);
    }

    @Test
    public void lesson1(){
        driver.get("https://www.gmail.com/");
    }

    @After
    public void stop(){
        driver.quit();
    }


}
