package cmc.msu.webpracjaba.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application-test.properties")
public class EmployeeAllTest {
    @BeforeAll
    static void setUpBeforeClass() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test
    void ViewEmployees() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals("Home", driver.getTitle());

        WebElement peopleButton = driver.findElement(By.cssSelector("body > div.container > section > div > div > a:nth-child(1)"));
        peopleButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Поиск работников", driver.getTitle());

        driver.quit();
    }

    @Test
    void FindEmployeesNoError() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/employee");
        assertEquals("Поиск работников", driver.getTitle());

        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > fieldset > label:nth-child(1) > input[type=text]")).sendKeys("Юлия");
        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > fieldset > label:nth-child(3) > input[type=text]")).sendKeys("Викторова");
        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > input")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        driver.findElement(By.cssSelector("body > div.container.table-tab > table > tbody > tr > td:nth-child(1) > a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Работник№1", driver.getTitle());

        driver.quit();
    }

    @Test
    void FindEmployeesError() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/employee");
        assertEquals("Поиск работников", driver.getTitle());

        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > fieldset > label:nth-child(1) > input[type=text]")).sendKeys("Юлия");
        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > fieldset > label:nth-child(3) > input[type=text]")).sendKeys("ВиктороваСамоварова");
        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div:nth-child(2) > form > input")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement resultSet = driver.findElement(By.cssSelector("body > div.container.table-tab > table > tbody > tr > td"));
        assertEquals("Сотрудники не найдены.", resultSet.getText());

        driver.quit();
    }

    @Test
    void addEditDeleteEmployeeError() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/employee");
        assertEquals("Поиск работников", driver.getTitle());

        driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div.container.align-content-center > section > div > div > a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавление сотрудника", driver.getTitle());

        driver.findElement(By.cssSelector("body > div.container > div > div > form > fieldset > label:nth-child(1) > input[type=text]")).sendKeys("Тест");
        driver.findElement(By.cssSelector("body > div.container > div > div > form > fieldset > label:nth-child(3) > input[type=text]")).sendKeys("Тестов");
        driver.findElement(By.cssSelector("body > div.container > div > div > form > fieldset > label:nth-child(5) > input[type=text]")).sendKeys("Тестович");
        driver.findElement(By.cssSelector("body > div.container > div > div > form > fieldset > label:nth-child(7) > input[type=text]")).sendKeys("ТЕСТ тест");
        driver.findElement(By.cssSelector("body > div.container > div > div > form > fieldset > label:nth-child(9) > input[type=text]")).sendKeys("г. Тест");
        driver.findElement(By.cssSelector("body > div.container > div > div > form > input")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        driver.findElement(By.cssSelector("body > div.container > div:nth-child(2) > div > a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(2) > div:nth-child(2) > form > fieldset > label:nth-child(2) > input[type=text]")).sendKeys("Тестинг");
        driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(2) > div:nth-child(2) > form > fieldset > label:nth-child(12) > input[type=number]")).sendKeys("1");
        driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(2) > div:nth-child(2) > form > fieldset > label:nth-child(14) > input[type=number]")).sendKeys("1");
        driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(2) > div:nth-child(2) > form > input")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Тестинг", driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(1) > div > div:nth-child(2) > h5")).getText());
        driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(2) > div:nth-child(1) > div > form > section > div > div > input")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Ошибка!", driver.getTitle());

        driver.quit();
    }
}
