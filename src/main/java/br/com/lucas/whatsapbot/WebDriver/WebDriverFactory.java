package br.com.lucas.whatsapbot.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.processing.Filer;
import javax.swing.*;
import java.io.*;

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
        //C:/Users/famil/AppData/Local/Google/Chrome/User Data
        options.addArguments("user-data-dir=C:/Users/famil/AppData/Local/Google/Chrome/User/Data");
        return options;
    }
    public static String verifyIfConfigured(){
        File file = new File("Features/pathToUserDate.txt");
        if(file.exists()){
            return readPath();
        }
        return createPath(file);
    }
    public static String readPath(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Features/pathToUserDate.txt"))){
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String createPath(File file){
        String path = JOptionPane.showInputDialog("Informe o caminho do User Data");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

}
