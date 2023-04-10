package br.com.feltex.whatsapbot;

import br.com.feltex.whatsapbot.View.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import javax.swing.*;

@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        SpringApplication.run(StartApp.class);
        Runtime.getRuntime();
    }

    @Bean
    public static WebDriver webDriver() {
        System.out.println("Instanciando o Seleniun webdriver");
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        var webDriver = new ChromeDriver(options);
        webDriver.get("https://web.whatsapp.com/");
        return webDriver;
    }

}
