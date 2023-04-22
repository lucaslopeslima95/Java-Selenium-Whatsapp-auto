package br.com.lucas.whatsapbot.WebDriver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.*;
import java.util.Set;
public class WhatsAppWebDriver extends ChromeDriver {

    private static final String COOKIE_FILE_PATH = "Features/cookies.data";
    private Thread saveCookiesThread;

    public WhatsAppWebDriver(ChromeOptions chromeDriver) {
        super(chromeDriver);
        startSaveCookiesThread();
    }

    private void startSaveCookiesThread() {
        saveCookiesThread = new Thread(() -> {
            try {
                System.out.println(1);
                Thread.sleep(60000); // atraso de 1 minuto
                System.out.println(2);
            } catch (InterruptedException e) {
                System.out.println("A thread de salvamento de cookies foi interrompida.");
                return;
            }
            saveCookiesToFile();
        });
        saveCookiesThread.start();
    }



    private void saveCookiesToFile() {
        try {
            Set<Cookie> cookies = this.manage().getCookies();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIE_FILE_PATH));
            oos.writeObject(cookies);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSaveCookiesThread() {
        if (saveCookiesThread != null && saveCookiesThread.isAlive()) {
            saveCookiesThread.interrupt();
            try {
                saveCookiesThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

