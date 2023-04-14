package br.com.lucas.whatsapbot.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverFactory {



    public static WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        var webDriver = new ChromeDriver(returnChromeConfiguration());
        webDriver.get(UrlWhatsapp.linkWhatsapp());
        return webDriver;
    }

    public static ChromeOptions returnChromeConfiguration(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        return options;
    }
}
