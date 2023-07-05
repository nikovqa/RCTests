package ru.redcollar.tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    final String PHONE_NUMBER = "+7 (473) 229-91-94",
            EMAIL = "hello@redcollar.ru",
            ADDRESS = "Россия, г. Воронеж,\n" +
                    "ул. Текстильщиков, 5Б";

    Faker faker = new Faker(new Locale("en"));
    String firstName = faker.address().firstName(),
            lastName = faker.address().lastName(),
            email = faker.internet().emailAddress(),
            text = faker.yoda().quote(),
            phone = String.valueOf(faker.number().randomNumber(10, true));
}
