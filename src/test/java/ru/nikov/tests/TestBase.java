package ru.nikov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Browser;
import config.ConfigReader;
import config.ProjectConfiguration;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RedCollarVacancyForm;

import java.util.Map;

@ExtendWith( {BrowserPerTestStrategyExtension.class} )
public class TestBase {
    TestData testData = new TestData();
    RedCollarVacancyForm vacancyForm = new RedCollarVacancyForm();

    private static final WebConfig webConfig = ConfigReader.Instance.read();
    private static ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        projectConfiguration.webConfig();
        projectConfiguration.apiConfig();

/*        Configuration.browser = System.getProperty( "browser","chrome" );
        Configuration.browserVersion = System.getProperty( "browserVersion","100.0" );
        Configuration.browserSize = System.getProperty( "browserSize", "1920x1080" );
        Configuration.baseUrl = System.getProperty( "baseUrl", "https://redcollar.ru/" );*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ) );
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs( "last screenshot" );
        Attach.pageSource();
        Attach.browserConsoleLogs();
            if (Configuration.browser.equals( Browser.CHROME.name())) {
                Attach.browserConsoleLogs();
        }
            if (projectConfiguration.isRemote()) {
                Attach.addVideo(projectConfiguration.getVideoStorageUrl());

        }
    }
}