import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.*;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;



import java.io.File;

public class OpenVkVideo {
    public static void main(String[] args) throws Exception {
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        if (service.isRunning()) {
            System.out.println("Appium server is running");
        } else {
            service.start();
        }
    }

    private static AndroidDriver driver;
    static File app = new File("C:\\Users\\villi\\IdeaProjects\\test\\src\\com.vk.vkvideo_41980_rs.apk");
    public static AndroidDriver getDriver () {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Medium Phone")
                .setApp(app.getAbsolutePath())
                .setAppPackage("com.vk.vkvideo")
                .setAppActivity("com.vk.video.screens.main.MainActivity")
                .setAutomationName("UiAutomator2");

        driver = new AndroidDriver(options);
        WebDriverRunner.setWebDriver(driver);

        return driver;
    }
}
