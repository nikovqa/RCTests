package config;


import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProjectConfiguration {
    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void apiConfig() {
        RestAssured.baseURI = webConfig.baseUrl();
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.remote = webConfig.remoteUrl();
        if (webConfig.isRemote()) {

            Configuration.remote = webConfig.remoteUrl();
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            setChromeOptions(capabilities);

        }
    }

    public static void setChromeOptions(MutableCapabilities capabilities) {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-notifications")
                .addArguments("--lang=en-US")
                .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"})
                .merge(capabilities);

    }

    public String getVideoStorageUrl() {
        return webConfig.videoStorageUrl();
    }

    public Boolean isRemote() {
        return webConfig.isRemote();
    }
}
