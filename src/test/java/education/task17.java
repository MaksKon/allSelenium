package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class task17 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        //driver= new InternetExplorerDriver();
        //driver= new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task17() throws Exception {

        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//li[@id='app-']")));
        driver.findElement(By.xpath("*//span[contains (text(), 'Catalog')]")).click();
        driver.findElement(By.xpath("*//a[contains (text(), 'Rubber Ducks')]")).click();

        //старый лог
        List<LogEntry> oldLog=driver.manage().logs().get("browser").getAll();
        List<WebElement> itemList=driver.findElements(By.xpath("*//tr//a[contains (@href, 'product_id')][@title='Edit']"));
        for (int i=0; i< itemList.size();i++){
            driver.findElements(By.xpath("*//tr//a[contains (@href, 'product_id')][@title='Edit']")).get(i).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='save']")));
            List<LogEntry> logList=driver.manage().logs().get("browser").getAll();
            int sizelog= oldLog.size();
            for (int z= sizelog+1;z<logList.size();z++) {
                System.out.println(logList.get(z));
                oldLog.add(logList.get(z));
            }
            driver.navigate().back();
            }

        }











    @After
    public void stop () {
        driver.quit();
        driver=null;
    }








}
