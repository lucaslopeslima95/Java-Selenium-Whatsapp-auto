package br.com.feltex.whatsapbot.controller;

import br.com.feltex.whatsapbot.model.Message;
import org.openqa.selenium.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/sendOnSchedule")
public class SendMessageOnSchedule {
    private final WebDriver webDriver;
    private String positionTopScrollBeforeScrolling = "";
    private String positionTopScrollAfterScrolling = "";
    boolean flag = true;
    private final Set<String> contacts = new LinkedHashSet<>();

    @PostMapping
    public void receiverMessageOnSchedule(@RequestBody Message message) {
        scrollToTop();
        for (String contact : message.getContacts()) {
            flag = true;
            do {
                try {
                    webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[3]/header/div[2]/div/span/div[2]/div/span")).click();
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
                       contacts.add(webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/span/div/span/div/div[2]/div[2]/div/div/div["+i+"]/div/div/div[2]/div[1]/div/span/span")).getText());
                    }catch (Exception e){
                        System.out.println("Causado por: "+e.getMessage());
                    }

                }
                returnJSExecutor().executeScript("document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollBy({top: 80 ,left: 0})");
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
        var xPathContact = "//*[@id=\"app\"]/*//span[@title='" + nameContact + "']";
        return webDriver.findElement(By.xpath(xPathContact));
    }

    public WebElement findBoxText() {
        var xPathBoxText = "//*[@id=\"main\"]/footer//*[@role='textbox']";
        return webDriver.findElement(By.xpath(xPathBoxText));
    }

    public void scrollToTop(){
        returnJSExecutor().executeScript("document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTo({top: 0 ,left: 0})");
    }

    public JavascriptExecutor returnJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return js;
    }
    public String getscrollPosition(){
        return returnJSExecutor().executeScript("return document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTop").toString();
    }
    public void scrollingBy(){
        returnJSExecutor().executeScript("document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollBy({top: 99 ,left: 0,behavior: 'smooth'})");
    }

    public SendMessageOnSchedule(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
