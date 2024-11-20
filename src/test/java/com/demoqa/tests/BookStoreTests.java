package com.demoqa.tests;


import api.BookApi;
import com.demoqa.api.AccountApi;
import com.demoqa.helpers.extentions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("API")
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Тесты с книгами")
    void successfulAuthorization() {
        step("Удалить все книги у пользователя", BookApi::deleteAllBooksInProfile);
        step("Получить данные пользователя", AccountApi::getUserProfile);
        step("Добавить пользователю кингу", BookApi::addBooksInProfile);
        step("Удалить добавленную книгу", BookApi::deleteBooksInProfile);
        step("Проверить что у пользователя нет книг", AccountApi::verifyUserHasNoBooks);


    }
}
