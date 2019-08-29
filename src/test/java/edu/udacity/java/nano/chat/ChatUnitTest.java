package edu.udacity.java.nano.chat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatUnitTest {
    private WebDriver webDriver = new ChromeDriver();

    private Message message;

    @Before
    public void setup() {
        System.getProperties().setProperty("webdriver.chrome.driver", "chromedriver");
    }

    @Test
    public void login_to_chat() {
        this.webDriver.get("http://localhost:8080");

        WebElement userNameElement = this.webDriver.findElement(By.id("username"));
        userNameElement.sendKeys("user1");

        WebElement loginElement = this.webDriver.findElement(By.className("submit"));
        loginElement.click();

        Assert.assertNotNull(loginElement);

        WebElement testAreaElement = this.webDriver.findElement(By.id("msg"));
        testAreaElement.sendKeys("Hello There");

        WebElement sendMsgElement = this.webDriver.findElement(By.className("sendMsg"));
        sendMsgElement.click();


        WebElement logoutElement = this.webDriver.findElement(By.id("logout"));
        logoutElement.click();

        Assert.assertNotNull(logoutElement);

        //WebElement webElement = this.webDriver.findElement(By.cssSelector("div[class='mdui-card-content message-content']"));
        //System.out.println(webElement.getText());

//        System.out.println("Message is " + msgElement.findElement(By.className("mdui-card-content")).getAttribute("value"));
        //Assert.assertNotNull("Message Sent",msgElement.getAttribute("value"));

    }

    @After
    public void tearDown() {
        webDriver.quit();
    }


}
