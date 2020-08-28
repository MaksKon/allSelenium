package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class task14 {
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
    public void task14() throws Exception {

        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//li[@id='app-']")));
        driver.findElement(By.xpath("*//span[contains (text(), 'Countries')]")).click();
        //страна
        wait.until(ExpectedConditions.attributeContains(By.xpath("*//a[contains (text(), 'Antarctica')]"),"href","http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=AQ"));
        driver.findElement(By.xpath("*//a[contains (text(), 'Antarctica')]")).click();
        //собираем внешние ссылки
        List <WebElement> hrefList=driver.findElements(By.xpath("*//i[@class='fa fa-external-link']"));
        for (int i=0;i<hrefList.size();i++) {
            String mainPage= driver.getWindowHandle();
            Set <String> oldPage=driver.getWindowHandles();

            hrefList.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldPage.size()+1));
            Set<String> allPage=driver.getWindowHandles();
            allPage.removeAll(oldPage);
            driver.switchTo().window(allPage.iterator().next());
            driver.close();
            driver.switchTo().window(mainPage);
        }

 }


    @After
    public void stop () {
             driver.quit();
             driver=null;
    }







}

