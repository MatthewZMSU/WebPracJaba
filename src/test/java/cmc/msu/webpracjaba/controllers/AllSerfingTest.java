package cmc.msu.webpracjaba.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application-test.properties")
public class AllSerfingTest {
    @BeforeAll
    static void setUpBeforeClass() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test
    void HomePage1() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals("Home", driver.getTitle());
        driver.quit();
    }

    @Test
    void HomePage2() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/home");
        assertEquals("Home", driver.getTitle());
        driver.quit();
    }
}
