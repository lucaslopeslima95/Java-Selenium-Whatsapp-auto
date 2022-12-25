package br.com.feltex.whatsapbot.controller;

import br.com.feltex.whatsapbot.model.Message;
import org.openqa.selenium.*;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/send")
public class SendMessage {


    private final WebDriver webDriver;
    private String positionTopScrollBeforeScrolling = "";
    private String positionTopScrollAfterScrolling = "";
    boolean flag = true;
    private final Set<String> contacts = new LinkedHashSet<>();

    @PostMapping
    public void receiverMessage(@RequestBody Message message) {
        scrollToTop();
        for (String contact : message.getContacts()) {
            flag = true;
            do {
                try {
                    var elementContact = findContact(contact);

                    if (elementContact != null) {
                        elementContact.click();

                        var canvasMessage = findBoxText();

                        canvasMessage.sendKeys(message.getContent());

                        canvasMessage.sendKeys(Keys.RETURN);

                        Thread.sleep(1500);

                        webDriver.findElement(By.cssSelector("span[data-icon='clip']")).click();

                        Thread.sleep(1500);

                        webDriver.findElement(By.cssSelector("input[type='file']"))
                                .sendKeys(message.getPathImage().replaceAll("\\\\","/"));

                        Thread.sleep(1500);

                        webDriver.findElement(By.xpath("//*[@id='app']/*//span[@data-icon='send']")).click();

                        Thread.sleep(1000);

                        flag = false;
                    }
                } catch (Exception e) {

                    positionTopScrollBeforeScrolling = getscrollPosition();
                    scrollingBy();
                    positionTopScrollAfterScrolling = getscrollPosition();

                    if (positionTopScrollBeforeScrolling.equals(positionTopScrollAfterScrolling)) {
                        flag = false;
                        scrollToTop();
                    }else{
                        scrollingBy();
                    }
                }
            } while (flag);
        }
    }
    @GetMapping
    public Set<String> requestContactsNames() {
        flag = true;
        scrollToTop();
        do{
            try {
                positionTopScrollBeforeScrolling = getscrollPosition();
                Thread.sleep(1000);
                for (int i = 18; i >= 1; i--) {
                    try {
                       contacts.add(webDriver.findElement(By.xpath("//*[@id=\"pane-side\"]/div[2]/div/div/div["+i+"]/div/div/div/div[2]/div[1]/div[1]/span/span")).getText());
                    }catch (Exception e){
                        System.out.println("Causado por: "+e.getMessage());
                    }

                }
                returnJSExecutor().executeScript("document.querySelector('#pane-side').scrollBy({top: 80 ,left: 0})");
                positionTopScrollAfterScrolling = getscrollPosition();
                if(positionTopScrollBeforeScrolling.equals(positionTopScrollAfterScrolling)) {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Causado por: "+e.getCause());
            }
        }while(flag);

        return contacts;
    }

    public  WebElement findContact(String nameContact) {
        var xPathContact = "//*[@id=\"pane-side\"]/*//span[@title='" + nameContact + "']";
        return webDriver.findElement(By.xpath(xPathContact));
    }

    public WebElement findBoxText() {
        var xPathBoxText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
        return webDriver.findElement(By.xpath(xPathBoxText));
    }

    public void scrollToTop(){
        returnJSExecutor().executeScript("document.querySelector('#pane-side').scrollTo({top: 0 ,left: 0})");
    }

    public JavascriptExecutor returnJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return js;
    }
    public String getscrollPosition(){
        return returnJSExecutor().executeScript("return document.querySelector('#pane-side').scrollTop").toString();
    }
    public void scrollingBy(){
        returnJSExecutor().executeScript("document.querySelector('#pane-side').scrollBy({top: 99 ,left: 0,behavior: 'smooth'})");
    }

    public SendMessage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


}
