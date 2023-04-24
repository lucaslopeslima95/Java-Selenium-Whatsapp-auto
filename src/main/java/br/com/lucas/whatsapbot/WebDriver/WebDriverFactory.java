package br.com.lucas.whatsapbot.WebDriver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.*;

public class WebDriverFactory {

    public static WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        var webDriver = new ChromeDriver(returnChromeConfiguration());
        webDriver.get(UrlWhatsapp.linkWhatsapp());
        return webDriver;
    }

    public static ChromeOptions returnChromeConfiguration() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("user-data-dir=C:/Program Files/Google/Chrome/Application/112.0.5615.138/MEIPreload");
        return options;
    }

   /** private static  WebDriver saveCookies(WebDriver driver){
        Timer timer = new Timer();
        File file = new File("Features/cookies.txt");
        if(file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));){
                Set<Cookie> cookies = (Set<Cookie>) objectInputStream.readObject();
                for (Cookie cookie : cookies) {
                    driver.manage().addCookie(cookie);
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
                        Set<Cookie> cookies = driver.manage().getCookies();
                        objectOutputStream.writeObject(cookies);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            timer.schedule(timerTask, 60*1000);

        }
        return driver;
    }**/


}
