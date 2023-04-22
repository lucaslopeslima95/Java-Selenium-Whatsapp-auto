package br.com.lucas.whatsapbot.WebDriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    public static WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        var webDriver = new WhatsAppWebDriver(returnChromeConfiguration());
        webDriver.get(UrlWhatsapp.linkWhatsapp());
        webDriver.stopSaveCookiesThread();
        return webDriver;
    }

    public static ChromeOptions returnChromeConfiguration(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("disable-extensions");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--allow-insecure-websocket-from-https-origin");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.last_modified", new Date().getTime());
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}
