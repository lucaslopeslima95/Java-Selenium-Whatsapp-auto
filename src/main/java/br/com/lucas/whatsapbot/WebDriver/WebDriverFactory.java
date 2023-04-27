package br.com.lucas.whatsapbot.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        //C:/Users/famil/AppData/Local/Google/Chrome/User Data"
        options.addArguments("user-data-dir=");
        return options;
    }

    public static String verifyIfConfigured(){
        File file = new File("Features/pathToUserDate.txt");
        if(file.exists()){
            return readPath();
        }
        return createPath();
    }
    public static String readPath(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Features/pathToUserDate.txt"))){
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createPath(){
        File file = new File("Features/pathToUserDate.txt");


        return null;
    }

}
