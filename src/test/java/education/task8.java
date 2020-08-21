package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class task8 {
    private WebDriver driver;


    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task8()throws Exception{
        driver.get("http://localhost/litecart");
        //ищем все товары представленные на главной
        List<WebElement> productList=driver.findElements(By.xpath("*//li[contains (@class, 'product')]"));
        //перебираем товары и проверяем стикеры
        for (int i=0; i< productList.size();i++){
              if (productList.get(i).findElements(By.xpath("*//div[contains (@class, 'sticker')]")).size()>1){
                  System.out.println("у одного из есть несколько стикеров");
              } else if (productList.get(i).findElements(By.xpath("*//div[contains (@class, 'sticker')]")).size()==0){
                  System.out.println("у одного из товаров отсутсвует стикер");
              }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }



}
