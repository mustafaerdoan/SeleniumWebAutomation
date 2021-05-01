package org.gittigidiyor;
import jdk.internal.net.http.common.Log;
import log.LogClass;
import org.junit.After;
import org.junit.Before;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AppTest {

    public  WebDriver driver;

    //@Before
    // By WebDriver to connect "GittiGidiyor" web page
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\cmust\\Desktop\\GittiGidiyor\\chromedriver.exe");
        driver = new ChromeDriver();
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    // to login to webpage by using id
    public void TestHome(){
        LogClass.startLog("started log");
        WebElement signbtn= driver.findElement(By.className("gekhq4-6 hnYHyk"));
        signbtn.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement mailbox= driver.findElement(By.id("L-UserNameField"));
        mailbox.click();
        mailbox.sendKeys("mstafaerdoan@gmail.com");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("Mustafa123");

        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.id("gg-login-enter")).click();
        LogClass.info("logged to system");
    }
    //@After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
        LogClass.endLog("ended log");
    }
}
