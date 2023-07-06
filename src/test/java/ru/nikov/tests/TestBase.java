package ru.nikov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RedCollarVacancyForm;

import java.util.Map;


public class TestBase {

    TestData testData = new TestData();
    RedCollarVacancyForm vacancyForm = new RedCollarVacancyForm();

    @BeforeAll
    static void setUp() {

        Configuration.browser = System.getProperty( "browser","chrome" );
        Configuration.browserVersion = System.getProperty( "browserVersion","100.0" );
        Configuration.browserSize = System.getProperty( "browserSize", "1920x1080" );
        Configuration.baseUrl = System.getProperty( "baseUrl", "https://redcollar.ru/" );
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ) );
        Configuration.browserCapabilities = capabilities;

/*        ChromeOptions options = new ChromeOptions();
        options.addArguments( "--remote-allow-origins=*" );
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(options);*/
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener( "allure", new AllureSelenide() );
    }


    @AfterEach
    void addAttachments() {
        AllureAttachments.screenshotAs( "last screenshot" );
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();
        AllureAttachments.addVideo();
//        Selenide.closeWebDriver();
    }
}