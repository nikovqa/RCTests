package ru.nikov.tests;

import io.qameta.allure.*;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Owner( "Nikolay Ovchinnikov" )
@Link(value = "repository", url = "https://github.com/nikovqa/")

public class RedCollarTests extends TestBase {

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

