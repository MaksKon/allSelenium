package education;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Array;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class task9 {
    private WebDriver driver;


    @Before
    public void startBrowser (){
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void task9()throws Exception{
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List <WebElement> tableLocatorList=driver.findElements(By.xpath("*//tr[@class='row']"));
        //часть 1
        //проверка списка стран на сортировку
        ArrayList <String> countrieList = new ArrayList<String>() ;
        for (int i=0; i<tableLocatorList.size();i++){
            countrieList.add(i,tableLocatorList.get(i).findElement(By.xpath("*//a[1]")).getText());

        }
        ArrayList <String> sortCountrieList = new ArrayList<String>(countrieList) ;
        Arrays.sort(new ArrayList[]{sortCountrieList});
        //сравнение двух списков
        for (int i=0;i<countrieList.size();i++){
            if (!countrieList.get(i).equals(sortCountrieList.get(i))){
                System.out.println("на странице неправильно отсортированы страны, страна "+countrieList.get(i)+" а должна быть "+ sortCountrieList.get(i) );
            }
        }
        //часть 2
        ArrayList <String> hrefcountrieZoneList= new ArrayList<>();
        //ищем страны с зонами отличными от нуля и запомнием ссылки по ним
        for (int i=0; i<tableLocatorList.size();i++){
            if (!tableLocatorList.get(i).findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0")){
                hrefcountrieZoneList.add(tableLocatorList.get(i).findElement(By.xpath("*//a[1]")).getAttribute("href"));
            }
        }
        //работаем по ссылкам
        for (int i=0; i< hrefcountrieZoneList.size();i++){
            driver.get(hrefcountrieZoneList.get(i));
            ArrayList <String> nameList=new ArrayList<String>();
            List<WebElement> nameLocatorList=driver.findElements(By.xpath("*//td/input[contains (@name, 'name')][@type='hidden']"));
            for (int z=0;z<nameLocatorList.size();z++){
                nameList.add(z,nameLocatorList.get(z).getAttribute("value"));
            }
            ArrayList <String> sortNameList=new ArrayList<String>(nameList);
            Arrays.sort(new ArrayList[]{sortNameList});
            for (int y=0;y<nameList.size();y++){
                if (!nameList.get(y).equals(sortNameList.get(y))){
                    System.out.println("на странице неправильно отсортированы страны, страна "+nameList.get(y)+" а должна быть "+ sortNameList.get(y) );
                }
            }
        }
    }
    @Test
    public void task9_2() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List <WebElement> tableLocatorList=driver.findElements(By.xpath("*//tr[@class='row']//a[@title='Edit']"));
        ArrayList <String> hrefcountrieZoneList= new ArrayList<>();
        for (int i=0; i<tableLocatorList.size();i++){
            hrefcountrieZoneList.add(tableLocatorList.get(i).getAttribute("href"));
        }
        for (int i=0; i< hrefcountrieZoneList.size();i++){
            driver.get(hrefcountrieZoneList.get(i));
            ArrayList <String> nameList=new ArrayList<String>();
            List<WebElement> nameLocatorList=driver.findElements(By.xpath("*//select[contains (@name, 'zone_code')]//option[@selected='selected']"));
            for (int z=0;z<nameLocatorList.size();z++){
                nameList.add(z,nameLocatorList.get(z).getText());
            }
            ArrayList <String> sortNameList=new ArrayList<String>(nameList);
            Arrays.sort(new ArrayList[]{sortNameList});
            for (int y=0;y<nameList.size();y++){
                if (!nameList.get(y).equals(sortNameList.get(y))){
                    System.out.println("на странице неправильно отсортированы страны, страна "+nameList.get(y)+" а должна быть "+ sortNameList.get(y) );
                }
            }
        }











    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }







}
