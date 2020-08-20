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

public class task7 {



        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void startBrowser (){
            driver= new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }

        @Test
        public void task7()throws Exception{
            //логирование
            driver.get("http://localhost/litecart/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            //верхнеуровневый список
            List<WebElement> headList=driver.findElements(By.cssSelector("li#app-"));
            //определяем размер
            int sizeList= headList.size();
            //верх дерева
            for (int i=1;i<sizeList+1;i++){
                driver.findElement(By.xpath("//li[@id='app-']["+i+"]")).click();
                check(driver.findElement(By.xpath(".//li//span[@class='name']")).getText());
                if (driver.findElements(By.xpath(".//li[@id='app-']["+i+"]//li[starts-with (@id, 'doc-')]")).size()>0){
                    //вложенные
                    for (int z=2;z<driver.findElements(By.xpath(".//li[@id='app-']["+i+"]//li[starts-with (@id, 'doc-')]")).size()+1;z++){
                        driver.findElement(By.xpath(".//li[@id='app-']["+i+"]//li[starts-with (@id, 'doc-')]["+z+"]")).click();
                        check(driver.findElement(By.xpath(".//li[@id='app-']["+i+"]//li[starts-with (@id, 'doc-')]["+z+"]//span[@class='name']")).getText());

                    }
                }


            }






        }

        @After
        public void stop(){
            driver.quit();
            driver=null;
        }

    /**
     * проверка наличия объекта с тэгом h1
     * @param text - наименование пунтка меню в котором нет тэга h1
     */
    public void check(String text){
        if (!(driver.findElements(By.cssSelector("h1")).size()>0)){
            System.out.println("отсутсвует заголовок в пункте меню "+text);
        }


        }






        


    }


