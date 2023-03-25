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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        options.addArguments("--remote-allow-origins=*");
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

    @Test
    public void shouldTestThenLastNameWithAHyphen() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов-Корсоков Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898056533");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();


        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);


    }
    @Test
    public void shouldTestThenTheLastNameFieldIsNotFilledIn() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898056533");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector(".input__sub")).getText().trim();


        assertEquals("Поле обязательно для заполнения", actualText);


    }
    @Test
    public void shouldTestThenTheLastNameFieldWithTheABCLetters() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Sergey");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898056533");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector(".input__sub")).getText().trim();


        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualText);


    }
    @Test
    public void shouldTestThenTheLastNameFieldWithSymbol() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("сер%%гей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898056533");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector(".input__sub")).getText().trim();


        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualText);


    }
    @Test
    public void shouldTestThenPhoneFieldWith5DigitsInThePhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79898");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText().trim();


        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);


    }
    @Test
    public void shouldTestThenPhoneFieldWith12DigitsInThePhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+798982365895");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText().trim();


        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);


    }
    @Test
    public void shouldTestThenPhoneFieldWithout7() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("89898236589");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText().trim();


        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);


    }
    @Test
    public void shouldTestThenPhoneFieldWithoutNumber() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText().trim();


        assertEquals("Поле обязательно для заполнения", actualText);


    }
    @Test
    public void shouldTestThenPhoneFieldNumberWithSymbols() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79012%$5678");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone] span.input__sub")).getText().trim();


        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);


    }

    @Test
    public void shouldTestThenPhoneFieldWith0DigitsInThePhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();


        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);


    }
    @Test
    public void shouldTestThenWithoutFlagInCheckbox() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79012765678");

        driver.findElement(By.cssSelector("button.button")).click();

       // var actualText = driver.findElement(By.cssSelector("[checkbox checkbox_size_m checkbox_hovered checkbox_theme_alfa-on-white input_invalid]")).getText().trim();


        assertTrue(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());


    }
}








