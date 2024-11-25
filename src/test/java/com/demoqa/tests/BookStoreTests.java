package com.demoqa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.api.AccountApi;
import com.demoqa.api.BookApi;
import com.demoqa.helpers.extentions.WithLogin;
import com.demoqa.models.GetBookListModel;
import com.demoqa.pages.ProfilePage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("API")
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Тесты с книгами")
    void deleteBookTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Удалить все книги из корзины", BookApi::deleteAllBooksInCart);

        step("Добавить книгу в корзину", () ->
                BookApi.addBookToList("9781449325862"));

        step("Удалить добавленную книгу", () -> {
            ProfilePage.openPage();
            ProfilePage.deleteUiBook();
        });

        step("Проверить удаление книги через UI", () -> {
            ProfilePage.openPage();
            ProfilePage.checkThatTheBookDeletedUI();
        });

        step("Получить список книг в корзине через API", () -> {
            GetBookListModel response = AccountApi.getListOfBooks();
            assertThat(response.getBooks()).isNotNull();
        });

        step("Проверить удаление книги через API", () -> {
            GetBookListModel response = AccountApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }

}
