package br.com.feltex.whatsapbot;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        SpringApplication.run(StartApp.class);
        Runtime.getRuntime();
    }

    @Bean
    public WebDriver webDriver() {
        System.out.println("Instanciando o Seleniun webdriver");
        System.setProperty("webdriver.chrome.driver", "Features/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        var webDriver = new ChromeDriver(options);
        webDriver.get("https://web.whatsapp.com/");
        return webDriver;
    }
}
