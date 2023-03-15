package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {

            driver.quit();


    }


    @Test
    void shouldTestWeb() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);


        driver.get("http://localhost:9999/");// открыть страницу

        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79898056533");
        driver.findElement(By.tagName("label")).click();
        driver.findElement(By.className("button__text")).click();

        String expected = ("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");
        String actual = driver.findElement(By.tagName("p")).getText();

        assertEquals(expected, actual);


    }


}



