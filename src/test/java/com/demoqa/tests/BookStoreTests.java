package com.demoqa.tests;


import api.BookStoreApi;
import com.demoqa.api.AccountApi;
import com.demoqa.helpers.extentions.WithLogin;
import com.demoqa.models.GetListBoookResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {
    @Tag("Regress")
    @Test
    @WithLogin.WithLogin
    @DisplayName("Проверка успешного удаления товара из списка")
    void successfulDeleteBookTest() {

        step("Очистить корзину с книгами", BookStoreApi::deleteAllBooksInCart);

        step("Добавить книгу в корзину", () ->
                api.BookStoreApi.addBookToList("9781449331818"));

        step("Удалить добавленную книгу", () -> {
            ProfilePage.openPage();
            ProfilePage.deleteBook();
        });

        step("Проверить, что книга удалена, через UI", () -> {
            ProfilePage.openPage();
            ProfilePage.checkDeleteThisBookUi();
        });

        step("Получить список книг в корзине, и проверить, что книга удалена, через API", () -> {
            GetListBoookResponseModel response = AccountApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }
}
