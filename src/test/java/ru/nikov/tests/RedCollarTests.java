package ru.redcollar.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;




@Owner( "Nikolay Ovchinnikov" )
@Link(value = "repository", url = "https://github.com/nikovqa/RedCollarTestProject")

public class RedCollarTests extends TestBase {
@Disabled
    @Test
    @Tag( "critical" )
    @Severity( SeverityLevel.CRITICAL )
    @Feature("Сайт Red Collar")
    @Story( "В консоле страницы нет ошибок" )
    @DisplayName( "Ошибки в консоли сайта" )
    void consoleShouldNotHaveErrorsTest() {

        step("Открываем сайт " + baseUrl, () -> {
            open(baseUrl);
        });
        step("Журнал консоли не должен содержать текст 'SEVERE'", () -> {
            String consoleLogs = String.join("\n", Selenide.getWebDriverLogs( String.valueOf( LogType.BROWSER ) )),
                    errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @Tag( "normal" )
    @Severity( SeverityLevel.NORMAL )
    @Feature("Сайт Red Collar")
    @Story( "Пользователь может посмотреть проекты компании" )
    @DisplayName( "Просмотр проектов" )
    void projectsCanBeReachedFromMenuTest() {

        step( "Открываем сайт "+ baseUrl, ()-> {
            open("baseUrl");
        });
        step( "Переходим в меню", ()-> {
            $(".menu-btn").click();
        });
        step( "Переходим в 'работы'", ()-> {
            $("a[href*='/work?filter[tag]=all']").click();
        });
        step( "Проверяем что мы на странице с проектами", ()-> {
            $(".h3").shouldHave(text("проекты"));
        });
    }

    @Test
    @Tag( "critical" )
    @Severity( SeverityLevel.CRITICAL )
    @Feature("Сайт Red Collar")
    @Story( "В футере висит актуальная контактная информация" )
    @DisplayName( "Контактная информация в футере" )
    void footerRelevantContactTest() {

        step( "Открываем сайт " + baseUrl, ()-> {
            open(baseUrl);
        });
        step( "Пролистываем страницу вниз пока не увидим контактную информацию", ()-> {
            $(".contacts-menu").scrollIntoView(false);
        });
        step( "Проверяем контактактную информацию", ()-> {
            $(".contacts-menu").shouldHave(text(testData.PHONE_NUMBER + " " + testData.EMAIL + " " + testData.ADDRESS));
        });
    }
    @Test
    @Tag( "normal" )
    @Severity( SeverityLevel.NORMAL )
    @Feature("Сайт Red Collar")
    @Story( "Пользователь может выбрать вакансию" )
    @DisplayName( "Выбор акансии" )
    void vacancyCanBeViewedTest() {

        step( "Открываем сайт "+ baseUrl, ()-> {
            open(baseUrl);
        });
        step( "Переходим в меню", ()-> {
            $(".menu-btn").click();
        });
        step( "Переходим в 'карьера'", ()-> {
            $("a[href*='/career']").click();
        });
        step( "Смотрим вакансии", ()-> {
            $("#watch-vacancies-anchor").click();
        });
        step( "Выбираем Automation QA", ()-> {
            $("a[href*='career/qa-automation']").click();
        });
        step( "Проверяем описание вакансии Automation QA", ()-> {
            $(".h1").shouldHave(text("QA automation engineer"));
        });
    }
    @Test
    @Tag( "normal" )
    @Severity( SeverityLevel.NORMAL )
    @Feature("Сайт Red Collar")
    @Story( "Пользователь может откликнуться на вакансию" )
    @DisplayName( "Отклик на вакансию" )
    void applyForJobVacancyTest() {

        step( "Открываем форму", () -> {
            vacancyForm.openVacancyPage();
        });
        step( "Заполняем форму", ()-> {
            vacancyForm.setFirstName( testData.firstName )
                    .setLastName( testData.lastName )
                    .setPhoneNumber( testData.phone )
                    .setEmail( testData.email )
                    .setInfo( testData.text )
                    .attachCv()
                    .checkUploadedCv();
        });
        step( "Проверяем загрузку cv", ()-> {
            vacancyForm.checkUploadedCv();
        });
        step( "Проверяем что кнопка 'submit' доступна", ()-> {
            vacancyForm.checkSubmitButton();
        });
    }

}

