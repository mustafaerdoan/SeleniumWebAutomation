package org.gittigidiyor;
import jdk.internal.net.http.common.Log;
import log.LogClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.apache.log4j.Logger;
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
        LogClass.startLog("testing is start");
        openSecondPage(); //opening second page of what we searched in search box
        LogClass.info("opened second page");
        addingCart();// adding item to cart
        LogClass.info("added item");
        removeItems();// removing all of items in cart
        LogClass.info("removed");
        LogClass.endLog("ended log");
        }

    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
        LogClass.endLog("ended log");
    }
    public void ComputerSearch(){

        LogClass.info("Test is startin");
        WebElement searchBox;
        /* to search "bilgisayar" in search box */
        searchBox = driver.findElement(By.className("sc-4995aq-4 dNPmGY"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.className("qjixn8-0 sc-1bydi5r-0 hKfdXF")).click();
    }
    public void addingCart(){
        LogClass.info("starting to add item to chart");
        /* After searching item, adding to cart that item*/
        WebElement quantityBox = driver.findElement(By.id("quantity"));
        quantityBox.click();
        quantityBox.clear();
        quantityBox.sendKeys("1");

        WebElement basketBtn = driver.findElement(By.className("gg-ui-button gg-ui-btn-primary"));
        basketBtn.click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("header-cart-hidden-link")).click();
        LogClass.info("added to cart"+driver.getClass());
    }
    public void removeItems(){
        /* To remove all of items in cart */
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("gg-icon gg-icon-bin-medium")).click();
        LogClass.info("removed all of items from cart");
    }
    public void openSecondPage() {
        /* To open 2nd page of searching result page and to pick second  item */
        driver.findElement(By.xpath(".//*[@class='pagination']/a[2]")).click();
        driver.findElement(By.xpath(".//*[@id='p-369374378']/div[1]/a[1]")).click();

        WebElement price = driver.findElement(By.xpath(".//*[@class='newPrice']/ins[1]"));
        String priceText = price.getText();
        LogClass.info("we have opened result of what you have searched"+priceText);

        /* To compare price of item between currentPage and cart */
        WebElement priceBasket = driver.findElement(By.className("total-price"));
        String priceText2 = priceBasket.getText();
        if (priceText.compareTo(priceText2) > 0) {

            /* To increase amount of product in cart */
            WebElement quantityBasket = driver.findElement(By.id("quantity_126686985817"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("1");
            driver.findElement(By.className("amount")).click();
            LogClass.info("we have increased amount of item");

        }
    }
}
