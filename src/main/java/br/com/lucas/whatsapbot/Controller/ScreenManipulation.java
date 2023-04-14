package br.com.lucas.whatsapbot.Controller;

import br.com.lucas.whatsapbot.Model.Message;
import br.com.lucas.whatsapbot.WebDriver.WebDriverFactory;
import org.openqa.selenium.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/send")
public class ScreenManipulation {

    private final WebDriver webDriver;
    private String positionTopScrollBeforeScrolling = "";
    private String positionTopScrollAfterScrolling = "";
    boolean flag = true;
    private final Set<String> contacts = new LinkedHashSet<>();

    public ScreenManipulation() {
        this.webDriver = WebDriverFactory.webDriver();
    }


    @PostMapping
    public void receiverMessage(@RequestBody Message message)  {
        for (String contact : message.getContacts()) {
            flag = true;
            scrollToTop();
            do {
                try {
                    var elementContact = findContact(contact);
                    if (elementContact != null) {
                        elementContact.click();
                        Thread.sleep(3000);
                        if (doSequenceToSendMessageOnChatScreen(message)){
                            flag = false;
                        }
                    }
                } catch (Exception e) {

                    positionTopScrollBeforeScrolling = getscrollPosition();
                    scrollingBy();
                    positionTopScrollAfterScrolling = getscrollPosition();

                    if (positionTopScrollBeforeScrolling.equals(positionTopScrollAfterScrolling)) {
                        sendMessageFromScheduleScreen(message,contact);
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
                Thread.sleep(700);
                for (int i = 20; i > 0; i--) {
                    try {
                        contacts.add(webDriver.findElement(By.xpath("//*[@id=\"pane-side\"]/div[2]/div/div/div["+i+"]/div/div/div/div[2]/div[1]/div[1]/span/span")).getText());
                    }catch (Exception e){
                        System.err.println("Causado por: "+e.getMessage());
                    }
                }
                scrollingBy();
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

    public boolean doSequenceToSendMessageOnChatScreen(Message message) throws InterruptedException {
        var canvasMessage = findBoxText();
        Thread.sleep(1500);
        canvasMessage.sendKeys(message.getContent());
        Thread.sleep(1500);
        canvasMessage.sendKeys(Keys.RETURN);
        Thread.sleep(1500);
        webDriver.findElement(By.cssSelector("span[data-icon='clip']")).click();
        Thread.sleep(1500);
        webDriver.findElement(By.cssSelector("input[type='file']"))
                .sendKeys(message.getPathImage().replaceAll("\\\\", "/"));
        Thread.sleep(1500);
        webDriver.findElement(By.xpath("//*[@id='app']/*//span[@data-icon='send']")).click();
        Thread.sleep(180*1000);
        return true;
    }

    public void sendMessageFromScheduleScreen(Message message, String contact){
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[3]/header/div[2]/div/span/div[2]/div/span")).click();
        do {
            try {
                var elementContact = findContactSchedule(contact);
                    elementContact.click();
                    Thread.sleep(3000);
                    if (doSequenceToSendMessageOnChatScreen(message)){
                        flag = false;
                    }

            } catch (Exception f) {

                positionTopScrollBeforeScrolling = getScrollPositionSchedule();
                scrollingBySchedule();
                positionTopScrollAfterScrolling = getScrollPositionSchedule();

                if (positionTopScrollBeforeScrolling.equals(positionTopScrollAfterScrolling)) {
                    webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/span/div/span/div/header/div/div[1]/div/span")).click();
                    flag = false;
                }
            }
        } while (flag);
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
        returnJSExecutor().executeScript("document.querySelector('#pane-side').scrollBy({top: 99 ,left: 0})");
    }

    public  WebElement findContactSchedule(String nameContact) {
        var xPathContact = "//div[@id='app']/*//div[@role='button']/*//span[@title='"+nameContact+"']";
        return webDriver.findElement(By.xpath(xPathContact));
    }
    public void scrollingBySchedule(){
        returnJSExecutor().executeScript("document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollBy({top: 99 ,left: 0})");
    }
    public void scrollToTopSchedule(){
        returnJSExecutor().executeScript("document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTo({top: 0 ,left: 0})");
    }
    public String getScrollPositionSchedule(){
        return returnJSExecutor().executeScript("return  document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTop").toString();
    }
}
