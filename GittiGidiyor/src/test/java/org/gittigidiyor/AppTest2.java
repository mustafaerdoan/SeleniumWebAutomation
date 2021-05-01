package org.gittigidiyor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AppTest2 {
    public WebDriver driver;
    //public WebDriverWait wait;
    @Before
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\cmust\\Desktop\\GittiGidiyor\\chromedriver.exe");
        driver = new ChromeDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }
    @Test
    public void TestSearch() {

        ComputerSearch(); // searching "bilgisayar in search box"
        openSecondPage(); //opening second page of what we searched in search box
        addingCart();// adding item to cart
        removeItems();// removing all of items in cart
        }

    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
    public void ComputerSearch(){
        WebElement searchBox;
        /* to search "bilgisayar" in search box */
        searchBox = driver.findElement(By.id("searchData"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.className("searchBtn")).click();
    }
    public void addingCart(){
        /* After searching item, adding to cart that item*/
        WebElement quantityBox = driver.findElement(By.id("quantity"));
        quantityBox.click();
        quantityBox.clear();
        quantityBox.sendKeys("1");

        WebElement basketBtn = driver.findElement(By.className("btnAddBasket"));
        basketBtn.click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("iconBasket")).click();
    }
    public void removeItems(){
        /* To remove all of items in cart */
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("removeProd")).click();
    }
    public void openSecondPage() {
        /* To open 2nd page of searching result page and to pick second  item */
        driver.findElement(By.xpath(".//*[@class='pagination']/a[2]")).click();
        driver.findElement(By.xpath(".//*[@id='p-369374378']/div[1]/a[1]")).click();

        WebElement price = driver.findElement(By.xpath(".//*[@class='newPrice']/ins[1]"));
        String priceText = price.getText();

        /* To compare price of products between currentPage and cart */
        WebElement priceBasket = driver.findElement(By.className("price"));
        String priceText2 = priceBasket.getText();
        if (priceText.compareTo(priceText2) > 0) {

            /* To increase amount of product in cart */
            WebElement quantityBasket = driver.findElement(By.id("quantity_126686985817"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("1");
            driver.findElement(By.className("spinnerUp")).click();

        }
    }
}
