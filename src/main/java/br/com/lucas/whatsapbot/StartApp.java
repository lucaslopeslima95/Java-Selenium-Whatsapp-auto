package br.com.lucas.whatsapbot;

import br.com.lucas.whatsapbot.ViewDesktop.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import javax.swing.*;

@EnableFeignClients
@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        SpringApplication.run(StartApp.class);
        Runtime.getRuntime();
    }
}
