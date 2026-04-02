import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.conditions.CombinedAttribute;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.appium.AppiumCondition.attribute;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class VKVideoTests {
    private static AndroidDriver driver;

    @BeforeAll
    public static void setup () throws InterruptedException {
        driver = OpenVkVideo.getDriver();
        Thread.sleep(4000);
        try {
            driver.findElement(By.id("com.vk.vkvideo:id/close_btn_left")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("com.vk.vkvideo:id/fast_login_tertiary_btn")).click();
        } finally {

        }
    }


    @Test
    public void testPlayingVideo () throws InterruptedException {

        driver.findElement(By.id("com.vk.vkvideo:id/search_button")).click();
        Thread.sleep(2000);
        WebElement text_fld = driver.findElement(By.id("com.vk.vkvideo:id/query"));
        text_fld.click();
        Thread.sleep(2000);
        text_fld.sendKeys("фишки гнилушки");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        Thread.sleep(6000);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward(40)"
        ));
        WebElement video = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.vk.vkvideo:id/title\" and @text=\"SOARER НА INTEL CORE i5 и ПНЕВМАПОДВЕСКА С ЗАВОДА! / TOYOTA SOARER GZ20\"]"));
        video.click();
        Thread.sleep(6000);
        driver.findElement(By.id("com.vk.vkvideo:id/video_display")).click();
        SelenideElement play_bttn = $(By.id("com.vk.vkvideo:id/video_play_button"));
        play_bttn.shouldHave(attribute(CombinedAttribute.android("content-desc"), "Pause"));
    }


    @Test
    public void testPausedVideo () throws InterruptedException {
        driver.findElement(By.id("com.vk.vkvideo:id/search_button")).click();
        Thread.sleep(2000);
        WebElement text_fld = driver.findElement(By.id("com.vk.vkvideo:id/query"));
        text_fld.click();
        Thread.sleep(2000);
        text_fld.sendKeys("фишки гнилушки");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        Thread.sleep(6000);
        VKVideoTests.driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward(40)"
        ));
        WebElement video = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.vk.vkvideo:id/title\" and @text=\"SOARER НА INTEL CORE i5 и ПНЕВМАПОДВЕСКА С ЗАВОДА! / TOYOTA SOARER GZ20\"]"));
        video.click();
        Thread.sleep(6000);
        driver.findElement(By.id("com.vk.vkvideo:id/video_display")).click();
        SelenideElement play_bttn = $(By.id("com.vk.vkvideo:id/video_play_button"));
        play_bttn.click();
        play_bttn.shouldHave(attribute(CombinedAttribute.android("content-desc"), "Play"));
    }

    @AfterEach
    public void getBack () throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().back();
        driver.navigate().back();
        driver.findElement(By.id("com.vk.vkvideo:id/closeView")); //xpath //android.widget.ImageButton[@content-desc="Close"]
        Thread.sleep(1000);
    }

    @AfterAll
    public static void fin () {
        driver.quit();
    }
}


