package br.com.feltex.whatsapbot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class StartAppTest {
    @Test
    void testWebDriver() {
        WebDriver driver = StartApp.webDriver();
        Assertions.assertTrue(driver instanceof ChromeDriver);
    }
}