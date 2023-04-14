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
        JavascriptExecutor js = returnJSExecutor();
        js.executeScript("function newButton(text, callback, position) { var a = document.createElement('button'); (a.innerHTML = text), (a.style.backgroundColor = '#44c767'), (a.style.backgroundImage = 'linear-gradient(#44c767, #64e787)'), (a.style.borderRadius = '28px'), (a.style.border = '1px solid #18ab29'), (a.style.display = 'inline-block'), (a.style.color = '#ffffff'), (a.style.fontSize = '17px'), (a.style.padding = '11px 31px'), (a.style.position = 'fixed'), (a.style.right = ' + (10 + ((150 + 15) * (position -1))) + px'), (a.style.width = '150px'), (a.style.top = '7px'), (a.style.zIndex = '999'); var b = document.getElementsByTagName('body')[0]; b.appendChild(a), a.addEventListener('click', function() { callback() }) } function getContent() { var content = ''; for(a in window.sContacts) content += `${a}\n`; return content; } function init() { newButton('Download', () => { download(getContent()) }, 1); getNumbers(); document.querySelector('#pane-side').addEventListener('scroll', getNumbers); } function download(content) { var data = 'data:application/octet-stream,' + encodeURIComponent(content); var newWindow = window.open(data, 'file'); } function getNumbers() { if(window.sContacts === undefined) window.sContacts = []; document.querySelectorAll('#pane-side div[role=grid] div[role=row] > div:first-child div:nth-child(2) div:first-child > div:first-child').forEach( element => { var phone = ''; if(/^((\\+| |-|\\d)+$)/g.test(element.innerText)) phone = element.innerText; else if(/^((\\+| |-|\\d)+$)/g.test(element.title)) phone = element.title; if(phone !== '') { window.sContacts[phone] = phone; element.style.backgroundColor = '#00ff00'; } else { element.style.backgroundColor = 'inherit'; } }) } init();");
        return contacts;
    }

    public boolean doSequenceToSendMessageOnChatScreen(Message message) throws InterruptedException {
        var canvasMessage = findBoxText();
        Thread.sleep(1500);

        for(int i=0;i< returnArrChar(message).length;i++){
            canvasMessage.sendKeys(returnArrChar(message)[i]);
            Thread.sleep(500);
        }

        Thread.sleep(1500);
        canvasMessage.sendKeys(Keys.RETURN);
        Thread.sleep(1500);
        if(!message.getPathImage().isEmpty()){
            webDriver.findElement(By.cssSelector("span[data-icon='clip']")).click();
            Thread.sleep(1500);
            webDriver.findElement(By.cssSelector("input[type='file']"))
                    .sendKeys(message.getPathImage().replaceAll("\\\\", "/"));
            Thread.sleep(1500);
            webDriver.findElement(By.xpath("//*[@id='app']/*//span[@data-icon='send']")).click();
        }
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

    public String[] returnArrChar(Message message){
        char[] arr = message.getContent().toCharArray();
        String[] strArr = new String[arr.length];
        for (int i =0;i<arr.length;i++) {
            strArr[i] = String.valueOf(arr[i]);
        }
        return strArr;
    }
}
