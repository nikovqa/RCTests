package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RedCollarVacancyForm {
    public void openVacancyPage() {

        open( "career/qa-automation" );
        $("#fill-form-anchor").click();

    }

    public RedCollarVacancyForm setFirstName(String value) {

        $( By.name( "firstname" )).setValue( value );

        return this;
    }

    public RedCollarVacancyForm setLastName(String value) {

        $( By.name( "lastname" )).setValue( value );

        return this;
    }

    public RedCollarVacancyForm setPhoneNumber(String value) {

        $( By.name( "phone" )).setValue( value );

        return this;
    }

    public RedCollarVacancyForm setEmail(String value) {

        $( By.name( "email" )).setValue( value );

        return this;
    }

    public RedCollarVacancyForm setInfo(String value) {

        $( By.name( "text" )).setValue( value );

        return this;
    }

    public RedCollarVacancyForm attachCv() {

        $( By.name( "cv" )).uploadFromClasspath( "cat.png" );

        return this;
    }

    public void checkUploadedCv() {
        $(".file-name").shouldHave(text("cat.png"));
    }
    public void checkSubmitButton() {
        $(".submit").shouldBe( enabled );
    }
}
