package br.com.lucas.whatsapbot.WebDriver;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WebDriverFactory {
    public static WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        var webDriver = new ChromeDriver(returnChromeConfiguration());
        webDriver.get(UrlWhatsapp.linkWhatsapp());
        return saveCookies(webDriver);
    }

    public static ChromeOptions returnChromeConfiguration() {
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

    private static WebDriver saveCookies(WebDriver driver){
        File file = new File("Features/cookies.txt");
        if(!file.exists()) {
            try {
                Thread.sleep(10000);
                file = new File("Features/cookies.txt");
                FileUtils.write(file, "", "UTF-8");
                Set<Cookie> cookies = driver.manage().getCookies();
                for (Cookie cookie : cookies) {
                    FileUtils.write(file, cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure() + System.lineSeparator(), "UTF-8", true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                    while (stringTokenizer.hasMoreTokens()) {
                        String name = stringTokenizer.nextToken();
                        String value = stringTokenizer.nextToken();
                        String domain = stringTokenizer.nextToken();
                        String path = stringTokenizer.nextToken();
                        Date expiry = null;
                        String dt;
                        if (!(dt = stringTokenizer.nextToken()).equals("null")) {
                            expiry = new Date(dt);
                        }
                        boolean isSecure = Boolean.valueOf(stringTokenizer.nextToken());
                        Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                        driver.manage().addCookie(cookie);
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        driver.navigate().refresh();
        return driver;
    }
}
