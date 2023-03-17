package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {


        WebDriverManager.chromedriver().setup();


    }


    @BeforeEach
    public void beforeEach() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:9999/");
    }

    @AfterEach
    public void afterEach() {

        driver.quit();
        driver = null;


    }


    @Test
    public void shouldTestWeb() {


        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898056533");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();


        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);


    }

    //@Test
    //void shouldHyphenatedLastName() throws InterruptedException {
    //ChromeOptions options = new ChromeOptions();
    //options.addArguments("--disable-dev-shm-usage");
    //options.addArguments("--no-sandbox");
    //options.addArguments("--headless");
    // options.addArguments("--remote-allow-origins=*");
    // driver = new ChromeDriver(options);


    // driver.get("http://localhost:9999/");// открыть страницу

    //driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Римский-Корсоков Сергей");
    //driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79898056532");
    //driver.findElement(By.tagName("label")).click();
    //driver.findElement(By.className("button__text")).click();

    //String expected = ("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");
    //String actual = driver.findElement(By.tagName("p")).getText();

    //assertEquals(expected, actual);


}








